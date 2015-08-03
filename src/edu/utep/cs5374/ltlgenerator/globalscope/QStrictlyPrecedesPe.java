package edu.utep.cs5374.ltlgenerator.globalscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;

public class QStrictlyPrecedesPe extends QPrecedesPeParent {

	@Override
	protected String secondAndHelper(String Q_ltl, String afterAndRPart) {
		String firstPart = new AndR().and(Q_ltl, afterAndRPart);
		return firstPart;
	}
}
