package edu.utep.cs5374.ltlgenerator.and;
import edu.utep.cs5374.ltlgenerator.regexpr.*;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AndR implements AndParent{
	
	private static String alphabetOrString = generateRange('a', 'z', Symbols.OR.charAt(0));
	private static String numericOrString = generateRange('0','9', Symbols.OR.charAt(0));
	private Language andChain;
	private Language fChain;
	private Language gChain;
	private Language untilChain;
	private Language nextChain;
	private Language arrowChain;
	private Language orChain;
	private Language notChain;
	
	public static void main(String[] args)
	{
		System.out.println(new AndL().and("(p0|p1|p2)", "P"));
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
		andChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString +"*)+(&." +
				alphabetOrString + "." + numericOrString + "*)*");
		fChain = DFAFactory.generate("f." + alphabetOrString + "." + numericOrString + "*");
		gChain = DFAFactory.generate("g." + alphabetOrString + "." + numericOrString + "*");
		untilChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString + "*).u.(" 
				+ alphabetOrString + "." + numericOrString + "*)");
		nextChain = DFAFactory.generate("x." + alphabetOrString + "." + numericOrString + "*");
		arrowChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString + "*)." 
				+ Symbols.RIGHT_ARROW + ".(" + alphabetOrString + "." + numericOrString + "*)");
		orChain = DFAFactory.generate("(" + alphabetOrString + "." + numericOrString +"*)+(/." +
				alphabetOrString + "." + numericOrString + "*)*");
		notChain = DFAFactory.generate("~." + alphabetOrString + "." + numericOrString + "*");
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
		
		//Now delegate the work to our helper function
		String result = andHelper(leftHandSide.toLowerCase(), rightHandSide);
		
		//Finally, if ~ and / are still left in the string
		//replace them with their original symbols
		result = result.replaceAll("[~]", "!");
		result = result.replaceAll("[/]", "|");
		
		//Now let's return the result
		return result;
	}

	private String andHelper(String leftHandSide, String rightHandSide)
	{
		//First, if we have an empty string return an empty string.
		if(leftHandSide.isEmpty())
		{
			return "";
		}
		
		//If the lead character is not an Open Parenthesis, submit
		//the string to our pattern matching algorithms.
		if(leftHandSide.charAt(0) != Symbols.OPEN_Parenth.charAt(0))
		{			
			if(andChain.recognizes(leftHandSide) || orChain.recognizes(leftHandSide))
			{
				return "(" + leftHandSide + ")&" + rightHandSide;
			}
			else if(fChain.recognizes(leftHandSide))
			{
				return "(F(" + leftHandSide.substring(1) + "&" + rightHandSide + "))";
			}
			else if(gChain.recognizes(leftHandSide))
			{
				return "(G(" + leftHandSide.substring(1) + "&" + rightHandSide + "))";
			}
			else if(nextChain.recognizes(leftHandSide))
			{
				return "(X(" + leftHandSide.substring(1) + "&" + rightHandSide + "))";
			}
			else if(notChain.recognizes(leftHandSide))
			{
				return "(!(" + leftHandSide.substring(1) + "&" + rightHandSide + "))";
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
				return "((" + leftHandSide.substring(0,i)+ "&" + rightHandSide + ")U(" 
					+ leftHandSide.substring(i+1, leftHandSide.length()) + "&" + rightHandSide 
					+ "))";
			}
			else if(arrowChain.recognizes(leftHandSide))
			{
				//Find arrow then split based on arrow
				System.out.println(leftHandSide);
				int i;
				for(i = 0;i < leftHandSide.length(); i++)
				{
					if(leftHandSide.charAt(i) == Symbols.RIGHT_ARROW.charAt(0))
						break;
				}
				return "((" + leftHandSide.substring(0,i)+ "&" + rightHandSide + ")" + Symbols.RIGHT_ARROW + "(" 
					+ leftHandSide.substring(i+1, leftHandSide.length()) + "&" + rightHandSide 
					+ "))";
			}
			else
			{
				//Did not find a match. Iterating to next character.
				return andHelper(leftHandSide.substring(1, leftHandSide.length()), rightHandSide);
			}
		}
		
		//Else we go to our recursive case. Break the string down into new substrings.
		for(int j = 1, k = 0; j < leftHandSide.length(); j++)
		{
			if(leftHandSide.charAt(j) == Symbols.OPEN_Parenth.charAt(0))
			{
				k++;
			}
			else if(leftHandSide.charAt(j) == Symbols.CLOSE_Parenth.charAt(0) && k > 0)
			{
				k--;
			}
			else if(leftHandSide.charAt(j) == Symbols.CLOSE_Parenth.charAt(0))
			{
				return andHelper(leftHandSide.substring(1, j), rightHandSide) +
						andHelper(leftHandSide.substring(j+1, leftHandSide.length()), rightHandSide);
			}
		}
		
		System.out.println("We were not able to partition the string. Terminating...");
		return leftHandSide + "&" + rightHandSide;
	}
}
