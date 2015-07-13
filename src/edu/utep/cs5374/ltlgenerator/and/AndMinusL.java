package edu.utep.cs5374.ltlgenerator.and;

public class AndMinusL implements AndParent{

	@Override
	public String and(String leftHandSide, String rightHandSide) {
		// TODO Auto-generated method stub
		return leftHandSide.replaceAll("\\", "&"+rightHandSide);
	}
	
	public String replaceAndLMinus(String formula){
		
		while(formula.contains("&P")){
			int positionOfAnd = formula.indexOf("&P");
			int positionLeft = manageleft(formula, positionOfAnd);
			int positionRight = manageright(formula, positionOfAnd);
			
			String substringleft = formula.substring(positionLeft, positionOfAnd);
            String substringright = formula.substring(positionOfAnd+2, positionRight+1);
            
            String toReplace = and(substringright, substringleft);
            
            String substring = formula.substring(positionLeft, positionRight+1);
            formula = formula.replace(substring, toReplace);
			
		}
		
		return formula;
	}
	
	 public int manageleft(String formula, int pos){
	        int open = 0;
	        int close = 0;
	        int finalPos = 0;
	        
	        for (int i = pos; i >= 0; i--){
	            if (formula.charAt(i) == ')')
	                close ++;
	             if (formula.charAt(i) == '(')
	                open ++;
	             if (open == close && open > 0 && close > 0){
	                     finalPos = i;
	                     return finalPos;
	             }
	        }
	        return 0;
	    }

	    public int manageright(String formula, int pos){
	        int open = 0;
	        int close = 0;
	        int finalPos = 0;
	        
	        for (int i = pos; i <= formula.length(); i++){
	            if (formula.charAt(i) == ')')
	                close ++;
	             if (formula.charAt(i) == '(')
	                open ++;
	             if (open == close && open > 0 && close > 0){
	                     finalPos = i;
	                     return finalPos;
	             }
	        }
	        return 0;
	    }

}
