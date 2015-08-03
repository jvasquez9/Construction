package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndMinusL;
import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.cp.ParallelH;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QPrecedesPeBeforeRe extends BeforeRScopeParent{

	@Override
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl, int numProposition) {
		String firstSyllable = generateFirstSyllable(R_ltl);
		String Ph_ltl = new ParallelH().compute(numProposition, 'p');
		String Rh_ltl = new ParallelH().compute(numProposition, 'r');
		
		String partialPartP = getPartialPart(numProposition, 'p');
		String partialPartR = getPartialPart(numProposition, 'r');
		String andRPart = new AndR().and(Ph_ltl, Symbols.NOT + Rh_ltl);
		
		String secondSyllable = Symbols.OPEN_Parenth + Symbols.NOT + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + partialPartP + Symbols.CLOSE_Parenth + Symbols.AND + Symbols.NOT + Rh_ltl + Symbols.AND + Symbols.NEXT + Symbols.OPEN_Parenth + andRPart + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth; 
		
		String andMinulLPart = new AndMinusL().and(Q_ltl, Symbols.NOT + Ph_ltl);
		
		String thirdSyllable = Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + andMinulLPart + Symbols.CLOSE_Parenth + Symbols.OR + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + partialPartR + Symbols.CLOSE_Parenth + Symbols.AND + Symbols.NEXT + Rh_ltl + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth; 
		
		return firstSyllable + Symbols.RIGHT_ARROW + secondSyllable + Symbols.UNTIL + thirdSyllable + Symbols.CLOSE_Parenth;
	}
	
	String getPartialPart(int numProposition, char valueChar){
		String partialPart = "";
		for(int i = 0; i < numProposition; i++)
		{
			if(i == numProposition-1)
				partialPart += "" + Symbols.NOT + valueChar + i;
			else
				partialPart += "" + Symbols.NOT + valueChar + i + Symbols.AND; 
		}
		return partialPart;
	}

}
