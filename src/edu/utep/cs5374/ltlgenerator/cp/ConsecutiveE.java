package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class ConsecutiveE implements CompositePropositionParent {
	
	private String ltlFormulaGenerator(int numP, char charValue, int countRecursion, StringBuilder ltlFormula){
		for(int i=countRecursion;i< numP;i++)
		{
			if(i == countRecursion){
				ltlFormula.append(" " + charValue + i + " ");
			}
			else{
				ltlFormula.append(Symbols.AND + Symbols.NOT + " " + charValue + i + " ");
			}

		}
		if(numP-countRecursion > 1){

			ltlFormula.append(Symbols.AND);
			ltlFormula.append(Symbols.NEXT);
			ltlFormula.append(Symbols.OPEN_Parenth);
			countRecursion++;
			ltlFormulaGenerator(numP, charValue, countRecursion, ltlFormula);
			ltlFormula.append(Symbols.CLOSE_Parenth);
			return ltlFormula.toString();
		}
		else{
			return ltlFormula.append(Symbols.CLOSE_Parenth).toString();
		}
	}
	
	@Override
	public String compute(int numProposition, char charValue) 
	{
		if (numProposition <= 0)
		{
			return "";
		}

		/*First part of ConsecutiveE*/
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
		/*First part done*/
		
		
		/**Second part of ConsecutiveE**/
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
		/*Second part done*/
		
		/*Third part of ConsecutiveE*/
		stringBuilder.append(Symbols.UNTIL);
		stringBuilder.append(Symbols.OPEN_Parenth);
		
		
		for(int i=0;i< numProposition;i++){
			if(i == 0){
				stringBuilder.append(" " + charValue + i + " ");
			}
			else{
				stringBuilder.append(Symbols.AND + Symbols.NOT + " " + charValue + i + " ");
			}

		}
		if(numProposition > 1){
			StringBuilder ltlFormula = new StringBuilder(stringBuilder);
			ltlFormula.append(Symbols.AND);
			ltlFormula.append(Symbols.NEXT);
			ltlFormula.append(Symbols.OPEN_Parenth);
			int recursionCount = 1;
			ltlFormula.append(ltlFormulaGenerator(numProposition, charValue, recursionCount, new StringBuilder("")));
			ltlFormula.append(Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth);
			return ltlFormula.toString();
		}
		else{
			return stringBuilder.append(Symbols.CLOSE_Parenth 
					+ Symbols.CLOSE_Parenth).toString();
		}
	}
}
