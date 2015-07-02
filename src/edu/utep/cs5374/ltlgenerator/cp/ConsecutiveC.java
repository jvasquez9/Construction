package edu.utep.cs5374.ltlgenerator.cp;

/**
 * @author Porag
 *
 */

public class ConsecutiveC extends CompositePropositionParent {

	private static final String OR = " | ";
	private static final String OPEN_P = "(";
	private static final String CLOSE_P = ")";

	public static String compute(int aCount)
	{
		StringBuilder stringBuilder = new StringBuilder(OPEN_P);
		
		stringBuilder.append(CLOSE_P);
		return stringBuilder.toString();
	}

}
