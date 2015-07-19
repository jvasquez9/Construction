package edu.utep.cs5374.ltlgenerator.and;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import edu.utep.cs5374.ltlgenerator.regexpr.DFAFactory;
import edu.utep.cs5374.ltlgenerator.regexpr.Language;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public abstract class AndParent {
	
	public static void main(String[] args)
	{
		System.out.println(AndParent.premiseAndNextChain.getRegExpr());
	}
	
	private static String alphabetOrString = generateRange('a', 'z', Symbols.OR.charAt(0));
	private static String numericOrString = generateRange('0','9', Symbols.OR.charAt(0));
	
	private static Language singlePremiseChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString +"*)");
	private static Language andChain = DFAFactory.generate("((~)*." + alphabetOrString + "." + numericOrString +"*)+(&.(~)*." +
			alphabetOrString + "." + numericOrString + "*)*");
	private static Language andWithTrailChain = DFAFactory.generate("((~)*." + alphabetOrString + "." + numericOrString +"*)+(&.(~)*." +
			alphabetOrString + "." + numericOrString + "*)*.&");
	private static Language untilChain = DFAFactory.generate("((~)*." + alphabetOrString + "." + numericOrString 
			+ "*).u.((~)*." + alphabetOrString + "." + numericOrString + "*)");
	private static Language arrowChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString + "*)." 
			+ Symbols.RIGHT_ARROW + ".(" + alphabetOrString + "." + numericOrString + "*)");
	private static Language orChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString +"*)+(/." +
			alphabetOrString + "." + numericOrString + "*)*");
	private static Language notChain = DFAFactory.generate("~." + alphabetOrString + "." + numericOrString + "*");
	private static Language premiseAndNextChain = DFAFactory.generate("(~)*" + alphabetOrString + "." + numericOrString 
			+ "*.(&.(~)*." + alphabetOrString + "." + numericOrString + "*)*.&.x");
	private static Language premiseAndNextPremiseChain = DFAFactory.generate(alphabetOrString + "." + numericOrString + "*.&.x."
			+ alphabetOrString + "." + numericOrString + "*");
	private static Language identityChain = DFAFactory.generate("&|u");
	private static Language fgOrNextChain = DFAFactory.generate("(x|f|g)." + alphabetOrString + "." + numericOrString + "*");
	private static Language premiseUntilChain = DFAFactory.generate("(~)." + alphabetOrString + "." + numericOrString + "*" + "u");
	
	@SuppressWarnings("unused")
	private static Language[] patternList = {singlePremiseChain, andChain, andWithTrailChain, untilChain, arrowChain, 
		orChain, notChain, premiseAndNextChain, premiseAndNextPremiseChain, identityChain, fgOrNextChain};
	
	protected enum TraversalMode {AND_R, AND_L, AND_MINUS_L}
	
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
	
	public abstract String and(String leftHandSide, String rightHandSide);
	
	public String andHelper(String leftHandSide, String rightHandSide, TraversalMode traversalType) {
		
		//Strip white space from both strings
		leftHandSide = leftHandSide.replaceAll("\\s+","");
		rightHandSide = rightHandSide.replaceAll("\\s+", "");
		
		//Since ! and | are used by the regexpr package,
		//let's use temporary substitutions ~ and /
		leftHandSide = leftHandSide.replaceAll("[!]", "~");
		leftHandSide = leftHandSide.replaceAll("[|]", "/");
		
		String result = "";
		
		//Note that this is a band-aid fix. This should be resolved in the andHelper(str, str, t_type, bool)
		//method. However, we get extra parenthesis when we do this so this is to avoid the extra parenthesis.
		//To-do: Fix this.
		if(notChain.recognizes(leftHandSide.substring(1, leftHandSide.length() - 1)))
		{
			result = "(!(" + leftHandSide.substring(2, leftHandSide.length() - 1) + "&" + rightHandSide + "))";
		}
		else if(andChain.recognizes(leftHandSide.substring(1, leftHandSide.length() - 1)) 
				|| orChain.recognizes(leftHandSide.substring(1, leftHandSide.length() - 1)))
		{
			//Special case. If we have a single and - or chain use this.
			//If we omitted this case we would have an extra pair of parenthesis.
			result = leftHandSide + "&" + rightHandSide;
		}
		else
		{
			//else delegate the work to helper function
			result = andHelper(leftHandSide.toLowerCase(), rightHandSide,
					traversalType, true);
		}
		
		//Finally, if ~ and / are still left in the string
		//replace them with their original symbols
		result = result.replaceAll("[~]", "!");
		result = result.replaceAll("[/]", "|");
		
		//Now let's return the result
		return result;
	}
	
	protected String andHelper(String leftHandSide, String rightHandSide, TraversalMode traversalType, 
			boolean containsRightMostTimeOp)
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
			String result = "";
			//We try to match the string to a pattern
			if(identityChain.recognizes(leftHandSide))
			{
				result = leftHandSide.toUpperCase();
			}
			else if(singlePremiseChain.recognizes(leftHandSide))
			{
				result = leftHandSide + "&" + rightHandSide;
			}
			else if(andChain.recognizes(leftHandSide) || orChain.recognizes(leftHandSide))
			{
				result = "(" + leftHandSide + ")&" + rightHandSide;
			}
			else if(andWithTrailChain.recognizes(leftHandSide))
			{
				result = leftHandSide + rightHandSide + "&";
			}
			else if(fgOrNextChain.recognizes(leftHandSide))
			{
				result = leftHandSide.toUpperCase().charAt(0) + "(" + leftHandSide.substring(1) + "&" + rightHandSide + ")";
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
				String left = andHelper(leftHandSide.substring(0, i),
						rightHandSide, traversalType, containsRightMostTimeOp);
				String right = andHelper(leftHandSide.substring(i+1), rightHandSide, 
						traversalType, containsRightMostTimeOp);
				result = "(" + left + ")" + Symbols.RIGHT_ARROW 
						+ "(" + right + ")";
			}
			else if(premiseAndNextChain.recognizes(leftHandSide))
			{
				result = "(" + andHelper(leftHandSide.substring(0,leftHandSide.length() - 2), rightHandSide,
						traversalType, false) + ")&X";
			}
			else if(premiseUntilChain.recognizes(leftHandSide))
			{
				result = "(" + leftHandSide.substring(0, leftHandSide.length() - 1) +
						"&" + rightHandSide + ")U";
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
				String left = leftHandSide.substring(0, i - 1);
				String right = leftHandSide.substring(i + 1);
				if (traversalType == TraversalMode.AND_MINUS_L)
					return "(" + left + "&" + rightHandSide + ")&X" + right;
				return "(" + left + "&" + rightHandSide + ")&X(" + right + "&" + rightHandSide + ")";
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
				
				String left = leftHandSide.substring(0, i);
				String right = leftHandSide.substring(i+1);
				if (traversalType == TraversalMode.AND_MINUS_L)
					return "(" + left + "&" + rightHandSide + ")U" + right;
				return "(" + left + "&" + rightHandSide + ")U(" + right + "&" + rightHandSide + ")";
			}
			
			//If we have a result, then use it.
			if (!result.isEmpty()) return result;
			//Else we failed to match the pattern, try to find sub formula
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
		
		//If we did not find a parenthesis pair. Return identity and log the error.
		//This basically suggests something went wrong with the algorithm (missing or
		//bad pattern)?
		if(currentCharacter >= leftHandSide.length())
		{
			
			try {
				FileWriter fw = new FileWriter("andRLog.txt", true);
				fw.write("Following string was unhandled by method at time " + (new Date().toString()) + "\n");
				fw.write(leftHandSide + "\n");
				fw.close();
			} catch (IOException e) {

			}
			
			return leftHandSide;
		}
		
		//Determine where the last time operator is in the expression.
		boolean leftHasTimeOp = false, centerHasTimeOp = false, rightHasTimeOp = false;
		for(int i = leftHandSide.length() - 1; i >= 0; i--)
		{
			if(leftHandSide.charAt(i) == Symbols.NEXT.toLowerCase().charAt(0) ||
					leftHandSide.charAt(i) == Symbols.UNTIL.toLowerCase().charAt(0))
			{
				leftHasTimeOp = inRange(i, 0, openParenthesisLocation) && containsRightMostTimeOp;
				centerHasTimeOp = inRange(i, openParenthesisLocation + 1, currentCharacter)
						&& containsRightMostTimeOp;
				rightHasTimeOp = inRange(i, currentCharacter + 1, leftHandSide.length())
						&& containsRightMostTimeOp;
				break;
			}
		}
		
		//Else we did find a parenthesis pair. Do a three way recursive call & try to find more sub formula.
		String left = andHelper(leftHandSide.substring(0, openParenthesisLocation), rightHandSide,
				traversalType, leftHasTimeOp);
		String center = "(" + andHelper(leftHandSide.substring(openParenthesisLocation + 1, currentCharacter),
				rightHandSide, traversalType, centerHasTimeOp) + ")";
		String right = andHelper(leftHandSide.substring(currentCharacter+1), rightHandSide, 
				traversalType, rightHasTimeOp);
		
		String output = left + center + right;
		
		return output;
	}
	
	private boolean containsTimeOp(String testString)
	{
		for(int i = 0; i < testString.length(); i++)
		{
			if(testString.charAt(i) == Symbols.NEXT.toLowerCase().charAt(0) ||
					testString.charAt(i) == Symbols.UNTIL.toLowerCase().charAt(0))
			{
				return true;
			}
		}
		return false;
	}
	
	private boolean inRange(int value, int min, int max)
	{
		return value >= min && value <= max;
	}
}
