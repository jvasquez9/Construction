package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public abstract class BeforeRScopeParent {

	public abstract String getFormula(String P_ltl, String Q_ltl, String R_ltl, int numProposition);

	protected String generateFirstSyllable(String R_ltl) {
		return Symbols.OPEN_Parenth + Symbols.F + R_ltl + Symbols.CLOSE_Parenth;
	}

	protected String not(String R_ltl) {
		return Symbols.NOT + Symbols.OPEN_Parenth + R_ltl + Symbols.CLOSE_Parenth;
	}
	
}