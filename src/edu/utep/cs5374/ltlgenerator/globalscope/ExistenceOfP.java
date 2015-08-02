package edu.utep.cs5374.ltlgenerator.globalscope;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class ExistenceOfP extends GlobalScopeParent {

	@Override
	public String getFormula(String Q, int numProposition) {
		return Symbols.F + " " + Q;
	}

}
