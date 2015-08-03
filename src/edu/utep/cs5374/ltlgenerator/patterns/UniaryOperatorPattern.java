package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.utility.SubFormula;

public class UniaryOperatorPattern extends PatternRecognizer {
	
	public UniaryOperatorPattern() {
		super("(x|f|g|>)." + alphabetOrString + "." + numericOrString + "*");
	}

	@Override
	public String replace(SubFormula stringToReplace, String rightHandSide) {
		char leadSymbol = stringToReplace.charAt(0);
		String trailingString = stringToReplace.toString().substring(1);
		return leadSymbol + "(" + trailingString + "&" + rightHandSide + ")";
	}
}
