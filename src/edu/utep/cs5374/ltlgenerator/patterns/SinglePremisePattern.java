package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.utility.SubFormula;

public class SinglePremisePattern extends PatternRecognizer {

	public SinglePremisePattern() {
		super("((~)*." + alphabetOrString + "." + numericOrString +"*)");
	}

	@Override
	public String replace(SubFormula stringToReplace, String rightHandSide) {
		return "(" + stringToReplace + "&" + rightHandSide + ")";
	}

}
