package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class ParallelE implements CompositePropositionParent  {

	@Override
	public String compute(int numProposition)
	{
		if (numProposition <= 0)
		{
			return "";
		}

		/*First half of ParallelE*/
		StringBuilder stringBuilder = new StringBuilder(Symbols.OPEN_Parenth);
		for(int i=0;i< numProposition;i++)
		{
			stringBuilder.append(Symbols.NOT + " p" + i + " ");
			if(i < numProposition - 1)
			{
				stringBuilder.append(Symbols.AND);
			}

		}
		stringBuilder.append(Symbols.CLOSE_Parenth);
		/*First half done*/


		/**Second half of ParallelE**/
		stringBuilder.append(Symbols.AND);
		stringBuilder.append(Symbols.OPEN_Parenth);
		stringBuilder.append(Symbols.OPEN_Parenth);
		for(int i = 0; i < numProposition; i++){
			stringBuilder.append(Symbols.NOT + " p" + i + " ");
			if(i < numProposition - 1)
			{
				stringBuilder.append(Symbols.AND);
			}
		}
		stringBuilder.append(Symbols.CLOSE_Parenth);
		/*Second half done*/


		/*Third part of parallelE*/
		stringBuilder.append(Symbols.UNTIL);
		stringBuilder.append(Symbols.OPEN_Parenth);
		for(int i = 0; i < numProposition; i++){
			stringBuilder.append(" p" + i + " ");
			if(i < numProposition - 1)
			{
				stringBuilder.append(Symbols.AND);
			}
		}
		stringBuilder.append(Symbols.CLOSE_Parenth);
		stringBuilder.append(Symbols.CLOSE_Parenth);
		/*Third part done*/

		return stringBuilder.toString();
	}
}
