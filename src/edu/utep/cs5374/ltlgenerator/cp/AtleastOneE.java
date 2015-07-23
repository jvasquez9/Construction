package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AtleastOneE implements CompositePropositionParent {

	@Override
	public String compute(int numProposition, char charValue)
	{
		if (numProposition <= 0)
		{
			return "";
		}

		/*First half of AtleastOneE*/
		StringBuilder stringBuilder = new StringBuilder(Symbols.OPEN_Parenth);
		for(int i=0;i< numProposition;i++)
		{
			stringBuilder.append(Symbols.NOT + " " + charValue + i + " ");
			if(i < numProposition - 1)
			{
				stringBuilder.append(Symbols.AND);
			}

		}
		stringBuilder.append(Symbols.CLOSE_Parenth);
		/*First half done*/


		/**Second half of AtleastOneE**/
		stringBuilder.append(Symbols.AND);
		stringBuilder.append(Symbols.OPEN_Parenth);
		stringBuilder.append(Symbols.OPEN_Parenth);
		for(int i = 0; i < numProposition; i++){
			stringBuilder.append(Symbols.NOT + " " + charValue + i + " ");
			if(i < numProposition - 1)
			{
				stringBuilder.append(Symbols.AND);
			}
		}
		stringBuilder.append(Symbols.CLOSE_Parenth);
		/*Second half done*/


		/*Third part of AtleastOneE*/
		stringBuilder.append(Symbols.UNTIL);
		stringBuilder.append(Symbols.OPEN_Parenth);
		for(int i = 0; i < numProposition; i++){
			stringBuilder.append(" " + charValue + i + " ");
			if(i < numProposition - 1)
			{
				stringBuilder.append(Symbols.OR);
			}
		}
		stringBuilder.append(Symbols.CLOSE_Parenth);
		stringBuilder.append(Symbols.CLOSE_Parenth);
		/*Third part done*/

		return stringBuilder.toString();
	}
}
