package edu.utep.cs5374.ltlgenerator.globalscope;

public class ExistenceOfP extends GlobalScopeParent {

	@Override
	public String getFormula(String Q, int numProposition) {
		// TODO Auto-generated method stub
		return ("E" + Q);
	}

}
