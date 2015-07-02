package edu.utep.cs5374.ltlgenerator.cp;

public class AtleastOneH implements CompositePropositionParent {

	private static final String OR = " | ";
	
	@Override
	public String compute(int aCount)
	{
		if (aCount <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder("(");
		
		for(int i=0;i< aCount;i++)
		{
			stringBuilder.append("p" + i + " ");
			
			if(i < aCount - 1)
			{
				stringBuilder.append(OR);
			}
		}
		
		stringBuilder.append(")");
		
		return stringBuilder.toString();
	}

}
