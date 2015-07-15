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
	
	@Test
	public void testAndLOne()
	{
		String andResult = new AndL().and("(a1|a2|a3)", "P");
		TestAnd(andResult, "(a1|a2|a3)&P");
	}
	
	@Test
	public void testAndLTwo()
	{
		String andResult = new AndL().and("(a1&a2&a3)", "P");
		TestAnd(andResult, "(a1&a2&a3)&P");
	}
	
	@Test
	public void testAndLThree()
	{
		String andResult = new AndL().and("(a1&X(a2&Xa3))", "P");
		TestAnd(andResult, "(a1&X(a2&X(a3&P)))");
	}
	
	@Test
	public void testAndLFour()
	{
		String andResult = new AndL().and("(a1&X(!a2U(a2&X(!a3Ua3))))", "P");
		TestAnd(andResult, "(a1&X(!a2U(a2&X(!a3U(a3&P)))))");
	}
	
	@Test
	public void testAndLFive()
	{
		String andResult = new AndL().and("(!a1&!a2&!a3)&((!a1&!a2&!a3)U(a1|a2|a3))", "P");
		TestAnd(andResult, "(!a1&!a2&!a3)&((!a1&!a2&!a3)U((a1|a2|a3)&P))");
	}
	
	@Test
	public void testAndLSix()
	{
		String andResult = new AndL().and("(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U (a1& a2 & a3))", "P");
		TestAnd(andResult, "(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U ((a1& a2 & a3) & P)) ");
	}
	
	@Test
	public void testAndLSeven()
	{
		String andResult = new AndL().and("(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U ((a1& !a2 & !a3 & X(a2 & !a3 & X(a3)))))", "P");
		TestAnd(andResult, "(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U ((a1& !a2 & !a3 & X(a2 & !a3 & X((a3) & P)))))");
	}
	
	@Test
	public void testAndLEight()
	{
		String andResult = new AndL().and("(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U (a1& !a2 & !a3 &((!a2 & !a3) U (a2 & !a3 & (!a3 U a3)))))", "P");
		TestAnd(andResult, "(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U (a1& !a2 & !a3 &((!a2 & !a3) U (a2 & !a3 & (!a3 U (a3 & P))))))");
	}	
	
	/****** ANDL MINUS TESTS ********/
	@Test
	public void testAndLMINUSOne()
	{
		String andResult = new AndL().and("(p0|p1|p23)", "P");
		TestAnd(andResult, "(p0|p1|p2)&P");
	}
	
	@Test
	public void testAndLMINUSTwo()
	{
		String andResult = new AndL().and("(p0&p2&p3)", "Q");
		TestAnd(andResult, "(p0&p1&p2)&Q");
	}
	
	@Test
	public void testAndLMINUSThree()
	{
		String andResult = new AndL().and("(p0&X(p1&Xp2))", "Q");
		TestAnd(andResult, "((p0&Q)&X((p1&Q)&Xp2))");
	}
	
	/*@Test
	public void testAndLMINUSFour()
	{
		String andResult = new AndL().and("(a1&X(!a2U(a2&X(!a3Ua3))))", "P");
		TestAnd(andResult, "(a1&X(!a2U(a2&X(!a3U(a3&P)))))");
	}
	
	@Test
	public void testAndLMINUSFive()
	{
		String andResult = new AndL().and("(!a1&!a2&!a3)&((!a1&!a2&!a3)U(a1|a2|a3))", "P");
		TestAnd(andResult, "(!a1&!a2&!a3)&((!a1&!a2&!a3)U((a1|a2|a3)&P))");
	}
	
	@Test
	public void testAndLMINUSSix()
	{
		String andResult = new AndL().and("(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U (a1& a2 & a3))", "P");
		TestAnd(andResult, "(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U ((a1& a2 & a3) & P)) ");
	}
	
	@Test
	public void testAndLMINUSSeven()
	{
		String andResult = new AndL().and("(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U ((a1& !a2 & !a3 & X(a2 & !a3 & X(a3)))))", "P");
		TestAnd(andResult, "(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U ((a1& !a2 & !a3 & X(a2 & !a3 & X((a3) & P)))))");
	}
	
	@Test
	public void testAndLMINUSEight()
	{
		String andResult = new AndL().and("(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U (a1& !a2 & !a3 &((!a2 & !a3) U (a2 & !a3 & (!a3 U a3)))))", "P");
		TestAnd(andResult, "(!a1 & !a2 & !a3) & ((!a1 & !a2 & !a3) U (a1& !a2 & !a3 &((!a2 & !a3) U (a2 & !a3 & (!a3 U (a3 & P))))))");
	}	
*/

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
