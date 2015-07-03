package edu.utep.cs5374.ltlgenerator.cp;

public class EventualH implements CompositePropositionParent {

	private static final String OR = " | ";
	private static final String AND = "& ";
	private static final String NOT = "!";
	private static final String UNTIL = "U";
	private static final String OPEN_P = "(";
	private static final String CLOSE_P = ")";
	private static final String NEXT = "X";
	
	@Override
	public String compute(int aCount) {
		int count = 0;
		
		if (aCount <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder(OPEN_P);
	
		int nextP;
		
		for(int i = 0; i < aCount; i++){
			nextP = i + 1;
			stringBuilder.append("p" + i + " " + AND);
			for(;nextP < aCount; nextP++){
				stringBuilder.append(NOT + "p" + nextP + " " + AND);
			}
			stringBuilder.append(OPEN_P + OPEN_P);
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
					stringBuilder.append(CLOSE_P + " " + UNTIL + " " + OPEN_P);
					count += 2;
			}
			else{
				stringBuilder.append(NOT + "p" + nextP + " "
				+ UNTIL + " " + "p" + nextP);
				count += 2;
				break;
			}
		}
		
		while(count > 0)
		{
			stringBuilder.append(CLOSE_P);
			count--;
		}
		
		stringBuilder.append(CLOSE_P);
		
		return stringBuilder.toString();
	}
}
