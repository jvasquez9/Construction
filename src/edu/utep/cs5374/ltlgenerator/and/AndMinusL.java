package edu.utep.cs5374.ltlgenerator.and;

public class AndMinusL implements AndParent {

	@Override
	public String and(String leftHandSide, String rightHandSide) {
		return new AndR().and(leftHandSide, rightHandSide);
	}
}

