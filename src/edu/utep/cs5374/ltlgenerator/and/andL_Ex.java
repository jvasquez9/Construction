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

		
		// Get the position of the special character i.e. X and U
		int specialCharPos = getFirstSpecialCharPositionFromEnd(leftHandSide);
		
		if(specialCharPos == 0){
			// No special character found; just join with &
			formula = leftHandSide + Symbols.AND + rightHandSide;
		}
		else {
			// Special character found
			String rightPart=rightHandSide;
			String ActualPart = leftHandSide.substring(STARTINDEX,specialCharPos+ONECHARFLAG);
			String lastPart=leftHandSide.substring(specialCharPos+ONECHARFLAG);
				
					
			btr=new StringBuilder(ActualPart);
			
			// Appended &P when identified ')' which means there is a scenario like '()' case
			btr=handleCloseOpen(btr);
			for(int i=0;i<btr.length();i++)
			{
				if(btr.toString().contains("X")&&!btr.toString().contains("U"))
				{
					//This is only to handle &X scenario, only &X exist in the formula
					btr=handleandX(btr);
					break;
				}
				if(!btr.toString().contains("X")&&btr.toString().contains("U"))
				{
					
					//This is only to handle &U scenario, only &U exist in the formula
					
					if(!(btr.toString().contains(")U")))
					{
						if(!btr.toString().endsWith("U"))
					    {
						btr=handlU(btr);
					    }
					}
					
					break;
				}
				else
				{
					//Both X and U exist exist in the formula
					btr=handleandX(btr);
					btr=handlU(btr);
					break;
				}
				
			}
			
					
				
			formula=btr.toString()+lastPart;
			 
		}
		
		return formula;
	}
	
//Handle open close scenario	
public StringBuilder handleCloseOpen(StringBuilder btr)
{
	//If ')' exist just appended &Q
	 
	for (int j = 0; j < btr.length(); j++) {
		
		       
	   
	    if(btr.charAt(j)==')')
	    {
	    	
	    	
	    	btr.insert(j+1, "&Q");
	    	
	    }
	    
	}
	 return btr;
}


/*Handle &X scenario, when &X found in a string split string and then appended &Q at each substrings if they ends with '&'
*/
public StringBuilder handleandX(StringBuilder btr)
{
	
	String checkAndX=btr.toString();
	StringBuilder result = new StringBuilder("");
	String[] andXArray = btr.toString().split("X");
	
	for(int i=0;i<(andXArray.length);i++)
	{
		System.out.println("Array is in X "+andXArray[i]);

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



/*Handle &U scenario, when &U found in a string split string and then appended &Q at each substrings if they doesn't ends with 'Q' or 'X'
*/
public StringBuilder handlU(StringBuilder btr)
{
	String checkAndX=btr.toString();
	StringBuilder result = new StringBuilder("");
	String[] andXArray = btr.toString().split("U");
	
	for(int i=0;i<(andXArray.length);i++)
	{
		System.out.println("Array is in U"+andXArray[i]);

	    if(!andXArray[i].endsWith("Q"))
	    {
	    	 if(!andXArray[i].endsWith("X"))
	 	    {
	    		 
		andXArray[i]=andXArray[i]+"&Q&U";
		}
	    	 else
	    	 {
	    		 return btr;
	    	 }
	 	    }
	}
	
	for(int i=0;i<andXArray.length;i++)
	{
		
		
		result.append(andXArray[i]);
		
		
	}
	
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




