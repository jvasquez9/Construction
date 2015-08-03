package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QStrictlyPrecedesPeBeforeRe extends QPrecedesPeBeforeReParent {

	@Override
	protected String secondAndHelper(String Ph_ltl, String Q_ltl) {
		String andRPart = new AndR().and(Q_ltl, Symbols.NOT + Ph_ltl);
		return andRPart;
	}
	
}
