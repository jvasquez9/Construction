package edu.utep.cs5374.ltlgenerator.and;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Stack;

import edu.utep.cs5374.ltlgenerator.patterns.*;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;
import edu.utep.cs5374.ltlgenerator.utility.SubString;

public abstract class AndParent {
	
	private static Pattern singlePremiseChain = new SinglePremisePattern();
	private static Pattern uniaryOperatorChain = new UniaryOperatorPattern();
	private static Pattern untilChain = new PremiseUntilPattern();
	private static Pattern AndChain = new AndPattern();
	private static Pattern orChain = new OrPattern();
	private static Pattern AndWithTrailingAndChain = new AndWithTrailingAndPattern();
	private static Pattern notPremiseChain = new NotPremisePattern();
	private static Pattern AndWithTrailingAndNextChain = new AndWithTrailingAndNextPattern();
	
	private static Pattern[] peliminaryTests = {notPremiseChain, AndChain, orChain};
	
	private static Pattern[] patternList = {singlePremiseChain, uniaryOperatorChain,
		untilChain, orChain, AndChain, AndWithTrailingAndChain, AndWithTrailingAndNextChain};
	
	public enum TraversalMode{AND_R, AND_L, AND_MINUS_L};
	
	public abstract String and(String leftHandSide, String rightHandSide);
		
	protected String and(String leftHandSide, String rightHandSide, TraversalMode traversalMode) {
		//Strip white space from both strings
		leftHandSide = leftHandSide.replaceAll("\\s+","");
		rightHandSide = rightHandSide.replaceAll("\\s+", "");
		
		//Since ! and | are used by the regexpr package,
		//let's use temporary substitutions ~ and /
		leftHandSide = leftHandSide.replaceAll("[!]", "~");
		leftHandSide = leftHandSide.replaceAll("[|]", "/");
		
		//Seriously, the only reason this "fix" is here is because an extra
		//pair of parenthesis will be added to select expressions that will
		//cause them to fail test cases. Don't like adding such logic but
		//these checks are required.
		String dirtyFix = patternMatchHelper(new SubString(0, leftHandSide.length() - 1, 
				leftHandSide), rightHandSide, peliminaryTests);
		
		if(!dirtyFix.isEmpty())
		{
			dirtyFix = dirtyFix.replaceAll("[~]", "!");
			dirtyFix = dirtyFix.replaceAll("[/]", "|");
			System.out.println(dirtyFix);
			return dirtyFix;
		}
		
		//First, compute our substrings.
		String workingString = "(" + leftHandSide + ")";
		Stack<SubString> initialSubStrings = computeSubStrings(workingString);
		
		//Second, filter out substring that we do not need to process
		Stack<SubString> filteredSubStrings = filterSubStrings(initialSubStrings, workingString);
		
		//Third, split the top token if we have to
		splitTopToken(filteredSubStrings, workingString);
		
		//Next, let's do string replacements using the stack we generated.
		String outputString = processSubStrings(traversalMode, rightHandSide, workingString,
				filteredSubStrings);
		
		//Finally, if ~ and / are still left in the string
		//replace them with their original symbols
		outputString = outputString.replaceAll("[~]", "!");
		outputString = outputString.replaceAll("[/]", "|");
		
		//Okay just kidding. We need to remove the extra parenthesis we added
		outputString = outputString.substring(1, outputString.length() - 1);
		
		System.out.println(outputString);
		
		return outputString;
	}

	private void splitTopToken(Stack<SubString> filteredSubStrings,
			String workingString) {
		SubString topToken = filteredSubStrings.pop();
		
		for(int i = topToken.getLeftIndex() + 1; i < topToken.getRightIndex(); i++)
		{
			char currentCharacter = workingString.charAt(i);
			
			//If this condition is satisfied, we must split the string
			if(currentCharacter == Symbols.NEXT.charAt(0) ||
					currentCharacter == Symbols.UNTIL.charAt(0) ||
					currentCharacter == Symbols.RIGHT_ARROW.charAt(0))
			{
				SubString leftHandSide = new SubString(topToken.getLeftIndex(), i + 1, workingString);
				SubString rightHandSide = new SubString(i, topToken.getRightIndex(), workingString);
				
				if(!leftHandSide.toString().isEmpty())
				{
					filteredSubStrings.push(leftHandSide);
				}
				
				if(!rightHandSide.toString().isEmpty())
				{
					filteredSubStrings.push(rightHandSide);
				}
				
				return;
			}
		}
		
		//Else we leave the token alone and put it back on the stack
		filteredSubStrings.push(topToken);
	}

