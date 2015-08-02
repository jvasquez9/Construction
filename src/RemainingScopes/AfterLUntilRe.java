package RemainingScopes;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;
import edu.utep.cs5374.ltlgenerator.and.AndL;

public class AfterLUntilRe extends RemainingScopesParent{

	@Override
	public String getFormula(String PbeforeR, String PGlobalR, String R_ltl, String L_ltl, int numProposition) {
		// ((LLTL)	→(LLTL&l((PLTL<R∧((¬♦RLTL)→	PLTLG )))))
		AndL andLCall = new AndL();
		return Symbols.G + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + L_ltl + Symbols.CLOSE_Parenth +
				Symbols.RIGHT_ARROW + Symbols.OPEN_Parenth + andLCall.and(L_ltl, (Symbols.OPEN_Parenth + Symbols.OPEN_Parenth +
						PbeforeR + Symbols.AND + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + 
						Symbols.NEXT + Symbols.F + R_ltl + Symbols.CLOSE_Parenth + 
						Symbols.NEXT + PGlobalR + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth)) + 
						Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
	}
	

}
