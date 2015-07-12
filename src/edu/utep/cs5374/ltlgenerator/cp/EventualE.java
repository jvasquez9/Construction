package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class EventualE implements CompositePropositionParent {
	
	@Override
	public String compute(int numProposition)
	{
		if(numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder(Symbols.OPEN_Parenth);
		
		for (int i = 0; i < numProposition; i++)
		{
			stringBuilder.append(Symbols.NOT + "p" + i);
			
			if(i < numProposition - 1)
			{
				stringBuilder.append(" " + Symbols.AND);
			}
		}
		stringBuilder.append(Symbols.CLOSE_Parenth + Symbols.AND 
				+ Symbols.OPEN_Parenth);
		
		for (int front = 0; front < numProposition; front++)
		{
			stringBuilder.append(Symbols.OPEN_Parenth);
			for (int i = front; i < numProposition; i++)
			{
				stringBuilder.append(Symbols.NOT + "p" + i);
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
				stringBuilder.append(negation + "p" + i);
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
