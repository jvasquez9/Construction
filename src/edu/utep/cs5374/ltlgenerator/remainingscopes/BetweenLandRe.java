package edu.utep.cs5374.ltlgenerator.remainingscopes;

import edu.utep.cs5374.ltlgenerator.and.AndL;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class BetweenLandRe extends RemainingScopesParent{

	@Override
	public String getFormula(String PbeforeR, String PGlobalR, String R_ltl, String L_ltl, int numProposition) {
		String L_ltlAndLPbeforeR_ltl = new AndL().and(L_ltl, PbeforeR);
		return Symbols.G + Symbols.OPEN_Parenth + L_ltl + 
				Symbols.RIGHT_ARROW + Symbols.OPEN_Parenth + L_ltlAndLPbeforeR_ltl +
				Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
		
	}
}
