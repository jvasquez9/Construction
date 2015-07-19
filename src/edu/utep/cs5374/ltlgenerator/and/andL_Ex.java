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
			String rightPart=rightHandSide;
			String middlePart;
			String ActualPart = leftHandSide.substring(STARTINDEX,specialCharPos+ONECHARFLAG);
			String lastPart=leftHandSide.substring(specialCharPos+ONECHARFLAG);
		
			System.out.println("left part to append"+ActualPart);
			//System.out.println("fnal right part to append"+lastPart);
					
			btr=new StringBuilder(ActualPart);
				
			String result = btr.toString();
			btr=handleCloseOpen(btr);
			for(int i=0;i<btr.length();i++)
			{
				if(btr.toString().contains("X")&&!btr.toString().contains("U"))
				{
					//System.out.println("trying for only one X"+!btr.toString().contains("U"));
					btr=handleandX(btr);
					break;
				}
				if(!btr.toString().contains("X")&&btr.toString().contains("U"))
				{
					//System.out.println("trying for only one U"+!btr.toString().contains("X")+"\t btr"+btr.toString());
					
					if(!(btr.toString().contains(")U")))
					{
						//System.out.println("not )U together"+!btr.toString().contains(")U"));
						if(!btr.toString().endsWith("U"))
					    {
						btr=handlU(btr);
					    }
					}
					
					break;
				}
				else
				{
					System.out.println("Both X and U exist");
					break;
				}
				
			}
			
			
		
				
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
	System.out.println("After handing close paranthesis scenario"+btr.toString());
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
	System.out.println("After handing &X scenario"+btr.toString());
	return result;
}



// Handle U scenario	
public StringBuilder handlU(StringBuilder btr)
{
	String checkAndX=btr.toString();
	StringBuilder result = new StringBuilder("");
	String[] andXArray = btr.toString().split("U");
	
	for(int i=0;i<(andXArray.length);i++)
	{
		System.out.println("Array is "+andXArray[i]);

	    if(!andXArray[i].endsWith("Q"))
	    {
	    	
		andXArray[i]=andXArray[i]+"&Q&U";
		}
		
	}
	
	for(int i=0;i<andXArray.length;i++)
	{
		
		
		result.append(andXArray[i]);
		
		
	}
	System.out.println("After handing U scenario"+result);
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




