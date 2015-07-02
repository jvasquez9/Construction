package edu.utep.cs5374.ltlgenerator.cp;

public class ConsecutiveC implements CompositePropositionParent {

	private static final String AND = " & ";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";
	private static final String NEXT = "X";

	@Override
	public String compute(int numProposition)
	{
		int openParenthCount = 0;
		
		StringBuilder strLTLFormula = new StringBuilder(OPEN_Parenth);
		openParenthCount++;
		
		if (numProposition <= 0)
		{
			return "";
		}
		
		for(int i = 0; i < numProposition; i++)
		{
			if(i == 0){
				strLTLFormula.append("p" + i + AND + NEXT );
			}
			
			else
			{
				if(numProposition - i > 1)
				{
					strLTLFormula.append(OPEN_Parenth + "p" + i + AND + NEXT );
					openParenthCount++;
				}
				else
				{
					strLTLFormula.append("p" + i );
				}
			}			
		}
		
		// Closing all the open parenthesis
		while(openParenthCount > 0)
		{
			strLTLFormula.append(CLOSE_Parenth);
			openParenthCount--;
		}
		
		return strLTLFormula.toString();
	}

}
