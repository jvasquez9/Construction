/**
 * 
 */
package edu.utep.cs5374.ltlgenerator.cp;

/**
 * @author John
 *
 */
public class ParallelE extends CompositePropositionParent {

	public class ParallelE  {

		private static final String OR = " | ";
		private static final String AND = "& ";
		private static final String NOT = "!";
		private static final String UNTIL = "U";
		private static final String OPEN_P = "(";
		private static final String CLOSE_P = ")";


		public static String compute(int aCount)
		{
			if (aCount <= 0)
			{
				return "";
			}

			/*First half of ParallelE*/
			StringBuilder stringBuilder = new StringBuilder(OPEN_P);
			for(int i=0;i< aCount;i++)
			{
				stringBuilder.append(NOT + " p" + i + " ");
				if(i < aCount - 1)
				{
					stringBuilder.append(AND);
				}

			}
			stringBuilder.append(CLOSE_P);
			/*First half done*/


			/**Second half of ParallelE**/
			stringBuilder.append(AND);
			stringBuilder.append(OPEN_P);
			stringBuilder.append(OPEN_P);
			for(int i = 0; i < aCount; i++){
				stringBuilder.append(NOT + " p" + i + " ");
				if(i < aCount - 1)
				{
					stringBuilder.append(AND);
				}
			}
			stringBuilder.append(CLOSE_P);
			/*Second half done*/


			/*Third part of parallelE*/
			stringBuilder.append(UNTIL);
			stringBuilder.append(OPEN_P);
			for(int i = 0; i < aCount; i++){
				stringBuilder.append(" p" + i + " ");
				if(i < aCount - 1)
				{
					stringBuilder.append(AND);
				}
			}
			stringBuilder.append(CLOSE_P);
			stringBuilder.append(CLOSE_P);
			/*Third part done*/

			return stringBuilder.toString();
		}
	}
