package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AtleastOneC implements CompositePropositionParent {
	
	@Override
	public String compute(int numProposition)
	{
		if (numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder("(");
		
		for(int i=0;i< numProposition;i++)
		{
			stringBuilder.append(" p" + i + " ");
			
			if(i < numProposition - 1)
			{
				stringBuilder.append(Symbols.OR);
			}
		}
		
		stringBuilder.append(")");
		
		return stringBuilder.toString();
	}

}
