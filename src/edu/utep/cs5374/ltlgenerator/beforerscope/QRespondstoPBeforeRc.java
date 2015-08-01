package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndL;
import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QRespondstoPBeforeRc extends BeforeRScopeParent {

	@Override
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl,int numProposition) {
		// ¬((¬ RLTL) U ((PLTL & r ¬ RLTL) & l((¬ (QLTL & r ¬ RLTL)) U RLTL)))
	
		
		String andRLeft=new AndR().and(P_ltl, Symbols.NOT+R_ltl);
		String andRRight=new AndR().and(Q_ltl, Symbols.NOT+R_ltl);
		
		String andL=new AndL().and(Symbols.OPEN_Parenth+andRLeft+Symbols.CLOSE_Parenth,				
				Symbols.OPEN_Parenth+Symbols.OPEN_Parenth+Symbols.NOT+Symbols.OPEN_Parenth+andRRight
				+Symbols.CLOSE_Parenth+Symbols.CLOSE_Parenth+Symbols.UNTIL+R_ltl+Symbols.CLOSE_Parenth);
		
		
		return Symbols.NOT+Symbols.OPEN_Parenth+Symbols.OPEN_Parenth+Symbols.NOT+R_ltl+Symbols.CLOSE_Parenth+
				Symbols.UNTIL+Symbols.OPEN_Parenth+andL+Symbols.CLOSE_Parenth+Symbols.CLOSE_Parenth;
		
		
	}

}
