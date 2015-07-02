package edu.utep.cs5374.ltlgenerator.cp;

public class ConsecutiveC implements CompositePropositionParent {

	private static final String AND = " & ";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";
	private static final String NEXT = "X";
	
	public String ltlFormulaGenerator(int numP, int countRecursion, StringBuilder str){
		if(numP == countRecursion)
		{
			str.append("p");
			// appending (countRecursion - 1) 
			// as we are considering p0 instead of p1 when number of prop is 1
			return str.append(countRecursion-1).toString(); 
		}
		else {
			str.append("p");
			// appending (countRecursion - 1) 
			// as we are considering p0 instead of p1 when number of prop is 1
			str.append(countRecursion-1);
			str.append(AND);
			str.append(NEXT);
			str.append(OPEN_Parenth);
			countRecursion++;
			ltlFormulaGenerator(numP, countRecursion, str);
			str.append(CLOSE_Parenth);
			return str.toString();
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
