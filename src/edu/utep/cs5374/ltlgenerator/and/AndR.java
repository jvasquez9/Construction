package edu.utep.cs5374.ltlgenerator.and;

public class AndR extends AndParent{

	@Override
	public String and(String leftHandSide, String rightHandSide) {
		return andHelper(leftHandSide, rightHandSide, TraversalMode.AND_R);
	}
	
	public static void main(String[] args)
	{
		System.out.println(new AndMinusL().and("(!a1&!a2&!a3)&((!a1&!a2&!a3)U(a1|a2|a3))", "Q"));
		//System.out.println(new AndMinusL().and("(!a1&!a2&!a3)&((!a1&!a2&!a3)U((a1&!a2&!a3&X(a2&!a3&X(a3)))))", "Q"));
		//System.out.println(new AndMinusL().and("(!a1&!a2&!a3)&((!a1&!a2&!a3)U(a1&!a2&!a3&((!a2&!a3)U(a2&!a3&(!a3Ua3)))))", "Q"));
		//System.out.println(new AndMinusL().and("(p0&X(p1&Xp2))", "Q"));
		//System.out.println(new AndMinusL().and("(Fa)", "Q"));
	}
}
