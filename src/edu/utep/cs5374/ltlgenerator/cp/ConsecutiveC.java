package edu.utep.cs5374.ltlgenerator.cp;

public class ConsecutiveC implements CompositePropositionParent {

	private static final String AND = " & ";
	private static final String OPEN_Parenth = "(";
	private static final String CLOSE_Parenth = ")";
	private static final String NEXT = "X";

	
	public String ltlRecursion(int numP, int counter, StringBuilder str){
		if(numP == counter)
		{
			str.append("p");
			// appending (counter - 1) as we are considering p0 instead of p1
			return str.append(counter-1).toString(); 
		}
		else {
			str.append("p");
			// appending (counter - 1) as we are considering p0 instead of p1
			str.append(counter-1);
			str.append(AND);
			str.append(NEXT);
			str.append(OPEN_Parenth);
			counter++;
			ltlRecursion(numP, counter, str);
			str.append(CLOSE_Parenth);
			return str.toString();
		}
	}
	
	@Override
	public String compute(int numProposition)
	{
		int recursionCount = 1;
		return ltlRecursion(numProposition, recursionCount, new StringBuilder(""));
	}

}
