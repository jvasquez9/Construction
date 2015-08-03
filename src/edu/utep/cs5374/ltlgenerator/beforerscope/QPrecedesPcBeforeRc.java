package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndMinusL;

public class QPrecedesPcBeforeRc extends QPrecedesPcBeforeRcParent {

	@Override
	//(F Rltl)->((!(Pltl &r !rltl))U((qltl &-l !pltl)|(Rltl)))
	protected String secondAndHelper(String P_ltl, String Q_ltl) {
		AndMinusL andMinusLHelper = new AndMinusL();
		String notPLTL = not(P_ltl);
		String andMinusLResult = andMinusLHelper.and(Q_ltl, notPLTL);
		return andMinusLResult;
	}

}
