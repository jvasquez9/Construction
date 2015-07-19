package edu.utep.cs5374.ltlgenerator.and;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AndL extends AndParent {
	private int STARTINDEX = 0;
	private int ONECHARFLAG = 1;
	
	@Override
	public String and(String leftHandSide, String rightHandSide) {
		
		String formula = "";
		// Trim the spaces
		leftHandSide = leftHandSide.replaceAll("\\s+","");

		int length = leftHandSide.length();
		
		// Get the position of the special character i.e. X and U
		int specialCharPos = getFirstSpecialCharPositionFromEnd(leftHandSide);
		
		if(specialCharPos == 0){
			// No special character found; just join with &
			formula = leftHandSide + Symbols.AND + rightHandSide;
		}
		else {
			// Special character found
			String rightPart;
			String middlePart;
			String leftPart = leftHandSide.substring(STARTINDEX,specialCharPos+ONECHARFLAG);
			
			// Extract the middle part 
			int middlePartEndIndex = getMiddlePartEndIndex(specialCharPos+1, leftHandSide);
			if(leftHandSide.charAt(specialCharPos+1) == Symbols.OPEN_Parenth.charAt(STARTINDEX))
			{
				middlePart = leftHandSide.substring(specialCharPos+ONECHARFLAG,middlePartEndIndex+ONECHARFLAG);
				rightPart = leftHandSide.substring(middlePartEndIndex+1,length);
			}
			else {
				middlePart = leftHandSide.substring(specialCharPos+ONECHARFLAG,middlePartEndIndex);
				rightPart = leftHandSide.substring(middlePartEndIndex,length);
			}
			
			// Create the formula by adding And P with the middle part 
			formula = leftPart + Symbols.OPEN_Parenth + middlePart + Symbols.AND + "P" + Symbols.CLOSE_Parenth + rightPart;
		}
		
		return formula;
	}
	
	public int getFirstSpecialCharPositionFromEnd(String leftHandString)
	{
		int indexPos = leftHandString.length()-ONECHARFLAG;
		for(; indexPos >= 0; indexPos--)
		{
			if(leftHandString.charAt(indexPos) == Symbols.NEXT.charAt(STARTINDEX) ||
					leftHandString.charAt(indexPos) == Symbols.UNTIL.charAt(STARTINDEX))
				return indexPos;
		}
		return 0;
	}
	
	public int getMiddlePartEndIndex(int pos, String leftside){
		for(; pos < leftside.length()-ONECHARFLAG; pos++){
			if(leftside.charAt(pos) == Symbols.CLOSE_Parenth.charAt(0)){
				return pos;
			}
		}
		return 0;
	}
}

