package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class ExistenceofPBeforeRc extends BeforeRScopeParent{
	
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl,int numProposition) {
		// �((� PLTL & r � RLTL)) URLTL)
		
		String andR=new AndR().and(Symbols.NOT + P_ltl, Symbols.NOT+R_ltl);
		
		return Symbols.NOT+Symbols.OPEN_Parenth+Symbols.OPEN_Parenth+andR+Symbols.CLOSE_Parenth+Symbols.CLOSE_Parenth
				+ Symbols.UNTIL+R_ltl;
	}
	
}