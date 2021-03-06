package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.utility.SubFormula;

public class AndWithTrailingAndNextPattern extends PatternRecognizer {

	public AndWithTrailingAndNextPattern() {
		super("((~)*." + alphabetOrString + "." + numericOrString +"*)+(&.(~)*." +
				alphabetOrString + "." + numericOrString + "*)*.&.x");
	}

	@Override
	public String replace(SubFormula stringToReplace, String rightHandSide) {
		String leftHandSide = stringToReplace.toString();
		leftHandSide = leftHandSide.substring(0, leftHandSide.length() - 1);
		return "(" + leftHandSide + rightHandSide + ")&X";
	}
}
