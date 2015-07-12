package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class ParallelC implements CompositePropositionParent {
	
	@Override
	public String compute(int numProposition)
	{
		if (numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder(Symbols.OPEN_Parenth);
		
		for(int i=0;i< numProposition;i++)
		{
			stringBuilder.append(" p" + i + " ");
			
			if(i < numProposition - 1)
			{
				stringBuilder.append(Symbols.AND);
			}
		}
		
		stringBuilder.append(Symbols.CLOSE_Parenth);
		
		return stringBuilder.toString();
	}
}
