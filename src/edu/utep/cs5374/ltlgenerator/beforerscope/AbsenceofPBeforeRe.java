package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.cp.ParallelH;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AbsenceofPBeforeRe extends BeforeRScopeParent{
	
	@Override
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl, int numProposition) {
		// (♦RLTL)→	((¬((¬r1 ∧···∧¬rn)∧X(RLTLH ))) U (PLTL&r¬RLTLH ))
		
		String firstSyllable = Symbols.OPEN_Parenth + Symbols.F + R_ltl + Symbols.CLOSE_Parenth + Symbols.RIGHT_ARROW + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth +
				Symbols.NOT + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth;
		
		String Rh_ltl = new ParallelH().compute(numProposition, 'r');
		String andRPart = new AndR().and(P_ltl, Symbols.NOT + Rh_ltl);
		String thirdSyllable = Symbols.AND + Symbols.NEXT + Symbols.OPEN_Parenth + R_ltl +  Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth +	Symbols.UNTIL; 
		
		String fourthSyllable = Symbols.OPEN_Parenth + andRPart + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth ; 
		
		String partialPart = "";
		for(int i = 0; i < numProposition; i++)
		{
			if(i == numProposition-1)
				partialPart += "" + Symbols.NOT + "r" + i;
			else
				partialPart += "" + Symbols.NOT + "r" + i + Symbols.AND; 
		}
		
		
		return firstSyllable + partialPart + thirdSyllable + fourthSyllable;
	}
	
}
