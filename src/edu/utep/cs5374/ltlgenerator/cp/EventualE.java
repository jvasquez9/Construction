package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.patterns.PatternGenerator;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class EventualE implements CompositePropositionParent {
	
	@Override
	public String compute(int numProposition, char charValue)
	{
		if(numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder();
		String firstPartofPattern = PatternGenerator.generateNotAndNotPattern(numProposition, charValue);
		stringBuilder.append(firstPartofPattern);
		
		stringBuilder.append(Symbols.AND + Symbols.OPEN_Parenth);
		
		for (int front = 0; front < numProposition; front++)
		{
			stringBuilder.append(Symbols.OPEN_Parenth);
			for (int i = front; i < numProposition; i++)
			{
				stringBuilder.append(Symbols.NOT + "" + charValue + i);
				if(i < numProposition - 1)
				{
					stringBuilder.append(" " + Symbols.AND);
				}
			}
			
			stringBuilder.append(Symbols.CLOSE_Parenth + Symbols.UNTIL 
					+ Symbols.OPEN_Parenth);
			for (int i = front; i < numProposition; i++)
			{
				String negation = i == front ? "" : "!";
				stringBuilder.append(negation + "" + charValue + i);
				if (front < numProposition - 1)
				{
					stringBuilder.append(" " + Symbols.AND);
				}
			}
			
			if(front < numProposition - 1)
			{
				stringBuilder.append(Symbols.OPEN_Parenth);
			}
		}
		
		for (int i = 0; i <= numProposition; i++)
		{
			stringBuilder.append(Symbols.CLOSE_Parenth);
		}
		
		return stringBuilder.toString();
	}

}
