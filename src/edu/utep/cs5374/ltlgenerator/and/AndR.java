package edu.utep.cs5374.ltlgenerator.and;
import edu.utep.cs5374.ltlgenerator.regexpr.*;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AndR implements AndParent{
	
	private static String alphabetOrString = generateRange('a', 'z', Symbols.OR.charAt(0));
	private static String numericOrString = generateRange('0','9', Symbols.OR.charAt(0));
	private static Language singlePremiseChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString +"*)");;
	private static Language andChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString +"*)+(&." +
			alphabetOrString + "." + numericOrString + "*)*");
	private static Language fChain = DFAFactory.generate("f." + alphabetOrString + "." + numericOrString + "*");
	private static Language gChain = DFAFactory.generate("g." + alphabetOrString + "." + numericOrString + "*");;
	private static Language untilChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString + "*).u.(" 
			+ alphabetOrString + "." + numericOrString + "*)");
	private static Language nextChain = DFAFactory.generate("x." + alphabetOrString + "." + numericOrString + "*");
	private static Language arrowChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString + "*)." 
			+ Symbols.RIGHT_ARROW + ".(" + alphabetOrString + "." + numericOrString + "*)");
	private static Language orChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString +"*)+(/." +
			alphabetOrString + "." + numericOrString + "*)*");
	private static Language notChain = DFAFactory.generate("~." + alphabetOrString + "." + numericOrString + "*");
	private static Language premiseAndNextChain = DFAFactory.generate(alphabetOrString + "." + numericOrString + "*.&.x");
	private static Language premiseAndNextPremiseChain = DFAFactory.generate(alphabetOrString + "." + numericOrString + "*.&.x."
			+ alphabetOrString + "." + numericOrString + "*");
	
	public static void main(String[] args)
	{
		
	}
	
	private static String generateRange(char front, char back, char weavingSymbol)
	{
		StringBuilder builder = new StringBuilder(Symbols.OPEN_Parenth);
		for(char current = front; current <= back; current++)
		{
			if(Symbols.isSymbol(current))
				continue;
			
			builder.append(current);
			if(current < back)
				builder.append(weavingSymbol);
		}
		builder.append(Symbols.CLOSE_Parenth);
		return builder.toString();
	}
	
	public AndR()
	{

	}
	
	@Override
	public String and(String leftHandSide, String rightHandSide) {
		
		//Strip white space from both strings
		leftHandSide = leftHandSide.replaceAll("\\s+","");
		rightHandSide = rightHandSide.replaceAll("\\s+", "");
		
		//Since ! and | are used by the regexpr package,
		//let's use temporary substitutions ~ and /
		leftHandSide = leftHandSide.replaceAll("[!]", "~");
		leftHandSide = leftHandSide.replaceAll("[|]", "/");
		
		String result = "";
		
		if(andChain.recognizes(leftHandSide.substring(1, leftHandSide.length() - 1)) 
				|| orChain.recognizes(leftHandSide.substring(1, leftHandSide.length() - 1)))
		{
			//Special case. If we have a single and - or chain use this.
			//If we omitted this case we would have an extra pair of parenthesis.
			result = leftHandSide + "&" + rightHandSide;
		}
		else
		{
			//else delegate the work to our helper function
			result = andHelper(leftHandSide.toLowerCase(), rightHandSide,
					0, true);
		}
		
		//Finally, if ~ and / are still left in the string
		//replace them with their original symbols
		result = result.replaceAll("[~]", "!");
		result = result.replaceAll("[/]", "|");
		
		//Now let's return the result
		return result;
	}

	private String andHelper(String leftHandSide, String rightHandSide, int andType, 
			boolean containsLeftMostTimeOp)
	{
		int openParenthesisLocation = 0;
		
		//First, if we have an empty string return an empty string.
		if(leftHandSide.isEmpty())
		{
			return "";
		}
		
		//If the lead character is not an Open Parenthesis, submit
		//the string to our pattern matching algorithms.
		if (leftHandSide.charAt(openParenthesisLocation) != Symbols.OPEN_Parenth.charAt(0))
		{
			//We try to match the string to a pattern
			if(singlePremiseChain.recognizes(leftHandSide))
			{
				return leftHandSide + "&" + rightHandSide;
			}
			if(andChain.recognizes(leftHandSide) || orChain.recognizes(leftHandSide))
			{
				return "(" + leftHandSide + ")&" + rightHandSide;
			}
			else if(fChain.recognizes(leftHandSide))
			{
				return "F(" + leftHandSide.substring(1) + "&" + rightHandSide + ")";
			}
			else if(gChain.recognizes(leftHandSide))
			{
				return "G(" + leftHandSide.substring(1) + "&" + rightHandSide + ")";
			}
			else if(nextChain.recognizes(leftHandSide))
			{
				return "X(" + leftHandSide.substring(1) + "&" + rightHandSide + ")";
			}
			else if(notChain.recognizes(leftHandSide))
			{
				return "!(" + leftHandSide.substring(1) + "&" + rightHandSide + ")";
			}
			else if(untilChain.recognizes(leftHandSide))
			{
				//Find U then split based on U
				int i;
				for(i = 0;i < leftHandSide.length(); i++)
				{
					if(leftHandSide.charAt(i) == Symbols.UNTIL.toLowerCase().charAt(0))
						break;
				}
				return "(" + leftHandSide.substring(0,i)+ "&" + rightHandSide + ")U(" 
					+ leftHandSide.substring(i+1, leftHandSide.length()) + "&" + rightHandSide 
					+ ")";
			}
			else if(arrowChain.recognizes(leftHandSide))
			{
				//Find arrow then split based on arrow
				int i;
				for(i = 0;i < leftHandSide.length(); i++)
				{
					if(leftHandSide.charAt(i) == Symbols.RIGHT_ARROW.charAt(0))
						break;
				}
				return "(" + leftHandSide.substring(0,i)+ "&" + rightHandSide + ")" + Symbols.RIGHT_ARROW + "(" 
					+ leftHandSide.substring(i+1, leftHandSide.length()) + "&" + rightHandSide 
					+ ")";
			}
			else if(premiseAndNextChain.recognizes(leftHandSide))
			{
				return "(" + andHelper(leftHandSide.substring(0,leftHandSide.length() - 2), rightHandSide,
						0, false) + ")&X";
			}
			else if(premiseAndNextPremiseChain.recognizes(leftHandSide))
			{
				//Find x and split based on x
				int i;
				for(i = 0;i < leftHandSide.length(); i++)
				{
					if(leftHandSide.charAt(i) == Symbols.NEXT.toLowerCase().charAt(0))
						break;
				}
				String left = "(" + leftHandSide.substring(0, i - 1) + "&" + rightHandSide + ")";
				String right = "X(" + leftHandSide.substring(i + 1) + "&" + rightHandSide + ")";
				return left + "&" + right;
			}
			//If we failed to match the pattern, try to find sub formula
		} 
		
		//Try to find an open parenthesis in the string
		while(openParenthesisLocation < leftHandSide.length() &&
				leftHandSide.charAt(openParenthesisLocation) != Symbols.OPEN_Parenth.charAt(0))
			openParenthesisLocation++;
		
		int currentCharacter = openParenthesisLocation + 1;
		int parenthesisPairMatchingValue = 0; 
		
		//Now let's find a pair of matching parenthesis.
		while(currentCharacter < leftHandSide.length())
		{
			if(leftHandSide.charAt(currentCharacter) == Symbols.OPEN_Parenth.charAt(0))
			{
				parenthesisPairMatchingValue++;
			}
			else if(leftHandSide.charAt(currentCharacter) == Symbols.CLOSE_Parenth.charAt(0) && parenthesisPairMatchingValue > 0)
			{
				parenthesisPairMatchingValue--;
			}
			else if(leftHandSide.charAt(currentCharacter) == Symbols.CLOSE_Parenth.charAt(0))
			{
				break;
			}
			
			currentCharacter++;
		}
		
		//If we did not find a parenthesis pair, trim the first character off and try again
		if(currentCharacter >= leftHandSide.length())
		{
			return andHelper(leftHandSide.substring(1), rightHandSide, 0, false);
		}
		
		//Else we did find a parenthesis pair. Do a three way recursive call & try to find more sub formula
		String left = andHelper(leftHandSide.substring(0, openParenthesisLocation), rightHandSide,
				0, false);
		String center = "(" + andHelper(leftHandSide.substring(openParenthesisLocation + 1, currentCharacter),
				rightHandSide, 0, false) + ")";
		String right = andHelper(leftHandSide.substring(currentCharacter+1), rightHandSide, 0, false);
		
		String output = left + center + right;
		
		return output;
	}
}
