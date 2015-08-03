package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;

public class QStrictlyPrecedesPeBeforeRc extends QPrecedesPeBeforeRc {

	@Override
	//(F Rltl)->((!((!p0&...&!pn)&!rltl&X(Pltl &r !rltl)))U((Qltl &r !(pltl))|rltl))
	protected String secondAndHelper(String P_ltl, String Q_ltl) {	
		AndR andRHelper = new AndR();
		String notPLTL = not(P_ltl);
		String andRResult = andRHelper.and(Q_ltl, notPLTL);
		return andRResult;
	}
	
}
