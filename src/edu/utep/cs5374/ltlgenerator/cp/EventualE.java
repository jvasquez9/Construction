package edu.utep.cs5374.ltlgenerator.cp;

public class EventualE implements CompositePropositionParent {

	private static final String AND = "& ";
	private static final String NOT = "!";
	private static final String NEXT = "X";
	private static final String UNTIL = "U";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";
	
	@Override
	public String compute(int numProposition)
	{
		if(numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder(OPEN_Parenth);
		
		for (int i = 0; i < numProposition; i++)
		{
			stringBuilder.append(NOT + "p" + i);
			
			if(i < numProposition - 1)
			{
				stringBuilder.append(" " + AND);
			}
		}
		stringBuilder.append(CLOSE_Parenth + AND + OPEN_Parenth);
		
		for (int front = 0; front < numProposition; front++)
		{
			stringBuilder.append(OPEN_Parenth);
			for (int i = front; i < numProposition; i++)
			{
				stringBuilder.append(NOT + "p" + i);
				if(i < numProposition - 1)
				{
					stringBuilder.append(" " + AND);
				}
			}
			
			stringBuilder.append(CLOSE_Parenth + UNTIL + OPEN_Parenth);
			for (int i = front; i < numProposition; i++)
			{
				String negation = i == front ? "" : "!";
				stringBuilder.append(negation + "p" + i);
				if (front < numProposition - 1)
				{
					stringBuilder.append(" " + AND);
				}
			}
			
			if(front < numProposition - 1)
			{
				stringBuilder.append(OPEN_Parenth);
			}
		}
		
		for (int i = 0; i <= numProposition; i++)
		{
			stringBuilder.append(CLOSE_Parenth);
		}
		
		return stringBuilder.toString();
	}

}
