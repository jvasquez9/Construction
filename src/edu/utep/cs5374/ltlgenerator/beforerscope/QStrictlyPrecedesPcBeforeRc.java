package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;

public class QStrictlyPrecedesPcBeforeRc extends QPrecedesPcBeforeRcParent {

	@Override
	//(F Rltl)->((!(Pltl &r !rltl))U((qltl &r !pltl)|(Rltl)))
	protected String secondAndHelper(String P_ltl, String Q_ltl) {
		AndR andRHelper = new AndR();
		String notPLTL = not(P_ltl);
		String andRResult = andRHelper.and(Q_ltl, notPLTL);
		return andRResult;
	}

}
