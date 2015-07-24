package edu.utep.cs5374.ltlgenerator.and;

public class AndMinusL extends AndParent {

	@Override
	public String and(String leftHandSide, String rightHandSide) {
		return and(leftHandSide, rightHandSide, TraversalMode.AND_MINUS_L);
	}
}

