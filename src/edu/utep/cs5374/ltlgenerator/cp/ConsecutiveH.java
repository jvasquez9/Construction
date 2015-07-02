/**
 * 
 */
package edu.utep.cs5374.ltlgenerator.cp;

/**
 * @author John
 *
 */
public class ConsecutiveH  {
	
	private static final String OR = " | ";
	private static final String AND = "& ";
	private static final String NOT = "!";
	private static final String UNTIL = "U";
	private static final String OPEN_P = "(";
	private static final String CLOSE_P = ")";
	private static final String NEXT = "X";

	/**
	 * 
	 */
	public ConsecutiveH() {
		 String OnePropFormula = "([p1]&![p2]&X(p2))";
	     String TwoPropFormula = "(![p1]&![p2]&![p3]&X(p2&![p3]&X(p3)))";
	     String ThreePropFormula = "(![p1]&![p2]&![p3]&X(p2&![p3]&X(p3)))";
	  }

	public static String compute(int aCount)
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
		
		
		stringBuilder.append(OPEN_P);
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
		stringBuilder.append(CLOSE_P);
		
		return stringBuilder.toString();
	}
}
