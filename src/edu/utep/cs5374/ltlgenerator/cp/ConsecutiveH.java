package edu.utep.cs5374.ltlgenerator.cp;

public class ConsecutiveH  implements CompositePropositionParent{


	private static final String AND = "& ";
	private static final String NOT = "!";
	private static final String NEXT = "X";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";

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
		StringBuilder stringBuilder = new StringBuilder(OPEN_Parenth);
		if(numProposition < 1)
		{
			return "";
		}
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
			ltlFormula.append(CLOSE_Parenth);
			return ltlFormula.toString();
		}
		else{
			return stringBuilder.append(CLOSE_Parenth).toString();
		}
	}
}
