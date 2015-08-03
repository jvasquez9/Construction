package edu.utep.cs5374.ltlgenerator.patterns;

import edu.utep.cs5374.ltlgenerator.utility.SubFormula;

public class OrPattern extends PatternRecognizer {

	public OrPattern()
	{
		super("(" + alphabetOrString + "." + numericOrString +"*)+(/." +
			alphabetOrString + "." + numericOrString + "*)*");
	}

	@Override
	public String replace(SubFormula stringToReplace, String rightHandSide) {
		return "(" + stringToReplace.toString() + ")&" + rightHandSide;
	}
}
