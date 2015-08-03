package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.utility.SubFormula;

public class NotPremisePattern extends PatternRecognizer {

	public NotPremisePattern() {
		super("(~." + alphabetOrString + "." + numericOrString +"*)");
	}

	@Override
	public String replace(SubFormula stringToReplace, String rightHandSide) {
		String leftHandSide = stringToReplace.toString();
		leftHandSide = leftHandSide.substring(1, leftHandSide.length());
		return "(!(" + leftHandSide + "&" + rightHandSide + "))";
	}

}
