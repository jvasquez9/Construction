package edu.utep.cs5374.ltlgenerator.remainingscopes;

import edu.utep.cs5374.ltlgenerator.and.AndL;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AfterL extends RemainingScopesParent{

	public String getFormula(String PGlobalR_ltl, String L_ltl) {
		String notPGlobalR_ltl = Symbols.NOT + Symbols.OPEN_Parenth + PGlobalR_ltl + Symbols.CLOSE_Parenth;
		String andPart = new AndL().and(L_ltl, notPGlobalR_ltl);
		return Symbols.NOT + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth
				+ Symbols.NOT + L_ltl + Symbols.CLOSE_Parenth + Symbols.UNTIL
				+ Symbols.OPEN_Parenth + andPart + Symbols.CLOSE_Parenth
				+ Symbols.CLOSE_Parenth;
		
	}

	@Override
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl, String L_ltl, int numProposition) {
		// TODO Auto-generated method stub
		return null;
	}

}
