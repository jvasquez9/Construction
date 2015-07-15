package edu.utep.cs5374.ltlgenerator.and;

import java.util.ArrayList;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class andL_Ex implements AndParent {
	private int STARTINDEX = 0;
	private int ONECHARFLAG = 1;
	StringBuilder btr;
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
			String ActualPart = leftHandSide.substring(STARTINDEX,specialCharPos+ONECHARFLAG);
			String lastPart=leftHandSide.substring(specialCharPos+ONECHARFLAG);
			//System.out.println("left part to append"+ActualPart);
			//System.out.println("fnal right part to append"+lastPart);
			//(!p0&!p1&!p2)&((!p0&!p1&!p2)U(p0&!p1&!p2&X(p1&!p2&X
			//((!p0&!p1&!p2)&P)&(((!p0&!p1&!p2) &P )U(p0&!p1&!p2 &P&X(p1&!p2 &P &X
			//last part (p2))))
		
			
			btr=new StringBuilder(ActualPart);
           
			//Handle open close scenario like (!p0&!p1&!p2)
			btr=handleCloseOpen(btr);
			//System.out.println("Actual Part before X : "+btr.toString());
			
			//Handle &X scenario like (p0&!p1&!p2 &P&X
			StringBuilder result = new StringBuilder("");
			result=handleandX(btr);
	
			////Handle U scenario
				
			formula=result.toString()+lastPart;
			 
			//System.out.println("formula"+formula);
		}
		
		return formula;
	}
	
	 //Handle open close scenario	
public StringBuilder handleCloseOpen(StringBuilder btr)
{
	

	for (int i = 0; i < btr.length(); i++) {
	   
	    if(btr.charAt(i)==')')
	    {
	    	
	    	
	    	btr.insert(i+1, "&Q");
	    	
	    }
	    
	   	}
	 return btr;
}

//Handle &X scenario	

public StringBuilder handleandX(StringBuilder btr)
{
	
	StringBuilder result=new StringBuilder("");
	String[] andXArray = btr.toString().split("&X|\\n");
	
	for(int i=0;i<andXArray.length;i++)
	{
		
		
		
		
		andXArray[i]=andXArray[i]+"&Q&X";
		
	}
	
	for(int i=0;i<andXArray.length;i++)
	{
		
		
		result.append(andXArray[i]);
		
		
	}
	
	return result;
}

// Handle U scenario	
public StringBuilder handlU(StringBuilder btr)
{
	return btr;
	
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
	

}




