package edu.utep.cs5374.ltlgenerator.cp;

public class ConsecutiveE implements CompositePropositionParent {
	
	
	private static final String OR = " | ";
	private static final String AND = "& ";
	private static final String NOT = "!";
	private static final String UNTIL = "U";
	private static final String OPEN_P = "(";
	private static final String CLOSE_P = ")";
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
			ltlFormula.append(OPEN_P);
			countRecursion++;
			ltlFormulaGenerator(numP, countRecursion, ltlFormula);
			ltlFormula.append(CLOSE_P);
			return ltlFormula.toString();
		}
		else{
			return ltlFormula.append(CLOSE_P).toString();
		}
	}

	@Override
	public String compute(int aCount) 
	{
		if (aCount <= 0)
		{
			return "";
		}

		/*First part of ConsecutiveE*/
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
		/*First part done*/
		
		
		/**Second part of ConsecutiveE**/
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
		/*Second part done*/
		
		/*Third part of ConsecutiveE*/
		stringBuilder.append(UNTIL);
		stringBuilder.append(OPEN_P);
		
		int nextP;
		
		for(int i = 0; i < aCount; i++){
			nextP = i + 1;
			stringBuilder.append("p" + i + " " + AND);
			for(;nextP < aCount; nextP++){
				stringBuilder.append(NOT + "p" + nextP + " " + AND);
			}
		}
		
		if(aCount > 1){
			stringBuilder.append(NEXT);
			stringBuilder.append(OPEN_P);
			StringBuilder ltlFormula = new StringBuilder(stringBuilder);
			ltlFormula.append(AND);
			ltlFormula.append(NEXT);
			ltlFormula.append(OPEN_P);
			int recursionCount = 1;
			ltlFormula.append(ltlFormulaGenerator(aCount, recursionCount, new StringBuilder("")));
			ltlFormula.append(CLOSE_P);
			return ltlFormula.toString();
		}
		else{
			return stringBuilder.append(CLOSE_P).toString();
		}
		

		

	}
}
