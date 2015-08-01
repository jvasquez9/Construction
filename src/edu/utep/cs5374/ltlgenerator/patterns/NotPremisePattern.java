package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.utility.SubString;

public class NotPremisePattern extends PatternRecognizer {

	public NotPremisePattern() {
		super("(~." + alphabetOrString + "." + numericOrString +"*)");
	}

	@Override
	public String replace(SubString stringToReplace, String rightHandSide) {
		String leftHandSide = stringToReplace.toString();
		leftHandSide = leftHandSide.substring(1, leftHandSide.length());
		return "(!(" + leftHandSide + "&" + rightHandSide + "))";
	}

}
