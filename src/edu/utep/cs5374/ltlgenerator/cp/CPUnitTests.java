package edu.utep.cs5374.ltlgenerator.cp;

import static org.junit.Assert.*;

import org.junit.Test;

public class CPUnitTests {

	@Test
	public void AtleastOneCTestOne() {
		//N = 0 test case
		assertTrue(TestCP(new AtleastOneC().compute(0), ""));
	}
	
	@Test
	public void AtleastOneCTestTwo() {
		//N = -1 test case
		assertTrue(TestCP(new AtleastOneC().compute(-1), ""));
	}
	
	@Test
	public void AtleastOneCTestThree() {
		//N = 1 test case
		assertTrue(TestCP(new AtleastOneC().compute(1), "(p0)"));
	}
	
	@Test
	public void AtleastOneCTestFour() {
		//N = 2 test case
		assertTrue(TestCP(new AtleastOneC().compute(2), "(p0|p1)"));
	}
	
	@Test
	public void AtleastOneCTestFive() {
		//N = 3 test case
		assertTrue(TestCP(new AtleastOneC().compute(3), "(p0|p1|p2)"));
	}
	
	public static boolean TestCP(String result, String expectedResult)
	{
		//Remove all white space from both strings
		result = result.replaceAll("\\s+","");
		expectedResult = expectedResult.replaceAll("\\s+", "");
		return result.equals(expectedResult);
	}

}
