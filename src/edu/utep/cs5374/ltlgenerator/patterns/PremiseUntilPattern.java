package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.utility.SubString;

public class PremiseUntilPattern extends Pattern {

	public PremiseUntilPattern() {
		super("((~)*." + alphabetOrString + "." + numericOrString + "*).(u|>)");
	}

	@Override
	public String replace(SubString stringToReplace, String rightHandSide) {
		String processedString = stringToReplace.toString();
		char lastToken = processedString.charAt(processedString.length() - 1);
		processedString = processedString.substring(0, processedString.length() - 1);
		return "(" + processedString + "&" + rightHandSide + ")" + lastToken;
	}
}
