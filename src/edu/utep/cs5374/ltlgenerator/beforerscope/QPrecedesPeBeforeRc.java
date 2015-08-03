package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndMinusL;

public class QPrecedesPeBeforeRc extends QPrecedesPeBeforeRcParent {
	
	@Override
	//(F Rltl)->((!((!p0&...&!pn)&!rltl&X(Pltl &r !rltl)))U((Qltl &-l !(pltl))|rltl))
	protected String secondAndHelper(String P_ltl, String Q_ltl) {
		AndMinusL andMinusLHelper = new AndMinusL();
		String notPLTL = not(P_ltl);
		String andMinusLResult = andMinusLHelper.and(Q_ltl, notPLTL);
		return andMinusLResult;
	}

}
