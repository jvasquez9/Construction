package edu.utep.cs5374.ltlgenerator.utility;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class SubString {
	ParenthesisIndexPair indexPair;
	String parentString;
	
	public SubString(ParenthesisIndexPair indexPair, String parentString)
	{
		this.indexPair = indexPair;
		this.parentString = parentString;
	}
	
	public SubString(int leftIndex, int rightIndex, String parentString)
	{
		this(new ParenthesisIndexPair(leftIndex, rightIndex), parentString);
	}
	
	public int getLeftIndex()
	{
		return indexPair.getLeftIndex();
	}
	
	public int getRightIndex()
	{
		return indexPair.getRightIndex();
	}
	
	public boolean isSubFormula()
	{
		for(int i = getLeftIndex() + 1; i < getRightIndex(); i++)
		{
			if(parentString.charAt(i) == Symbols.OPEN_Parenth.charAt(0)
					|| parentString.charAt(i) == Symbols.CLOSE_Parenth.charAt(0))
				return false;
		}
		return true;
	}
	
	public String toString()
	{
		return parentString.substring(getLeftIndex() + 1, getRightIndex());
	}
	
	public SubString findSubformula()
	{
		//We need to compute new ending position. So starting at leftHandLimit + 1, find an open parenthesis
		int newRight = getLeftIndex() + 1;
		
		while(newRight < getRightIndex())
		{
			//If we found a parenthesis, return the new substring pair
			if(parentString.charAt(newRight) == Symbols.OPEN_Parenth.charAt(0) 
					|| parentString.charAt(newRight) == Symbols.CLOSE_Parenth.charAt(0))
			{
				return new SubString(getLeftIndex(), newRight, parentString);
			}
			
			//else keep trying
			newRight++;
		}
		
		//Based on previous computations and premises, we should not ever hit this. If we do something is
		//fatally wrong with the program and / or expression.
		assert false;
		return null;
	}
	
	public char charAt(int index)
	{
		return this.toString().charAt(index);
	}
}
