package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndMinusL;
import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.cp.ParallelH;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QPrecedesPcBeforeRe extends BeforeRScopeParent{

	@Override
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl, int numProposition) {
		String firstSyllable = Symbols.OPEN_Parenth + Symbols.F + R_ltl + Symbols.CLOSE_Parenth;
		
		String Rh_ltl = new ParallelH().compute(numProposition, 'r');
		String andRPart = new AndR().and(P_ltl, Symbols.NOT + Rh_ltl);
		String secondSyllable = Symbols.OPEN_Parenth + Symbols.NOT + Symbols.OPEN_Parenth + andRPart + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth; 
		String andMinulLPart = new AndMinusL().and(Q_ltl, Symbols.NOT + P_ltl);
		
		String thirdSyllable = Symbols.OPEN_Parenth + andMinulLPart + Symbols.CLOSE_Parenth; 
		
		String partialPart = "";
		for(int i = 0; i < numProposition; i++)
		{
			if(i == numProposition-1)
				partialPart += "" + Symbols.NOT + "p" + i;
			else
				partialPart += "" + Symbols.NOT + "p" + i + Symbols.AND; 
		}
		String fourthSyllable = Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + partialPart + Symbols.CLOSE_Parenth + Symbols.AND + Symbols.NEXT + Rh_ltl + Symbols.CLOSE_Parenth;
		
		
		return firstSyllable + Symbols.RIGHT_ARROW + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + secondSyllable + Symbols.UNTIL +
				Symbols.OPEN_Parenth + thirdSyllable + Symbols.OR + fourthSyllable + Symbols.CLOSE_Parenth +
				Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
	}

}
