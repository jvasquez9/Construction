package edu.utep.cs5374.ltlgenerator.regexpr;
import org.junit.Test;

import junit.framework.TestCase;


public class DFAMinimizerFactoryTester extends TestCase{
	
	public boolean testExpression(String regexpr, String test)
	{
		return DFAMinimizerFactory.generate(regexpr).recognizes(test);
	}
	
	public boolean testSize(String regexpr, int predictedSize)
	{
		return DFAMinimizerFactory.generate(regexpr).getNodeCount() ==
				predictedSize;
	}

	@Test
	public void testPlusOne()
	{
		assertTrue(testExpression("a+", "a"));
	}
	
	@Test
	public void testPlusTwo()
	{
		assertFalse(testExpression("a+", ""));
	}
	
	@Test
	public void testPlusThree()
	{
		assertTrue(testExpression("a+", "aaaaaa"));
	}
	
	@Test
	public void testStarOne()
	{
		assertTrue(testExpression("a*", ""));
	}
	
	@Test
	public void testStarTwo()
	{
		assertTrue(testExpression("a*", "a"));
	}
	
	@Test
	public void testStarThree()
	{
		assertTrue(testExpression("a*", "aaaa"));
	}
	
	@Test
	public void testStarFour()
	{
		assertFalse(testExpression("a*", "aabaa"));
	}
	
	@Test
	public void testUnionOne()
	{
		assertFalse(testExpression("a|b", ""));
	}
	
	@Test
	public void testUnionTwo()
	{
		assertTrue(testExpression("a|b", "a"));
	}
	
	@Test
	public void testUnionThree()
	{
		assertTrue(testExpression("a|b", "b"));
	}
	
	@Test
	public void testUnionFour()
	{
		assertFalse(testExpression("a|b", "aa"));
	}
	
	@Test
	public void testConcatenationOne()
	{
		assertTrue(testExpression("ab", "ab"));
	}
	
	@Test
	public void testConcatenationTwo()
	{
		assertFalse(testExpression("ab", "a"));
	}
	
	@Test
	public void testConcatenationThree()
	{
		assertFalse(testExpression("ab", "aa"));
	}
	
	@Test
	public void testConcatenationFour()
	{
		assertFalse(testExpression("ab", "ba"));
	}
	
	@Test
	public void testConcatenationFive()
	{
		assertTrue(testExpression("abcdefg", "abcdefg"));
	}
	
	@Test
	public void testCompoundOne()
	{
		assertTrue(testExpression("(a|b).(c|d)", "ad"));
	}
	
	@Test
	public void testCompoundTwo()
	{
		assertTrue(testExpression("(a|b).(c|d)", "bc"));
	}
	
	@Test
	public void testCompoundThree()
	{
		assertFalse(testExpression("(a|b).(c|d)", ""));
	}
	
	@Test
	public void testCompoundFour()
	{
		assertFalse(testExpression("(a|b).(c|d)", "aa"));
	}
	
	@Test
	public void testCompoundFive()
	{
		assertTrue(testExpression("a.(b|c)*.d+e", "ade"));
	}
	
	@Test
	public void testCompoundSix()
	{
		assertTrue(testExpression("a.(b|c)*.d+e", "abcbdde"));
	}
	
	@Test
	public void testCompoundSeven()
	{
		assertFalse(testExpression("a.(b|c)*.d+e", "accbe"));
	}
	
	@Test
	public void testCompoundEight()
	{
		assertTrue(testExpression("a.(b+|c).(de)*", "abbb"));
	}
	
	@Test
	public void testCompoundNine()
	{
		assertTrue(testExpression("a.(b+|c).(de)*", "acdede"));
	}
	
	@Test
	public void testInvalidOne()
	{
		try{
			testExpression("", "");
			fail();
		}
		catch(Exception e){}
	}
	
	@Test
	public void testInvalidTwo()
	{
		try{
			testExpression(")a(", "a");
			fail();
		}
		catch(Exception e){}
	}
	
	@Test
	public void testInvalidThree()
	{
		try{
			testExpression("a.|a", "aaa");
			fail();
		}
		catch(Exception e){}
	}
	
	@Test
	public void testComplementOne()
	{
		assertTrue(testExpression("!a", ""));
	}
	
	@Test
	public void testComplementTwo()
	{
		assertFalse(testExpression("!a", "a"));
	}
	
	@Test
	public void testComplementThree()
	{
		assertTrue(testExpression("!a", "aa"));
	}
	
	@Test
	public void testComplementFour()
	{
		assertTrue(testExpression("!(ab)", "aba"));
	}
	
	@Test
	public void testComplementFive()
	{
		assertFalse(testExpression("!(abc)", "abc"));
	}
	
	@Test
	public void testComplementSix()
	{
		assertTrue(testExpression("(a)!(b)", "aaa"));
	}
	
	@Test
	public void testCompoundComplementOne()
	{
		assertFalse(testExpression("ab!(cd)ef", "abcdef"));
	}
	
	@Test
	public void testCompoundComplementTwo()
	{
		assertFalse(testExpression("ab!(bc*)d", "abbccccccccd"));
	}
	
	@Test
	public void testCompoundComplementThree()
	{
		assertFalse(testExpression("!(a(b+)e)", "abbbbbe"));
	}
	
	@Test
	public void testMinimizationOne()
	{
		assertTrue(testSize("!(ab)", 4));
	}

	@Test
	public void testMinimizationTwo()
	{
		assertTrue(testSize("a|b", 3));
	}
	
	@Test
	public void testMinimizationThree()
	{
		assertTrue(testSize("a*", 1));
	}
	
	@Test
	public void testMinimizationFour()
	{
		assertTrue(testSize("(aaab(a*|b*))|(aaab(a|b)*)", 6));
	}
	
	@Test
	public void testIntersectionOne()
	{
		assertFalse(testExpression("a+^a*", ""));
	}
	
	@Test
	public void testIntersectionTwo()
	{
		assertTrue(testExpression("a+^a*", "a"));
	}
	
	@Test
	public void testIntersectionThree()
	{
		assertTrue(testExpression("(ab*c)^(abbb+c)", "abbbc"));
	}
}
