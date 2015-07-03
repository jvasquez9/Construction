package edu.utep.cs5374.ltlgenerator.cp;

public class AtleastOneH implements CompositePropositionParent {

	private static final String OR = " | ";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";
	
	@Override
	public String compute(int numProposition)
	{
		if (numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder ltlFormula = new StringBuilder(OPEN_Parenth);
		
		for(int i=0;i< numProposition;i++)
		{
			ltlFormula.append("p" + i + " ");
			
			if(i < numProposition - 1)
			{
				ltlFormula.append(OR);
			}
		}
		
		ltlFormula.append(CLOSE_Parenth);
		
		return ltlFormula.toString();
	}

}
