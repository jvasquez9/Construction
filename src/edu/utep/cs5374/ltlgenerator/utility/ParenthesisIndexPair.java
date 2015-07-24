package edu.utep.cs5374.ltlgenerator.utility;

public class ParenthesisIndexPair {
	private int openParenthesisIndex;
	private int closeParenthesisIndex;
	
	public ParenthesisIndexPair(int openParenthesisIndex, int closeParenthesisIndex)
	{
		this.openParenthesisIndex = openParenthesisIndex;
		this.closeParenthesisIndex = closeParenthesisIndex;
	}
	
	public int getLeftIndex()
	{
		return openParenthesisIndex;
	}
	
	public int getRightIndex()
	{
		return closeParenthesisIndex;
	}
	
	public String toString()
	{
		return "first: " + openParenthesisIndex + " second: " + closeParenthesisIndex;
	}
}
