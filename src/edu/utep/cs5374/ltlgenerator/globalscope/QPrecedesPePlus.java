package edu.utep.cs5374.ltlgenerator.globalscope;

import edu.utep.cs5374.ltlgenerator.and.AndMinusL;

public class QPrecedesPePlus extends QPrecedesPeParent {

	@Override
	protected String secondAndHelper(String Q_ltl, String afterAndRPart) {
		String firstPart = new AndMinusL().and(Q_ltl, afterAndRPart);
		return firstPart;
	}
}
