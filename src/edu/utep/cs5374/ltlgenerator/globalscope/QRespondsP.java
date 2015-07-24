package edu.utep.cs5374.ltlgenerator.globalscope;

import edu.utep.cs5374.ltlgenerator.and.AndL;
import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QRespondsP extends GlobalScopeParent{

	@Override
	public String getFormula(String Q, int numberofprop) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public String getFormula(String P_ltl, String Q_ltl,int numProposition){
		// always(PLTL right arrow (PLTL &_l eventual QLTL)))
	
		/*return Symbols.G+Symbols.OPEN_Parenth+P_ltl+Symbols.RIGHT_ARROW+Symbols.OPEN_Parenth+P_ltl+"&L"+Symbols.F+
				Q_ltl+Symbols.CLOSE_Parenth+Q_ltl+Symbols.CLOSE_Parenth+Q_ltl+Symbols.CLOSE_Parenth;
	}*/
	
	
		String leftSide=Symbols.G+Symbols.OPEN_Parenth+P_ltl+Symbols.RIGHT_ARROW+Symbols.OPEN_Parenth+P_ltl;
		String rightSide=Symbols.F+	Q_ltl+Symbols.CLOSE_Parenth+Q_ltl+Symbols.CLOSE_Parenth+Q_ltl+Symbols.CLOSE_Parenth;
		
		String formula=new AndL().and(leftSide, rightSide);
		
		return formula;
		
}
}