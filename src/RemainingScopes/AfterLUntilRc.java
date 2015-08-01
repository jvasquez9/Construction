package RemainingScopes;

import edu.utep.cs5374.ltlgenerator.beforerscope.BeforeRScopeParent;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;
import edu.utep.cs5374.ltlgenerator.and.AndL;
public class AfterLUntilRc extends RemainingScopesParent{

	@Override
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl, String L_ltl, int numProposition) {
		AndL andLCall = new AndL();
		//((LLTL&l¬RLTL)→	(LLTL&l((PLTL<R	∧((¬♦RLTL)	→PLTLG )))))
		return Symbols.G + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + andLCall.and(L_ltl, Symbols.NOT + R_ltl) + 
				Symbols.CLOSE_Parenth + Symbols.NEXT + Symbols.OPEN_Parenth + andLCall.and(L_ltl, P_ltl) + 
				Symbols.AND + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + Symbols.NOT + //diamond? 
				R_ltl + Symbols.CLOSE_Parenth + Symbols.NEXT + Symbols.CLOSE_Parenth +
				Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
		
	}
//
	
}
