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
		int count = 0;
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
		
		/*Third part of ConsecutiveE*/
		stringBuilder.append(UNTIL);
		stringBuilder.append(OPEN_P);
		
		int nextP;
		
		for(int i = 0; i < aCount; i++){
			nextP = i + 1;
			stringBuilder.append("p" + i + " " + AND);
			for(;nextP < aCount; nextP++){
				stringBuilder.append(NOT + "p" + nextP + " " + AND);
			}
		
		stringBuilder.append(NEXT);
		stringBuilder.append(OPEN_P);
		
		nextP = i + 1;
		if(aCount - i != 2){
			for(;nextP < aCount; nextP++){
				if(aCount - nextP == 1){
					stringBuilder.append(NOT + "p" + nextP);
				}
				else{
					stringBuilder.append(NOT + "p" + nextP + " " + AND);
				}
			}
				stringBuilder.append(CLOSE_P + " " + NEXT + " " + OPEN_P);
				count += 2;
		}
		else{
			stringBuilder.append(NOT + "p" + nextP + " "
			+ NEXT + " " + "p" + nextP);
			count += 2;
			break;
		}
	}
	
	while(count > 0)
	{
		stringBuilder.append(CLOSE_P);
		count--;
	}
		
		return stringBuilder.toString();
		

	}
}
