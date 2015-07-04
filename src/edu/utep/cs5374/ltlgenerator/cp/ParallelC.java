package edu.utep.cs5374.ltlgenerator.cp;

public class ParallelC implements CompositePropositionParent {
	
	private static final String AND = " & ";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";
	
	@Override
	public String compute(int numProposition)
	{
		if (numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder(OPEN_Parenth);
		
		for(int i=0;i< numProposition;i++)
		{
			stringBuilder.append(" p" + i + " ");
			
			if(i < numProposition - 1)
			{
				stringBuilder.append(AND);
			}
		}
		
		stringBuilder.append(CLOSE_Parenth);
		
		return stringBuilder.toString();
	}
}
