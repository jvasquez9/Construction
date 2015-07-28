package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndL;
import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.cp.ParallelH;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QRespondstoPBeforeRe extends BeforeRScopeParent {

	@Override
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl, int numProposition) {
		// !((!((!r1 & ! r2 &... !rn) & X(RH LTL))) U ((PLTL&r¬RLTL H )&l((¬(Q LTL&r¬RLTL H )) U RLTL H )))
	
		String Rh_ltl = new ParallelH().compute(numProposition, 'r'); 
		String andRLeft=new AndR().and(P_ltl, Symbols.NOT+Rh_ltl);
		String andRRight=new AndR().and(Q_ltl, Symbols.NOT+Rh_ltl);
		
		String andL=new AndL().and(Symbols.OPEN_Parenth+andRLeft+Symbols.CLOSE_Parenth,				
				Symbols.OPEN_Parenth+Symbols.OPEN_Parenth+Symbols.NOT+Symbols.OPEN_Parenth+andRRight
				+Symbols.CLOSE_Parenth+Symbols.CLOSE_Parenth+Symbols.UNTIL+Rh_ltl+Symbols.CLOSE_Parenth);
		
		String partialPart = "";
		for(int i = 0; i < numProposition; i++)
		{
			if(i == numProposition-1)
				partialPart += "" + Symbols.NOT + "r" + i;
			else
				partialPart += "" + Symbols.NOT + "r" + i + Symbols.AND; 
		}
		
		return Symbols.NOT+Symbols.OPEN_Parenth + Symbols.OPEN_Parenth +Symbols.NOT+Symbols.OPEN_Parenth + Symbols.OPEN_Parenth+ 
				partialPart + Symbols.CLOSE_Parenth+Symbols.AND+Symbols.NEXT+Symbols.OPEN_Parenth+Rh_ltl+Symbols.CLOSE_Parenth+Symbols.CLOSE_Parenth+Symbols.CLOSE_Parenth
				+Symbols.UNTIL+Symbols.OPEN_Parenth +andL+Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
		
		
				
	}

}
