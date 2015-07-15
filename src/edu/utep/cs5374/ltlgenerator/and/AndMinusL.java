package edu.utep.cs5374.ltlgenerator.and;

import java.util.ArrayList;

import edu.utep.cs5374.ltlgenerator.symbols.Symbols;

public class AndMinusL implements AndParent {
	private int STARTINDEX = 0;
	private int ONECHARFLAG = 1;

	@Override
	public String and(String leftHandSide, String rightHandSide) {

		String formula = "";
		// Trim the spaces
		leftHandSide = leftHandSide.replaceAll("\\s+","");

		int length = leftHandSide.length();

		

		ArrayList<Integer> currentCharPositition = new ArrayList();
	
		for(int i = 0; i < leftHandSide.length(); i++){
			if(leftHandSide.charAt(i) == 'p'){
				currentCharPositition.add(i);
			}
		}
		System.out.println(currentCharPositition);
		
		String copyOfLeft = leftHandSide;
		
		for(int i = 0; i < currentCharPositition.size()-1;i++){
			copyOfLeft += leftHandSide.substring(i,currentCharPositition.get(i+1)) + "&-L)";
		}
		
		
		return copyOfLeft;
	}
}

