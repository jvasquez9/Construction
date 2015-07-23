package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class EventualC implements CompositePropositionParent {

	@Override
	public String compute(int numProposition, char charValue)
	{
		int openParenthesesCount = 0;
		
		if (numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder ltlFormula = new StringBuilder(Symbols.OPEN_Parenth);
		
		for(int i=0;i< numProposition;i++)
		{
			//stringBuilder.append(" p " + i + AND + NEXT );
			if(numProposition==1)
			{
				ltlFormula.append(" " + charValue +" " + i );
			}
			else
			{
				if(i == 0)
				{
					ltlFormula.append(" "+ charValue +" " + i + Symbols.AND + Symbols.NEXT );
				}
				else
				{		
					if(numProposition - i > 1)
					{
						ltlFormula.append(Symbols.OPEN_Parenth + Symbols.NOT 
								+ " " + charValue + i + Symbols.UNTIL + Symbols.OPEN_Parenth 
								+ " " + charValue + i + Symbols.AND + Symbols.NEXT   );
						openParenthesesCount=openParenthesesCount+2;
					}
					else
					{
						ltlFormula.append(Symbols.OPEN_Parenth + Symbols.NOT 
								+ " " + charValue + i + Symbols.UNTIL + " p " + i 
								+ Symbols.CLOSE_Parenth );
					}
				}			
			}
		}
		
		while(openParenthesesCount > 0)
		{
			ltlFormula.append(Symbols.CLOSE_Parenth);
			openParenthesesCount--;
		}
		
		ltlFormula.append(Symbols.CLOSE_Parenth);
		System.out.println(ltlFormula);
		
		return ltlFormula.toString();
	}

}