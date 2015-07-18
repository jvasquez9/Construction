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
	
			
			btr=new StringBuilder(ActualPart);
			String result = btr.toString();
			
					
			btr=handleandX(btr);
			
			btr=handlU(btr);
		
				
			formula=btr.toString()+lastPart;
			 
			//System.out.println("formula"+formula);
		}
		
		return formula;
	}
	
	 //Handle open close scenario	
public StringBuilder handleCloseOpen(StringBuilder btr)
{
	//String formula=btr.toString();
	  int open = 0;
	  int close = 0;
	  int finalPos = 0,posStart=0;
	  
	for (int j = 0; j < btr.length(); j++) {
		
		       
	   
	    if(btr.charAt(j)==')')
	    {
	    	
	    	
	    	btr.insert(j+1, "&Q");
	    	
	    }
	}

	 return btr;
}

//Handle &X scenario	

public StringBuilder handleandX(StringBuilder btr)
{
	
	String checkAndX=btr.toString();
	StringBuilder result = new StringBuilder("");
	String[] andXArray = btr.toString().split("X");
	
	for(int i=0;i<(andXArray.length);i++)
	{
		System.out.println("Array is "+andXArray[i]);

	    if(andXArray[i].endsWith("&"))
	    {
	    	
		andXArray[i]=andXArray[i]+"Q&X";
		}
		
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
	StringBuilder result = new StringBuilder(btr);
	/*String checkAndX=btr.toString();
	StringBuilder result = new StringBuilder("");
	String[] andXArray = btr.toString().split("U");
	
	for(int i=0;i<(andXArray.length);i++)
	{
		System.out.println("Array is "+andXArray[i]);

	    
	    	
		andXArray[i]=andXArray[i]+"U";
		
		
	}
	
	for(int i=0;i<andXArray.length;i++)
	{
		
		
		result.append(andXArray[i]);
		
		
	}*/
	
	return result;
	
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




