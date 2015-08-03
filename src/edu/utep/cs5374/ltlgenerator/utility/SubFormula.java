package edu.utep.cs5374.ltlgenerator.utility;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class SubFormula {
	IndexPair indexPair;
	String parentFormula;
	
	public SubFormula(IndexPair indexPair, String parentString)
	{
		this.indexPair = indexPair;
		this.parentFormula = parentString;
	}
	
	public SubFormula(int leftIndex, int rightIndex, String parentString)
	{
		this(new IndexPair(leftIndex, rightIndex), parentString);
	}
	
	public int getLeftIndex()
	{
		return indexPair.getLeftIndex();
	}
	
	public int getRightIndex()
	{
		return indexPair.getRightIndex();
	}
	
	public boolean isSimpleFormula()
	{
		for(int i = getLeftIndex() + 1; i < getRightIndex(); i++)
		{
			if(parentFormula.charAt(i) == Symbols.OPEN_Parenth.charAt(0)
					|| parentFormula.charAt(i) == Symbols.CLOSE_Parenth.charAt(0))
				return false;
		}
		return true;
	}
	
	public boolean isCompoundFormula()
	{
		return !isSimpleFormula();
	}
	
	public String toString()
	{
		try
		{
			return parentFormula.substring(getLeftIndex() + 1, getRightIndex());
		}
		catch(Exception e)
		{
			return "";
		}
	}
	
	public SubFormula extractLeadingFormula()
	{
		//We need to compute new ending position. So starting at leftHandLimit + 1, find an open parenthesis
		int newRight = getLeftIndex() + 1;
		
		while(newRight < getRightIndex())
		{
			//If we found a parenthesis, return the new substring pair
			if(parentFormula.charAt(newRight) == Symbols.OPEN_Parenth.charAt(0) 
					|| parentFormula.charAt(newRight) == Symbols.CLOSE_Parenth.charAt(0))
			{
				return new SubFormula(getLeftIndex(), newRight, parentFormula);
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
