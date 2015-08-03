package edu.utep.cs5374.ltlgenerator.globalscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.cp.ParallelH;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QStrictlyPrecedesPe extends GlobalScopeParent {
	
	@Override
	public String getFormula(String P_ltl, String Q_ltl, int numProposition){
		
		String Ph_ltl = new ParallelH().compute(numProposition, 'p');
		
		String partialPart = "";
		for(int i = 0; i < numProposition; i++)
		{
			partialPart += "" + Symbols.NOT + "p" + i + Symbols.AND; 
		}
		partialPart += Symbols.NEXT + Ph_ltl;
		
		String afterAndRPart = Symbols.NOT + Symbols.OPEN_Parenth + partialPart + Symbols.CLOSE_Parenth;
		
		String firstPart = new AndR().and(Q_ltl, afterAndRPart);
		
		return Symbols.NOT + Symbols.OPEN_Parenth + Symbols.OPEN_Parenth + Symbols.NOT + 
				Symbols.OPEN_Parenth + firstPart + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth +
				Symbols.UNTIL + Symbols.OPEN_Parenth + partialPart + Symbols.CLOSE_Parenth + Symbols.CLOSE_Parenth;
	}
	
}
