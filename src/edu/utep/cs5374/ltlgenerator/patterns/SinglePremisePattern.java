package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.utility.SubString;

public class SinglePremisePattern extends Pattern {

	public SinglePremisePattern() {
		super("((~)*." + alphabetOrString + "." + numericOrString +"*)");
	}

	@Override
	public String replace(SubString stringToReplace, String rightHandSide) {
		return "(" + stringToReplace + "&" + rightHandSide + ")";
	}

}
