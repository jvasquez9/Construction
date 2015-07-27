package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.utility.SubString;

public class AndWithTrailingAndPattern extends Pattern {

	public AndWithTrailingAndPattern() {
		super("((~)*." + alphabetOrString + "." + numericOrString +"*)+(&.(~)*." +
				alphabetOrString + "." + numericOrString + "*)*.&");
	}

	@Override
	public String replace(SubString stringToReplace, String rightHandSide) {
		return stringToReplace.toString() + rightHandSide + "&";
	}
}