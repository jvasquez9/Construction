package edu.utep.cs5374.ltlgenerator.cp;

public class ConsecutiveE implements CompositePropositionParent {
	
	
	private static final String AND = "& ";
	private static final String NOT = "!";
	private static final String UNTIL = "U";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";
	private static final String NEXT = "X";

	
	public String ltlFormulaGenerator(int numP, int countRecursion, StringBuilder ltlFormula){
		for(int i=countRecursion;i< numP;i++)
		{
			if(i == countRecursion){
				ltlFormula.append(" p" + i + " ");
			}
			else{
				ltlFormula.append(AND + NOT + " p" + i + " ");
			}

		}
		if(numP-countRecursion > 1){

			ltlFormula.append(AND);
			ltlFormula.append(NEXT);
			ltlFormula.append(OPEN_Parenth);
			countRecursion++;
			ltlFormulaGenerator(numP, countRecursion, ltlFormula);
			ltlFormula.append(CLOSE_Parenth);
			return ltlFormula.toString();
		}
		else{
			return ltlFormula.append(CLOSE_Parenth).toString();
		}
	}
	
	@Override
	public String compute(int numProposition) 
	{
		if (numProposition <= 0)
		{
			return "";
		}

		/*First part of ConsecutiveE*/
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
		/*First part done*/
		
		
		/**Second part of ConsecutiveE**/
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
		/*Second part done*/
		
		/*Third part of ConsecutiveE*/
		stringBuilder.append(UNTIL);
		stringBuilder.append(OPEN_Parenth);
		
		
		for(int i=0;i< numProposition;i++)
		{
			if(i == 0){
				stringBuilder.append(" p" + i + " ");
			}
			else{
				stringBuilder.append(AND + NOT + " p" + i + " ");
			}

		}
		if(numProposition > 1){
			StringBuilder ltlFormula = new StringBuilder(stringBuilder);
			ltlFormula.append(AND);
			ltlFormula.append(NEXT);
			ltlFormula.append(OPEN_Parenth);
			int recursionCount = 1;
			ltlFormula.append(ltlFormulaGenerator(numProposition, recursionCount, new StringBuilder("")));
			ltlFormula.append(CLOSE_Parenth + CLOSE_Parenth);
			return ltlFormula.toString();
		}
		else{
			return stringBuilder.append(CLOSE_Parenth + CLOSE_Parenth).toString();
		}
	}
}
