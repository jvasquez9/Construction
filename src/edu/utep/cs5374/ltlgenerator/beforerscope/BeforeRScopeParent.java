package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public abstract class BeforeRScopeParent {

	public abstract String getFormula(String P_ltl, String Q_ltl, String R_ltl, int numProposition);
	
}