/**
 * 
 */
package edu.utep.cs5374.ltlgenerator.cp;

/**
 * @author Hima
 *
 */
public class EventualC extends CompositePropositionParent {

	/**
	 * 
	 */
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
	
	public static String compute(int aCount)
	{
		int count=0;
		if (aCount <= 0)
		{
			return "";
		}
		
		StringBuilder stringBuilder = new StringBuilder(OPEN_P);
		for(int i=0;i< aCount;i++)
		{
			if(i == 0){
				stringBuilder.append(" p " + i + AND + NEXT );
			}
			
			else
			{
						
			if(aCount - i > 1)
			{
				stringBuilder.append(OPEN_P + NOT + " p" + i + UNTIL + OPEN_P + " p" + i + AND + NEXT   );
				count=count+2;
			}
				
			else
			{
				stringBuilder.append(OPEN_P + NOT + " p" + i + UNTIL + " p " + i + CLOSE_P );
				
			}
			
			}			
		}
		
		while(count>0)
		{
			stringBuilder.append(CLOSE_P);
			count--;
		}
		stringBuilder.append(CLOSE_P);
		System.out.println(stringBuilder);
		
		return stringBuilder.toString();
	}
}