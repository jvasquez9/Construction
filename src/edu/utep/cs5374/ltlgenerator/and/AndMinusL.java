package edu.utep.cs5374.ltlgenerator.and;

public class AndMinusL implements AndParent{

	@Override
	public String and(String leftHandSide, String rightHandSide) {
		String ltlFormula = "";
		
		// trim the spaces
		leftHandSide = leftHandSide.replaceAll("\\s+","");
		
		ltlFormula=leftHandSide;
		// Need to work on logic
		return ltlFormula;
	}
	

}
