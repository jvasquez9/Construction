package edu.utep.cs5374.ltlgenerator.cp;

public class EventualH implements CompositePropositionParent {

	private static final String OR = " | ";
	private static final String AND = "& ";
	private static final String NOT = "!";
	private static final String UNTIL = "U";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";
	private static final String NEXT = "X";
	
	@Override
	public String compute(int numProposition) {
		int count = 0;
		
		if (numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder(OPEN_Parenth);
	
		int nextP;
		
		for(int i = 0; i < numProposition; i++){
			nextP = i + 1;
			stringBuilder.append("p" + i + " " + AND);
			for(;nextP < numProposition; nextP++){
				stringBuilder.append(NOT + "p" + nextP + " " + AND);
			}
			stringBuilder.append(OPEN_Parenth + OPEN_Parenth);
			nextP = i + 1;
			if(numProposition - i != 2){
				for(;nextP < numProposition; nextP++){
					if(numProposition - nextP == 1){
						stringBuilder.append(NOT + "p" + nextP);
					}
					else{
						stringBuilder.append(NOT + "p" + nextP + " " + AND);
					}
				}
					stringBuilder.append(CLOSE_Parenth + " " + UNTIL + " " + OPEN_Parenth);
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
			stringBuilder.append(CLOSE_Parenth);
			count--;
		}
		
		stringBuilder.append(CLOSE_Parenth);
		
		return stringBuilder.toString();
	}
}
