package edu.utep.cs5374.ltlgenerator.globalscope;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QRespondsP extends GlobalScopeParent{

	@Override
	public String getFormula(String Q, int numberofprop) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String formulaOfScope(String P_ltl, String Q_ltl){
		// always(PLTL right arrow (PLTL &_l eventual QLTL)))
	
				
		return Symbols.G+Symbols.OPEN_Parenth+P_ltl+Symbols.RIGHT_ARROW+Symbols.OPEN_Parenth+P_ltl+"&L"+Symbols.F+
				Q_ltl+Symbols.CLOSE_Parenth+Q_ltl+Symbols.CLOSE_Parenth+Q_ltl+Symbols.CLOSE_Parenth;
	}
	
	
}
