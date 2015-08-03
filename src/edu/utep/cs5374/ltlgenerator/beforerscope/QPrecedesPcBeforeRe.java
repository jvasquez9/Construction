package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndMinusL;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QPrecedesPcBeforeRe extends QPrecedesPcBeforeReParent{

	@Override
	protected String secondAndHelper(String P_ltl, String Q_ltl) {
		String andMinulLPart = new AndMinusL().and(Q_ltl, Symbols.NOT + P_ltl);
		return andMinulLPart;
	}

}
