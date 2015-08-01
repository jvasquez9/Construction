package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class PatternGenerator {
	
	// (!p0 & ... & !pn)
	public static String generateNotAndNotPattern(int numProposition, char charValue) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(Symbols.OPEN_Parenth);
		for (int i = 0; i < numProposition; i++)
		{
			stringBuilder.append(Symbols.NOT + "" + charValue + i);
			
			if(i < numProposition - 1)
			{
				stringBuilder.append(" " + Symbols.AND);
			}
		}
		stringBuilder.append(Symbols.CLOSE_Parenth);
		return stringBuilder.toString();
	}
	
}
