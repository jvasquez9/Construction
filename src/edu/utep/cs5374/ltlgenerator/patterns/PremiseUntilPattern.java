package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.utility.SubFormula;

public class PremiseUntilPattern extends PatternRecognizer {

	public PremiseUntilPattern() {
		super("((~)*." + alphabetOrString + "." + numericOrString + "*).(u|>)");
	}

	@Override
	public String replace(SubFormula stringToReplace, String rightHandSide) {
		String processedString = stringToReplace.toString();
		char lastToken = processedString.charAt(processedString.length() - 1);
		processedString = processedString.substring(0, processedString.length() - 1);
		return "(" + processedString + "&" + rightHandSide + ")" + lastToken;
	}
}
