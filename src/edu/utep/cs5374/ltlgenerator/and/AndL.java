package edu.utep.cs5374.ltlgenerator.and;

public class AndL implements AndParent {

	@Override
	public String and(String leftHandSide, String rightHandSide) {
		
		String formula = "";
		int flagForSecondLastChar = 2;
		// trim the spaces
		leftHandSide = leftHandSide.replaceAll("\\s+","");
		
		if(leftHandSide.charAt(leftHandSide.length()-flagForSecondLastChar) != ')')
			formula = leftHandSide + "&" + rightHandSide;
		
		return formula;
	}

}
