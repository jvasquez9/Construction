package edu.utep.cs5374.ltlgenerator.and;
import edu.utep.cs5374.ltlgenerator.regexpr.*;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AndR implements AndParent{
	
	Language andChain;
	Language fChain;
	
	public static void main(String[] args)
	{
		new AndR();
	}
	
	public AndR()
	{
		andChain = DFAFactory.generate("(a.(0|1|2|3|4|5|6|7|8|9)*)+(&.a.(0|1|2|3|4|5|6|7|8|9)*)*");
		fChain = DFAFactory.generate("f.a");
	}
	
	@Override
	public String and(String leftHandSide, String rightHandSide) {
		return andHelper(leftHandSide.toLowerCase(), rightHandSide);
	}

	private String andHelper(String leftHandSide, String rightHandSide)
	{
		if(andChain.recognizes(leftHandSide))
		{
			return "(" + leftHandSide + ")" + "&" + rightHandSide;
		}
		else if(fChain.recognizes(leftHandSide))
		{
			return "(F(a&" + rightHandSide + "))";
		}
		else if(!leftHandSide.isEmpty())
		{
			for(int i = 0; i < leftHandSide.length(); i++)
			{
				if(leftHandSide.charAt(i) == Symbols.OPEN_Parenth.charAt(0))
				{
					for(int j = i + 1, k = 0; j < leftHandSide.length(); j++)
					{
						if(leftHandSide.charAt(j) == Symbols.OPEN_Parenth.charAt(0))
						{
							k++;
						}
						else if(leftHandSide.charAt(j) == Symbols.CLOSE_Parenth.charAt(0) && k > 0)
						{
							k--;
						}
						else if(leftHandSide.charAt(j) == Symbols.CLOSE_Parenth.charAt(0))
						{
							System.out.println(leftHandSide.substring(i+1, j) + rightHandSide);
							return andHelper(leftHandSide.substring(0, i), rightHandSide) +
									andHelper(leftHandSide.substring(i+1, j), rightHandSide) +
									andHelper(leftHandSide.substring(j+1, leftHandSide.length()), rightHandSide);
						}
					}
				}
			}
		}
		return "";
	}
}
