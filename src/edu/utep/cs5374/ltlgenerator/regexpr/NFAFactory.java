package edu.utep.cs5374.ltlgenerator.regexpr;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * The NFAFactory class generates a Language with a non deterministic graph
 * given a regular expression. This class adheres to the singleton design
 * pattern so in order to use it one must invoke the generate(String) method.
 * If an invalid regular expression is provided to the method an IllegalArgumentException
 * will be thrown. Three stacks plus the {@link Operator} class are used in order
 * to parse a string. One stack holds operands, one holds operators, and the third stack
 * is used to correct the order of operands coming out of the operand stack. There is a
 * map as well that contains key value pairs of strings and {@link Operator}'s. This
 * is used more for simplification rather than necessity.
 * 
 * @author Robert McCain
 * @since 10/22/2014
 * @version 1.0
 *
 */
public class NFAFactory{
		private Map<Character, Operator> operatorMap = new HashMap<Character, Operator>();
		private static NFAFactory singleton = new NFAFactory();

		private NFAFactory()
		{
			Operator[] operations = {
					new StarOperator(),
					new ConcatenationOperator(),
					new PlusOperator(),
					new UnionOperator(),
					new ComplementOperator(),
					new IntersectionOperator()
			};
			
			for(Operator op : operations)
			{
				operatorMap.put(op.symbol(), op);
			}
		}
		
		public static Language generate(String s)
		{
			return singleton.generateNFA(s);
		}
		
		private Language generateNFA(String lang)
		{
			lang.toLowerCase();
			lang.replaceAll("\\s+", "");
			lang = addImplicitConcatenators(lang);
			Set<String> alphabet = generateAlphabet(lang);
			
			if(!testExpression(lang))
				throw new IllegalArgumentException("[" + lang + "] is not a valid regular expression!");
			
			Stack<Node> nodeStack = new Stack<Node>();
			Stack<Operator> opStack = new Stack<Operator>();
			int weight = 0;
			
			for(int i = 0; i < lang.length(); i++)
			{
				char current = lang.charAt(i);
				
				if(isLetter(current))
				{
					nodeStack.add(SingleCharacterLanguageFactory.generateLanguage(current + ""));
				}
				else
				{
					Operator nextOperator = operatorMap.get(current);
					
					if(isOpenParenthesis(current))
					{
						weight += 10;
						continue;
					}
					else if(isCloseParenthesis(current))
					{
						weight -= 10;
						continue;
					}
					else if(nextOperator == null)
						throw new IllegalArgumentException(current + " is not recognized by the parser!");
					
					nextOperator = nextOperator.clone();
					nextOperator.setWeightOffset(weight);
					nextOperator.setAlphabet(alphabet);
					
					if(!opStack.isEmpty())
					{
						while(opStack.peek().precedence() >= nextOperator.precedence())
						{
							Operator previousOperator = opStack.pop();
							Stack<Node> tmp = new Stack<Node>();
							for(int j = 0; j < previousOperator.operands(); j++)
							{
								tmp.push(nodeStack.pop());
							}
							while(!tmp.isEmpty())
							{
								previousOperator.register(tmp.pop());
							}
							
							Node newNode = previousOperator.evaluate();
							
							if(newNode != null)
								nodeStack.push(newNode);
							
							if(opStack.isEmpty())
								break;
						}
					}
					
					opStack.add(nextOperator);
				}
			}
			
			while(!opStack.isEmpty())
			{
				Operator operator = opStack.pop();
				Stack<Node> tmp = new Stack<Node>();
				for(int j = 0; j < operator.operands(); j++)
				{
					tmp.push(nodeStack.pop());
				}
				while(!tmp.isEmpty())
				{
					operator.register(tmp.pop());
				}
				Node newNode = operator.evaluate();
				if(newNode != null)
					nodeStack.push(newNode);
			}
			
			if(nodeStack.size() == 1)
				return new Language(nodeStack.firstElement(), alphabet, lang, false);
			throw new IllegalArgumentException("There was an irregular amount of operators!");
		}
		
		private static String addImplicitConcatenators(String lang)
		{
			StringBuilder sb = new StringBuilder();
			boolean previous = false;
			for(int i = 0; i < lang.length(); i++)
			{
				char current = lang.charAt(i);
				
				if((isLetter(current) || isOpenParenthesis(current) || isComplement(current)) && previous)
				{
					sb.append('.');
				}
				
				sb.append(current);
				
				previous = isLetter(current) || isStarOrPlus(current) || isCloseParenthesis(current);
			}
			return sb.toString();
		}
		
		private static boolean isComplement(char c)
		{
			return c == '!';
		}
		
		private static boolean isLetter(char c)
		{
			return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || (c == '&') || (c == '>')
					|| (c == '~') || (c == '/');
		}
		
		private static boolean isCloseParenthesis(char c)
		{
			return c == ')';
		}
		
		private static boolean isOpenParenthesis(char c)
		{
			return c == '(';
		}
		
		private static boolean isStarOrPlus(char c)
		{
			return c == '*' || c == '+';
		}
		
		private static boolean isValidSymbol(char c)
		{
			boolean isValid = false;
			isValid |= isLetter(c);
			isValid |= isCloseParenthesis(c);
			isValid |= isOpenParenthesis(c);
			isValid |= isOperator(c);
			return isValid;
		}
		
		private static boolean isOperator(char c)
		{
			return singleton.operatorMap.containsKey(c);
		}
		
		private static Set<String> generateAlphabet(String s)
		{
			Set<String> alphabet = new HashSet<String>();
			for(int i = 0; i < s.length(); i++)
				if(isLetter(s.charAt(i)))
					alphabet.add(s.charAt(i) + "");
			return alphabet;
		}
		
		private static boolean testExpression(String s)
		{
			int openParenthesisCount = 0;
			int closeParenthesisCount = 0;
			int letterCount = 0; //Count of total letters(not unique ones)
			
			if(s.length() == 0)
			{
				return false; //We cannot have a regexpr of 0
			}
			
			for(int i = 0; i < s.length(); i++)
			{
				char current = s.charAt(i);
				
				if(!isValidSymbol(current))
					return false; //Not a valid symbol or operator
				
				if(isLetter(current))
				{
					letterCount++;
				}
				else if(isOpenParenthesis(current))
				{
					openParenthesisCount++;
				}
				else if(isCloseParenthesis(current))
				{
					closeParenthesisCount++;
				}
				
				if(closeParenthesisCount > openParenthesisCount)
				{
					return false; //a close parenthesis occurred before an opening one
				}
			}
			
			if(openParenthesisCount != closeParenthesisCount)
			{
				return false; //Must have equal count of parenthesis
			}
			
			if(letterCount == 0)
			{
				return false; //Must have at least one letter in the alphabet
			}
			
			return true; //Passed Validations
		}
}
