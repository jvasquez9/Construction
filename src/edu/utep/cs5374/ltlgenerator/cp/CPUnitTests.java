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

	@Test
	public void ConsecutiveCTestOne() {
		//N = -1 test case
		assertTrue(TestCP(new ConsecutiveC().compute(-1), ""));
	}
	@Test
	public void ConsecutiveCTestTwo() {
		//N = 0 test case
		assertTrue(TestCP(new ConsecutiveC().compute(0), ""));
	}
	@Test
	public void ConsecutiveCTestThree() {
		//N = 1 test case
		assertTrue(TestCP(new ConsecutiveC().compute(1), "(p0)"));
	}
	@Test
	public void ConsecutiveCTestFour() {
		//N = 2 test case
		assertTrue(TestCP(new ConsecutiveC().compute(2), "(p0 & X(p1))"));
	}
	@Test
	public void ConsecutiveCTestFive() {
		//N = 3 test case
		assertTrue(TestCP(new ConsecutiveC().compute(3), "(p0 & X(p1 & X(p2)))"));
	}
	
	@Test
	public void ParallelHTestOne() {
		//N = -1 test case
		assertTrue(TestCP(new ParallelH().compute(-1), ""));
	}
	@Test
	public void ParallelHTestTwo() {
		//N = 0 test case
		assertTrue(TestCP(new ParallelH().compute(0), ""));
	}
	@Test
	public void ParallelHTestThree() {
		//N = 1 test case
		assertTrue(TestCP(new ParallelH().compute(1), "(p0)"));
	}
	@Test
	public void ParallelHTestFour() {
		//N = 2 test case
		assertTrue(TestCP(new ParallelH().compute(2), "(p0 & p1)"));
	}
	@Test
	public void ParallelHTestFive() {
		//N = 3 test case
		assertTrue(TestCP(new ParallelH().compute(3), "(p0 & p1 & p2)"));
	}
	
	 

	
	public static boolean TestCP(String result, String expectedResult)
	{
		//Remove all white space from both strings
		result = result.replaceAll("\\s+","");
		expectedResult = expectedResult.replaceAll("\\s+", "");
		return result.equals(expectedResult);
	}

}
