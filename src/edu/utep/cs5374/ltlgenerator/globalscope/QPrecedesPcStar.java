package edu.utep.cs5374.ltlgenerator.globalscope;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QPrecedesPcStar extends GlobalScopeParent{

	@Override
	public String getFormula(String Q, int numberofprop) {
		// TODO Auto-generated method stub
		return null;
	}

	public String formulaOfScope(String P_ltl, String Q_ltl){
		//      ! ( ( ! Q_ltl ) U ( P_ltl ) ) U P_ltl)
		return Symbols.NOT + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + Symbols.AND + Qltl + Symbols.CLOSE_Parenth +
				Symbols.UNTIL + Symbols.OPEN_Parenth + Pltl + Symbols.AND + Symbols.NOT + Qltl + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
	}
	

}