	private String processSubStrings(TraversalMode traversalMode, String rightHandSide,
			String workingString, Stack<SubString> filteredSubStrings) {
		int characterIterator = workingString.length() - 1;
		String outputString = "";
		boolean lastTimeBasedExpression = true;
		while(!filteredSubStrings.isEmpty())
		{
			SubString currentPair = filteredSubStrings.pop();
			
			//First, get any characters that are not in page specified
			//in the above pair.
			while(characterIterator >= currentPair.getRightIndex())
			{
				outputString = workingString.charAt(characterIterator--) + outputString;
			}
			
			//Next, and the expression if it satisfies our criteria else
			//do not modify the string.
			if(useAnd(traversalMode, lastTimeBasedExpression))
			{
				outputString = patternMatch(currentPair, rightHandSide) + outputString;
			}
			else
			{
				outputString = currentPair.toString() + outputString;
			}
			
			
			//Finally, set the characterIterator equal to the front of the range
			characterIterator = currentPair.getLeftIndex();
			lastTimeBasedExpression = false;
		}
		
		//Now let's be sure to grab the leading characters.
		while(characterIterator >= 0)
		{
			outputString = workingString.charAt(characterIterator--) + outputString;
		}
		return outputString;
	}
	
	private Stack<SubString> computeSubStrings(String processedString)
	{
		Stack<SubString> parenthesisPairs = new Stack<SubString>();
		
		for(int currentCharacter = 0; currentCharacter < processedString.length(); currentCharacter++)
		{
			//First, check if current character is an open parenthesis.
			int openParenthesisIndex = currentCharacter;
			if(processedString.charAt(openParenthesisIndex) != Symbols.OPEN_Parenth.charAt(0))
			{
				//This is not an open parenthesis. Continue.
				continue;
			}
			
			//Second, find the matching closing parenthesis
			int innerPairCount = 0;
			int closeParenthesisIndex = openParenthesisIndex + 1;
			while(closeParenthesisIndex < processedString.length())
			{
				if(processedString.charAt(closeParenthesisIndex) == Symbols.CLOSE_Parenth.charAt(0))
				{
					if(innerPairCount == 0) break;
					
					innerPairCount--;
				}
				else if(processedString.charAt(closeParenthesisIndex) == Symbols.OPEN_Parenth.charAt(0))
				{
					innerPairCount++;
				}
				
				closeParenthesisIndex++;
			}
			
			//Finally, push the pair to the stack
			parenthesisPairs.push(new SubString(openParenthesisIndex, closeParenthesisIndex, processedString));
		}
		
		return parenthesisPairs;
	}
	
	

	private Stack<SubString> filterSubStrings(Stack<SubString> inputStack, String workingString)
	{
		Stack<SubString> processedElements = new Stack<SubString>();
		
		while(!inputStack.isEmpty())
		{
			SubString currentPair = inputStack.pop();
			
			//If we have a leading parenthesis, we want to omit it from the stack
			if(currentPair.charAt(0) == Symbols.OPEN_Parenth.charAt(0))
			{
				continue;
			}
			
			//Else if we have a sub formula push the pair as-is.
			if(currentPair.isSubFormula())
			{
				processedElements.push(currentPair);
				continue;
			}
			
			//Else we have a compound expression. Recompute the pair then push that to the stack.
			//To-do: stuff
			processedElements.push(currentPair.findSubformula());
		}
		
		//We now must flip the stack in order to correct the order
		Stack<SubString> returnedStack = new Stack<SubString>();
		
		while(!processedElements.isEmpty())
		{
			returnedStack.push(processedElements.pop());
		}
		
		return returnedStack;
	}
	
	private boolean useAnd(TraversalMode traversalMode, boolean isLastTimeBasedExpression)
	{
		if(traversalMode == TraversalMode.AND_MINUS_L)
			return !isLastTimeBasedExpression;
		else if(traversalMode == TraversalMode.AND_L)
			return isLastTimeBasedExpression;
		return true;
	}
	
	private String patternMatch(SubString possiblePattern, String rightHandSide)
	{
		//This is a chain of responsibility. We try to find a regular expression to find a
		//replacement. If we do not find a pattern we return identity then log the pattern.
		
		String result = patternMatchHelper(possiblePattern, rightHandSide, patternList);
		
		if(!result.isEmpty())
		{
			return result;
		}
		
		//If all else fails, log the pattern and have Robert take a look at it.
		try {
			FileWriter fw = new FileWriter("AndDiagnosticLog.txt", true);
			fw.write("Time: " + (new Date().toString()) + "\n");
			fw.write("The following pattern was unrecognized by the program: " + 
					possiblePattern.toString() + "\n");
			fw.write("Index Pair was as follows: " + 
					possiblePattern.getLeftIndex() + "," + possiblePattern.getRightIndex() + "\n");
			fw.write("-----------------------------------------------------------------------------\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return possiblePattern.toString();
	}
	
	private String patternMatchHelper(SubString possiblePattern, String rightHandSide,
			Pattern[] patternList)
	{
		for(Pattern p : patternList)
		{
			if(p.recognizes(possiblePattern))
			{
				return p.replace(possiblePattern, rightHandSide);
			}
		}
		return "";
	}
}
