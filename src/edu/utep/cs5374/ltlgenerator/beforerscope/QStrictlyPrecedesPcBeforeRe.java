package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QStrictlyPrecedesPcBeforeRe extends QPrecedesPcBeforeReParent {
	
	@Override
	protected String secondAndHelper(String P_ltl, String Q_ltl) {
		String andRPart = new AndR().and(Q_ltl, Symbols.NOT + P_ltl);
		return andRPart;
	}

}
