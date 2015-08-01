package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QStrictlyPrecedesPcBeforeRc extends BeforeRScopeParent {
	
	public static void main(String[] args)
	{
		System.out.println(new QStrictlyPrecedesPcBeforeRc().getFormula("p0", "q0", "r0", 1));
	}

	@Override
	//(F Rltl)->((!(Pltl &r !rltl))U((qltl &r !pltl)|(Rltl)))
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl,
			int numProposition) {
		
		StringBuilder result = new StringBuilder();
		
		String firstPart = generateFirstSyllable(R_ltl);
		result.append(firstPart);
		
		result.append(Symbols.RIGHT_ARROW);
		result.append(Symbols.OPEN_Parenth);
		result.append(Symbols.OPEN_Parenth);
		result.append(Symbols.NOT);
		
		AndR andRHelper = new AndR();
		String notRLTL = not(R_ltl);
		String andRResult = andRHelper.and(P_ltl, notRLTL);
		result.append(andRResult);
		
		result.append(Symbols.CLOSE_Parenth);
		result.append(Symbols.UNTIL);
		result.append(Symbols.OPEN_Parenth);
		
		String notPLTL = not(P_ltl);
		String andRResult2 = andRHelper.and(Q_ltl, notPLTL);
		result.append(andRResult2);
		
		result.append(Symbols.OR);
		result.append(Symbols.OPEN_Parenth);
		result.append(R_ltl);
		result.append(Symbols.CLOSE_Parenth);
		result.append(Symbols.CLOSE_Parenth);
		result.append(Symbols.CLOSE_Parenth);
		
		return result.toString();
	}

}
