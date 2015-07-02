package edu.utep.cs5374.ltlgenerator.cp;

public class ConsecutiveC implements CompositePropositionParent {

	private static final String AND = " & ";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";
	private static final String NEXT = "X";
	
	public String ltlFormulaGenerator(int numP, int countRecursion, StringBuilder ltlFormula){
		if(numP == countRecursion)
		{
			ltlFormula.append("p");
			// appending (countRecursion - 1) 
			// as we are considering p0 instead of p1 when number of prop is 1
			return ltlFormula.append(countRecursion-1).toString(); 
		}
		else {
			ltlFormula.append("p");
			// appending (countRecursion - 1) 
			// as we are considering p0 instead of p1 when number of prop is 1
			ltlFormula.append(countRecursion-1);
			ltlFormula.append(AND);
			ltlFormula.append(NEXT);
			ltlFormula.append(OPEN_Parenth);
			countRecursion++;
			ltlFormulaGenerator(numP, countRecursion, ltlFormula);
			ltlFormula.append(CLOSE_Parenth);
			return ltlFormula.toString();
		}
	}
	
	@Override
	public String compute(int numProposition)
	{
		StringBuilder ltlFormula = new StringBuilder(OPEN_Parenth);
		int recursionCount = 1;
		ltlFormula.append(ltlFormulaGenerator(numProposition, recursionCount, new StringBuilder("")));
		ltlFormula.append(CLOSE_Parenth);
		return ltlFormula.toString();
	}

}
