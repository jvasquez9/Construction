package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.regexpr.Language;
import edu.utep.cs5374.ltlgenerator.regexpr.DFAFactory;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;
import edu.utep.cs5374.ltlgenerator.utility.SubFormula;

public abstract class PatternRecognizer {
	
	protected static String alphabetOrString = generateRange('a', 'z', Symbols.OR.charAt(0));
	protected static String numericOrString = generateRange('0','9', Symbols.OR.charAt(0));
	
	private static final int ERROR_VALUE = -1;
	Language deterministicFiniteAutonoma;
	
	public PatternRecognizer(String regularExpression)
	{
		deterministicFiniteAutonoma = DFAFactory.generate(regularExpression);
	}
	
	public boolean recognizes(SubFormula possiblePattern)
	{
		return deterministicFiniteAutonoma.recognizes(possiblePattern.toString().toLowerCase());
	}
	
	public abstract String replace(SubFormula stringToReplace, String rightHandSide);

	protected int findSymbolLocation(String processedString, char symbol) {
		int symbolLocation = 0;
		while(symbolLocation < processedString.length())
		{
			if(processedString.charAt(symbolLocation) == symbol)
				return symbolLocation;
			symbolLocation++;
		}
		return ERROR_VALUE;
	}

	/*
	 * Generates a range of characters on the ascii table from starting position front
	 * to ending position back. It will place weaving symbols between each character
	 * in the range omitting characters that are refered by the ltl system as special
	 * characters.
	 */
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
