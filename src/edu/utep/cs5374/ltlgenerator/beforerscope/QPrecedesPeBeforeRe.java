package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndMinusL;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QPrecedesPeBeforeRe extends QPrecedesPeBeforeReParent{

	@Override
	protected String secondAndHelper(String Ph_ltl, String Q_ltl) {
		String andMinulLPart = new AndMinusL().and(Q_ltl, Symbols.NOT + Ph_ltl);
		return andMinulLPart;
	}

}
