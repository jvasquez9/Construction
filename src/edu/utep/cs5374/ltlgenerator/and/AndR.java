package edu.utep.cs5374.ltlgenerator.and;

public class AndR extends AndParent{

	@Override
	public String and(String leftHandSide, String rightHandSide) {
		return andHelper(leftHandSide, rightHandSide, 0);
	}
	
}
