package edu.utep.cs5374.ltlgenerator.globalscope;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AbsenseOfP extends GlobalScopeParent{

	@Override
	public String getFormula(String Q, int numProposition) {
		return Symbols.G + " " + Symbols.NOT + " " + Q;
	}

}
