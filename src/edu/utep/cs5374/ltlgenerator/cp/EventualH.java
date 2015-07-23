package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class EventualH implements CompositePropositionParent {
	
	@Override
	public String compute(int numProposition, char charValue) {
		int count = 0;
		
		if (numProposition <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder(Symbols.OPEN_Parenth);
	
		int nextP;
		
		for(int i = 0; i < numProposition; i++){
			nextP = i + 1;
			stringBuilder.append(""+charValue + i + " " + Symbols.AND);
			for(;nextP < numProposition; nextP++){
				stringBuilder.append(Symbols.NOT + ""+ charValue + nextP + " " + Symbols.AND);
			}
			stringBuilder.append(Symbols.OPEN_Parenth + Symbols.OPEN_Parenth);
			nextP = i + 1;
			if(numProposition - i != 2){
				for(;nextP < numProposition; nextP++){
					if(numProposition - nextP == 1){
						stringBuilder.append(Symbols.NOT + "" + charValue + nextP);
					}
					else{
						stringBuilder.append(Symbols.NOT + "" + charValue + nextP 
								+ " " + Symbols.AND);
					}
				}
					stringBuilder.append(Symbols.CLOSE_Parenth + " " 
							+ Symbols.UNTIL + " " + Symbols.OPEN_Parenth);
					count += 2;
			}
			else{
				stringBuilder.append(Symbols.NOT + "" + charValue + nextP + " "
						+ Symbols.UNTIL + " " + charValue + nextP);
				count += 2;
				break;
			}
		}
		
		while(count > 0)
		{
			stringBuilder.append(Symbols.CLOSE_Parenth);
			count--;
		}
		
		stringBuilder.append(Symbols.CLOSE_Parenth);
		
		return stringBuilder.toString();
	}
}
