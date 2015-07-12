package edu.utep.cs5374.ltlgenerator.and;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AndUnitTests {

	@Test
	public void testAndROne()
	{
		String andResult = new AndR().and("(a0|a1|a2)", "P");
		TestAnd(andResult, "(a0|a1|a2)&P");
	}
	
	@Test
	public void testAndRTwo()
	{
		String andResult = new AndR().and("(aUb)", "P");
		TestAnd(andResult, "((a&P)U(b&P))");
	}
	
	@Test
	public void testAndRThree()
	{
		String andResult = new AndR().and("(a0&a1&a2)", "P");
		TestAnd(andResult, "(a0&a1&a2)&P");
	}
	
	@Test
	public void testAndRFour()
	{
		String andResult = new AndR().and("(Ga)", "P");
		TestAnd(andResult, "(G(a&P))");
	}
	
	@Test
	public void testAndRFive()
	{
		String andResult = new AndR().and("(!a)", "P");
		TestAnd(andResult, "(!(a&P))");
	}
	
	@Test
	public void testAndRSix()
	{
		String andResult = new AndR().and("(Fa)", "P");
		TestAnd(andResult, "(F(a&P))");
	}
	
	@Test
	public void testAndRSeven()
	{
		String andResult = new AndR().and("(a" + Symbols.RIGHT_ARROW + "b)", "P");
		TestAnd(andResult, "(a&P)" + Symbols.RIGHT_ARROW + "(b&P)");
	}
	
	@Test
	public void testAndREight()
	{
		String andResult = new AndR().and("(Xa)", "P");
		TestAnd(andResult, "(X(a&P))");
	}

	public static void TestAnd(String result, String expectedResult)
	{
		if(result == null)
			result = "";
		
		if(expectedResult == null)
			expectedResult = "";
		
		//Remove all white space from both strings
		result = result.replaceAll("\\s+","");
		expectedResult = expectedResult.replaceAll("\\s+", "");
				
		//Check if strings are equal
		assertTrue(result.equals(expectedResult));
	}
}
