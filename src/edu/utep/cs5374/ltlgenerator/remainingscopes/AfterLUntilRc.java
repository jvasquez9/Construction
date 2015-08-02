package edu.utep.cs5374.ltlgenerator.remainingscopes;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;
import edu.utep.cs5374.ltlgenerator.and.AndL;

public class AfterLUntilRc extends RemainingScopesParent{

	@Override
	public String getFormula(String PbeforeR, String PGlobalR, String R_ltl, String L_ltl, int numProposition) {
		AndL andLCall = new AndL();
		String insideAndL = Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + PbeforeR + Symbols.AND + 
				Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + Symbols.NOT + Symbols.F + R_ltl + Symbols.CLOSE_Parenth + 
				Symbols.RIGHT_ARROW + PGlobalR + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
		//((LLTL&l¬RLTL)→	(LLTL&l((PLTL<R	∧((¬♦RLTL)	→PLTLG )))))
		return Symbols.G + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + andLCall.and(L_ltl, Symbols.NOT + R_ltl) + Symbols.CLOSE_Parenth + 
				Symbols.RIGHT_ARROW + Symbols.OPEN_Parenth + andLCall.and(L_ltl, insideAndL) 
				+ Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
		
	}
//
	
}
