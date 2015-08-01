package RemainingScopes;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;
import edu.utep.cs5374.ltlgenerator.and.AndL;

public class AfterLUntilRe extends RemainingScopesParent{

	@Override
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl, String L_ltl, int numProposition) {
		// ((LLTL)	→(LLTL&l((PLTL<R∧((¬♦RLTL)→	PLTLG )))))
		AndL andLCall = new AndL();
		return Symbols.G + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + L_ltl + Symbols.CLOSE_Parenth +
				Symbols.NEXT + Symbols.OPEN_Parenth + andLCall.and(L_ltl, (Symbols.OPEN_Parenth + Symbols.OPEN_Parenth +
						P_ltl + Symbols.AND + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + 
						Symbols.NEXT + Symbols.Diamond + R_ltl + Symbols.CLOSE_Parenth + 
						Symbols.NEXT + P_ltl + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth)) + 
						Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
	}
	

}
