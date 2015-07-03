package edu.utep.cs5374.ltlgenerator.cp;

public class EventualC implements CompositePropositionParent {

	private static final String OR = " | ";
	private static final String AND = "& ";
	private static final String NOT = "!";
	private static final String UNTIL = "U";
	private static final String OPEN_P = "(";
	private static final String CLOSE_P = ")";
	private static final String NEXT = "X";

	@Override
	public String compute(int numProposition)
	{
		int openParenthesesCount=0;
		if (numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder ltlFormula = new StringBuilder(OPEN_P);
		for(int i=0;i< numProposition;i++)
		{
			//stringBuilder.append(" p " + i + AND + NEXT );
			if(numProposition==1)
			{
				ltlFormula.append(" p " + i );
			}
			
			else
			{
			if(i == 0){
				ltlFormula.append(" p " + i + AND + NEXT );
			}
			
			else
			{
						
			if(numProposition - i > 1)
			{
				ltlFormula.append(OPEN_P + NOT + " p" + i + UNTIL + OPEN_P + " p" + i + AND + NEXT   );
				openParenthesesCount=openParenthesesCount+2;
			}
				
			else
			{
				ltlFormula.append(OPEN_P + NOT + " p" + i + UNTIL + " p " + i + CLOSE_P );
				
			}
			
			}			
		}
		}
		while(openParenthesesCount>0)
		{
			ltlFormula.append(CLOSE_P);
			openParenthesesCount--;
		}
		
		ltlFormula.append(CLOSE_P);
		System.out.println(ltlFormula);
		
		return ltlFormula.toString();
	}

}