package edu.utep.cs5374.ltlgenerator.globalscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QStrictlyPrecedesPc extends GlobalScopeParent{

	@Override
	public String getFormula(String P_ltl, String Q_ltl, int numProposition) {
		//! ( ( ! ( Q_ltl &r ! P_ltl)) U P_ltl )
		String andPart = new AndR().and(Q_ltl, Symbols.NOT + P_ltl);
		return Symbols.NOT + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + Symbols.NOT  + 
				Symbols.OPEN_Parenth  + andPart + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth + 
				Symbols.UNTIL + Symbols.OPEN_Parenth + P_ltl + Symbols.CLOSE_Parenth;
	}

}
