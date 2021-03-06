package edu.utep.cs5374.ltlgenerator.globalscope;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QPrecedesPcStar extends GlobalScopeParent{

	@Override
	public String getFormula(String P_ltl, String Q_ltl, int numberofprop) {
		//	! ( ( ! Q_ltl ) U ( P_ltl ) ) U P_ltl)
		return Symbols.NOT + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + Symbols.NOT + Q_ltl + Symbols.CLOSE_Parenth +
				Symbols.UNTIL + Symbols.OPEN_Parenth + P_ltl + Symbols.AND + Symbols.NOT + Q_ltl + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
	}
}
