package edu.utep.cs5374.ltlgenerator.cp;

public class ConsecutiveE implements CompositePropositionParent {
	
	
	private static final String OR = " | ";
	private static final String AND = "& ";
	private static final String NOT = "!";
	private static final String UNTIL = "U";
	private static final String OPEN_P = "(";
	private static final String CLOSE_P = ")";
	private static final String NEXT = "X";

	@Override
	public String compute(int aCount) 
	{
		if (aCount <= 0)
		{
			return "";
		}

		/*First part of ConsecutiveE*/
		StringBuilder stringBuilder = new StringBuilder(OPEN_P);
		for(int i=0;i< aCount;i++)
		{
			stringBuilder.append(NOT + " p" + i + " ");
			if(i < aCount - 1)
			{
				stringBuilder.append(AND);
			}

		}
		stringBuilder.append(CLOSE_P);
		/*First part done*/
		
		
		/**Second part of ConsecutiveE**/
		stringBuilder.append(AND);
		stringBuilder.append(OPEN_P);
		stringBuilder.append(OPEN_P);
		for(int i = 0; i < aCount; i++){
			stringBuilder.append(NOT + " p" + i + " ");
			if(i < aCount - 1)
			{
				stringBuilder.append(AND);
			}
		}
		stringBuilder.append(CLOSE_P);
		/*Second part done*/
		
		return stringBuilder.toString();
		

	}
}
