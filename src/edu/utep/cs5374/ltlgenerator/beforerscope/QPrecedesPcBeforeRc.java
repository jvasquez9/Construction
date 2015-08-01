package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndMinusL;
import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class QPrecedesPcBeforeRc extends BeforeRScopeParent{
	
	public static void main(String[] args)
	{
		System.out.println(new QPrecedesPcBeforeRc().getFormula("p0", "q0", "r0", 1));
	}

	@Override
	//(F Rltl)->((!(Pltl &r !rltl))U((qltl &-l !pltl)|(Rltl))
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
		result.append(Symbols.OPEN_Parenth);
		
		AndMinusL andMinusLHelper = new AndMinusL();
		String notPLTL = not(P_ltl);
		String andMinusLResult = andMinusLHelper.and(Q_ltl, notPLTL);
		result.append(andMinusLResult);
		
		result.append(Symbols.CLOSE_Parenth);
		result.append(Symbols.OR);
		result.append(Symbols.OPEN_Parenth);
		result.append(R_ltl);
		result.append(Symbols.CLOSE_Parenth);
		result.append(Symbols.CLOSE_Parenth);
		
		return result.toString();
	}

}
