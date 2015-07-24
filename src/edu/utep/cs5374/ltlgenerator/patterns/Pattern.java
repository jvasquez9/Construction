package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.regexpr.Language;
import edu.utep.cs5374.ltlgenerator.regexpr.DFAFactory;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;
import edu.utep.cs5374.ltlgenerator.utility.SubString;

public abstract class Pattern {
	
	protected static String alphabetOrString = generateRange('a', 'z', Symbols.OR.charAt(0));
	protected static String numericOrString = generateRange('0','9', Symbols.OR.charAt(0));
	
	private static final int ERROR_VALUE = -1;
	Language deterministicFiniteAutonoma;
	
	public Pattern(String regularExpression)
	{
		deterministicFiniteAutonoma = DFAFactory.generate(regularExpression);
	}
	
	public boolean recognizes(SubString possiblePattern)
	{
		return deterministicFiniteAutonoma.recognizes(possiblePattern.toString().toLowerCase());
	}
	
	public abstract String replace(SubString stringToReplace, String rightHandSide);

	protected int findSymbolLocation(String processedString, char symbol) {
		int untilLocation = 0;
		while(untilLocation < processedString.length())
		{
			if(processedString.charAt(untilLocation) == symbol)
				return untilLocation;
			untilLocation++;
		}
		return ERROR_VALUE;
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
}
