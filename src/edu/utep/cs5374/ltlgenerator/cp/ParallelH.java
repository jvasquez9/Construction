package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class ParallelH implements CompositePropositionParent {
	
	@Override
	public String compute(int numProposition, char charValue)
	{
		if (numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder ltlFormula = new StringBuilder(Symbols.OPEN_Parenth);
		
		for(int i = 0; i < numProposition; i++)
		{
			ltlFormula.append(""+charValue + i);
			
			if(i < numProposition - 1)
			{
				ltlFormula.append(Symbols.AND);
			}
		}
		
		ltlFormula.append(Symbols.CLOSE_Parenth);
		
		return ltlFormula.toString();
	}
}