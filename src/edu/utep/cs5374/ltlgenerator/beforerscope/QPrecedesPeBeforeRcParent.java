package edu.utep.cs5374.ltlgenerator.beforerscope;

import edu.utep.cs5374.ltlgenerator.and.AndR;
import edu.utep.cs5374.ltlgenerator.patterns.PatternGenerator;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public abstract class QPrecedesPeBeforeRcParent extends BeforeRScopeParent {

	@Override
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl, int numProposition) {
		
		StringBuilder result = new StringBuilder();
		
		String firstPart = generateFirstSyllable(R_ltl);
		result.append(firstPart);
		
		result.append(Symbols.RIGHT_ARROW);
		result.append(Symbols.OPEN_Parenth);
		result.append(Symbols.OPEN_Parenth);
		result.append(Symbols.NOT);
		result.append(Symbols.OPEN_Parenth);
		
		String notChain = PatternGenerator.generateNotAndNotPattern(numProposition, 'p');
		result.append(notChain);
		
		result.append(Symbols.AND);
		
		String notRLTL = not(R_ltl);
		result.append(notRLTL);
		
		result.append(Symbols.AND);
		result.append(Symbols.NEXT);
		
		AndR andRHelper = new AndR();
		String andRResult = andRHelper.and(P_ltl, notRLTL);
		result.append(andRResult);
		
		result.append(Symbols.CLOSE_Parenth);
		result.append(Symbols.CLOSE_Parenth);
		
		result.append(Symbols.UNTIL);
		result.append(Symbols.OPEN_Parenth);
		
		//Here is where we use our template.
		result.append(secondAndHelper(P_ltl, Q_ltl));
		
		result.append(Symbols.OR);
		result.append(R_ltl);
		result.append(Symbols.CLOSE_Parenth);
		result.append(Symbols.CLOSE_Parenth);
		
		return result.toString();
	}

	protected abstract String secondAndHelper(String P_ltl, String Q_ltl);

}