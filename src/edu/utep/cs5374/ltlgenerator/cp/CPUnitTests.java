package edu.utep.cs5374.ltlgenerator.cp;

import static org.junit.Assert.*;

import org.junit.Test;

public class CPUnitTests {

	
	/****** AtLeastOneC ***************/
	
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
	
	
	
	/****** AtLeastOneH ***************/
	
	@Test
	public void AtleastOneHTestOne() {
		//N = 0 test case
		assertTrue(TestCP(new AtleastOneH().compute(0), ""));
	}
	
	@Test
	public void AtleastOneHTestTwo() {
		//N = -1 test case
		assertTrue(TestCP(new AtleastOneH().compute(-1), ""));
	}
	
	@Test
	public void AtleastOneHTestThree() {
		//N = 1 test case
		assertTrue(TestCP(new AtleastOneH().compute(1), "(p0)"));
	}
	
	@Test
	public void AtleastOneHTestFour() {
		//N = 2 test case
		assertTrue(TestCP(new AtleastOneH().compute(2), "(p0|p1)"));
	}
	
	@Test
	public void AtleastOneHTestFive() {
		//N = 3 test case
		assertTrue(TestCP(new AtleastOneH().compute(3), "(p0|p1|p2)"));
	}

	
	
	/****** EventualC ***************/
	
	@Test
	public void EventualCTestOne() {
		//N = 0 test case
		assertTrue(TestCP(new EventualC().compute(0), ""));
	}
	
	@Test
	public void EventualCTestTwo() {
		//N = -1 test case
		assertTrue(TestCP(new EventualC().compute(-1), ""));
	}
	
	@Test
	public void EventualCTestThree() {
		//N = 1 test case
		assertTrue(TestCP(new EventualC().compute(1), "(p0)"));
	}
	
	@Test
	public void EventualCTestFour() {
		//N = 2 test case
		assertTrue(TestCP(new EventualC().compute(2), "(p0&X(!p1Up1))"));
	}
	
	@Test
	public void EventualCTestFive() {
		//N = 3 test case
		assertTrue(TestCP(new EventualC().compute(3), "(p0&X(!p1U(p1&X(!p2Up2))))"));
	}

	
	/****** ConsecutiveC ***************/
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
	
	
	/****** ParallelH***************/
	
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
	@Test
	public void ParallelETestOne() {
		//N = -1 test case
		assertTrue(TestCP(new ParallelE().compute(-1), ""));
	}
	@Test
	public void ParallelETestTwo() {
		//N = 0 test case
		assertTrue(TestCP(new ParallelE().compute(0), ""));
	}
	@Test
	public void ParallelETestThree() {
		//N = 1 test case
		assertTrue(TestCP(new ParallelE().compute(1), "(! p0 )& ((! p0 )U( p0 ))"));
	}
	@Test
	public void ParallelETestFour() {
		//N = 2 test case
		assertTrue(TestCP(new ParallelE().compute(2), "(! p0 & ! p1 )& ((! p0 & ! p1 )U( p0 &  p1 ))"));
	}
	@Test
	public void ParallelETestFive() {
		//N = 3 test case
		assertTrue(TestCP(new ParallelE().compute(3), "(! p0 & ! p1 & ! p2 )& ((! p0 & ! p1 & ! p2 )U( p0 &  p1 &  p2 ))"));
	}
	
	@Test
	public void ConsecutiveHTestOne() {
		//N = -1 test case
		assertTrue(TestCP(new ConsecutiveH().compute(-1), ""));
	}
	@Test
	public void ConsecutiveHTestTwo() {
		//N = 0 test case
		assertTrue(TestCP(new ConsecutiveH().compute(0), ""));
	}
	@Test
	public void ConsecutiveHTestThree() {
		//N = 1 test case
		assertTrue(TestCP(new ConsecutiveH().compute(1), "(p0)"));
	}
	@Test
	public void ConsecutiveHTestFour() {
		//N = 2 test case
		assertTrue(TestCP(new ConsecutiveH().compute(2), "( p0 & ! p1 & X( p1 ))"));
	}
	@Test
	public void ConsecutiveHTestFive() {
		//N = 3 test case
		assertTrue(TestCP(new ConsecutiveH().compute(3), "( p0 & ! p1 & ! p2 & X( p1 & ! p2 & X( p2 )))"));
	}
	
	 

	
	public static boolean TestCP(String result, String expectedResult)
	{
		//Remove all white space from both strings
		result = result.replaceAll("\\s+","");
		expectedResult = expectedResult.replaceAll("\\s+", "");
		return result.equals(expectedResult);
	}

}
