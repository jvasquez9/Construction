package edu.utep.cs5374.ltlgenerator.beforerscope;

public class ExistanceOfPBeforeRe extends BeforeRScopeParent{

	@Override
	public String getFormula(String P_ltl, String Q_ltl, String R_ltl, int numProposition) {
		// (♦RLTL)→	((¬((¬r1 ∧···∧¬rn)∧X(RLTLH ))) U (PLTL&r¬RLTLH ))
		return null;
	}

}
