package edu.utep.cs5374.ltlgenerator.cp;

public class ConsecutiveH  implements CompositePropositionParent{
	
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
		
		StringBuilder stringBuilder = new StringBuilder(OPEN_P);
		for(int i=0;i< aCount;i++)
		{
			if(i == 0){
				stringBuilder.append(" p" + i + " ");
			}
			else{
				stringBuilder.append(AND + NOT + " p" + i + " ");
			}
			
			if(aCount - i == 1)
			{
				stringBuilder.append(NEXT);
			}
		}
		/*first part done*/
		
		
		/*second part*/
		stringBuilder.append(OPEN_P);
		for(int i=1;i< aCount;i++)
		{
			if(i == 1){
				stringBuilder.append(" p" + i + " ");
			}
			else{
				stringBuilder.append(AND + NOT + " p" + i + " ");
			}
			
			if(aCount - i == 1)
			{
				stringBuilder.append(CLOSE_P);
			}
			
		}
		stringBuilder.append(NEXT);
		
		
		stringBuilder.append(OPEN_P);
		for(int i=2;i< aCount;i++)
		{
			if(i == 2){
				stringBuilder.append(" p" + i + " ");
			}
			else{
				stringBuilder.append(AND + NOT + " p" + i + " ");
			}
			
			if(aCount - i == 1)
			{
				stringBuilder.append(CLOSE_P);
			}
			
		}
		
		stringBuilder.append(CLOSE_P);
		
		return stringBuilder.toString();
	}
}
