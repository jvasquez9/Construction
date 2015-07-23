package edu.utep.cs5374.ltlgenerator.cp;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class ConsecutiveC implements CompositePropositionParent {
	
	private String ltlFormulaGenerator(int numP, char charValue, int countRecursion, StringBuilder ltlFormula){
		if(numP == countRecursion)
		{
			ltlFormula.append(""+charValue);
			// appending (countRecursion - 1) 
			// as we are considering p0 instead of p1 when number of prop is 1
			return ltlFormula.append(countRecursion-1).toString(); 
		}
		else {
			ltlFormula.append(""+charValue);
			// appending (countRecursion - 1) 
			// as we are considering p0 instead of p1 when number of prop is 1
			ltlFormula.append(countRecursion-1);
			ltlFormula.append(Symbols.AND);
			ltlFormula.append(Symbols.NEXT);
			ltlFormula.append(Symbols.OPEN_Parenth);
			countRecursion++;
			ltlFormulaGenerator(numP, charValue, countRecursion, ltlFormula);
			ltlFormula.append(Symbols.CLOSE_Parenth);
			return ltlFormula.toString();
		}
	}
	
	@Override
	public String compute(int numProposition, char charValue)
	{
		if(numProposition < 1)
		{
			return "";
		}
		
		StringBuilder ltlFormula = new StringBuilder(Symbols.OPEN_Parenth);
		int recursionCount = 1;
		ltlFormula.append(ltlFormulaGenerator(numProposition, charValue, recursionCount, new StringBuilder("")));
		ltlFormula.append(Symbols.CLOSE_Parenth);
		return ltlFormula.toString();
	}

}
