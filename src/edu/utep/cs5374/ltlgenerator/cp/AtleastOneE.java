package edu.utep.cs5374.ltlgenerator.cp;

public class AtleastOneE implements CompositePropositionParent {

	private static final String OR = " | ";
	private static final String AND = "& ";
	private static final String NOT = "!";
	private static final String UNTIL = "U";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";

	@Override
	public String compute(int numProposition)
	{
		if (numProposition <= 0)
		{
			return "";
		}

		/*First half of AtleastOneE*/
		StringBuilder stringBuilder = new StringBuilder(OPEN_Parenth);
		for(int i=0;i< numProposition;i++)
		{
			stringBuilder.append(NOT + " p" + i + " ");
			if(i < numProposition - 1)
			{
				stringBuilder.append(AND);
			}

		}
		stringBuilder.append(CLOSE_Parenth);
		/*First half done*/


		/**Second half of AtleastOneE**/
		stringBuilder.append(AND);
		stringBuilder.append(OPEN_Parenth);
		stringBuilder.append(OPEN_Parenth);
		for(int i = 0; i < numProposition; i++){
			stringBuilder.append(NOT + " p" + i + " ");
			if(i < numProposition - 1)
			{
				stringBuilder.append(AND);
			}
		}
		stringBuilder.append(CLOSE_Parenth);
		/*Second half done*/


		/*Third part of AtleastOneE*/
		stringBuilder.append(UNTIL);
		stringBuilder.append(OPEN_Parenth);
		for(int i = 0; i < numProposition; i++){
			stringBuilder.append(" p" + i + " ");
			if(i < numProposition - 1)
			{
				stringBuilder.append(OR);
			}
		}
		stringBuilder.append(CLOSE_Parenth);
		stringBuilder.append(CLOSE_Parenth);
		/*Third part done*/

		return stringBuilder.toString();
	}
}
