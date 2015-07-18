package edu.utep.cs5374.ltlgenerator.symbols;

public class Symbols {
	public static String 	AND =				"&",
							OR = 				"|",
							UNTIL =				"U",
							NEXT =				"X",
							NOT = 				"!",
							OPEN_Parenth = 		"(",
							CLOSE_Parenth = 	")",
							RIGHT_ARROW = 		">",
							F =					"F",
							G = 				"G";
	
	public static String[] SYMBOL_LIST = {AND, OR, UNTIL, NEXT, NOT, 
		OPEN_Parenth, CLOSE_Parenth, RIGHT_ARROW, F, G};
	
	public static boolean isSymbol(char letter)
	{
		for(String symbolString : SYMBOL_LIST)
		{
			if(symbolString.toLowerCase().charAt(0) == letter)
			{
				return true;
			}
		}
		return false;
	}
	
	public static boolean isSymbol(String letterString)
	{
		return isSymbol(letterString.charAt(0));
	}
}
