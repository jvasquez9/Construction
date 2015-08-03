package edu.utep.cs5374.ltlgenerator.and;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Stack;

import edu.utep.cs5374.ltlgenerator.patterns.*;
import edu.utep.cs5374.ltlgenerator.symbols.Symbols;
import edu.utep.cs5374.ltlgenerator.utility.SubFormula;

public abstract class AndParent {
	
	private static PatternRecognizer singlePremisePattern = new SinglePremisePattern();
	private static PatternRecognizer uniaryOperatorPattern = new UniaryOperatorPattern();
	private static PatternRecognizer premiseUntilPattern = new PremiseUntilPattern();
	private static PatternRecognizer andPattern = new AndPattern();
	private static PatternRecognizer orPattern = new OrPattern();
	private static PatternRecognizer andWithTrailingAndPattern = new AndWithTrailingAndPattern();
	private static PatternRecognizer notPremisePattern = new NotPremisePattern();
	private static PatternRecognizer andWithTrailingAndNextPattern = new AndWithTrailingAndNextPattern();
	
	private static PatternRecognizer[] basicPatterns = {notPremisePattern, andPattern, orPattern};
	
	private static PatternRecognizer[] temporalPatterns = {singlePremisePattern, uniaryOperatorPattern,
		premiseUntilPattern, orPattern, andPattern, andWithTrailingAndPattern, andWithTrailingAndNextPattern};
	
	public enum TraversalMode{AND_R, AND_L, AND_MINUS_L};
	
	public abstract String and(String leftHandSide, String rightHandSide);
		
	protected String and(String leftHandSide, String rightHandSide, TraversalMode traversalMode) {
		//Strip white space from both strings.
		leftHandSide = leftHandSide.replaceAll("\\s+","");
		rightHandSide = rightHandSide.replaceAll("\\s+", "");
		
		//Since ! and | are used by the regexpr package,
		//let's use temporary substitutions ~ and /
		leftHandSide = leftHandSide.replaceAll("[!]", "~");
		leftHandSide = leftHandSide.replaceAll("[|]", "/");
		
		//Let's see if this is a simple string. These are strings without temporal
		//operators. If it is, resolve it and return the result.
		String firstTest = handleBasicFormula(leftHandSide, rightHandSide);
		
		if(!firstTest.isEmpty())
		{
			return firstTest;
		}
		
		//Otherwise we have a compound formula and must resolve it.
		
		//First, compute our subformula.
		String parentFormula = "(" + leftHandSide + ")";
		Stack<SubFormula> initialSubFormula = findSubFormula(parentFormula);
		
		//Second, filter out subformula that we do not need to process.
		Stack<SubFormula> filteredSubFormula = filterSubFormula(initialSubFormula, parentFormula);
		
		//Third, split the top token if we have to.
		splitTopToken(filteredSubFormula, parentFormula);
		
		//Next, let's do string replacements using the stack we generated.
		String outputString = processSubFormula(traversalMode, rightHandSide, parentFormula,
				filteredSubFormula);
		
		//Finally, if ~ and / are still left in the string
		//replace them with their original symbols
		outputString = outputString.replaceAll("[~]", "!");
		outputString = outputString.replaceAll("[/]", "|");
		
		//Okay just kidding. Last thing we need to do is remove the extra parenthesis we added.
		outputString = outputString.substring(1, outputString.length() - 1);
		
		return outputString;
	}

	private String handleBasicFormula(String leftHandSide, String rightHandSide) {
		
		String modifiedLeftHandSide = leftHandSide;
		
		//If the string does not have parenthesis, add parenthesis.
		if(leftHandSide.charAt(0) != Symbols.OPEN_Parenth.charAt(0))
		{
			modifiedLeftHandSide = Symbols.OPEN_Parenth + modifiedLeftHandSide + Symbols.CLOSE_Parenth;
		}
		
		//Try to generate a string from the given input.
		String result = matchPatternAndGenerateResult(new SubFormula(0, modifiedLeftHandSide.length() - 1, 
				modifiedLeftHandSide), rightHandSide, basicPatterns);
		
		//If we generated a result, remove special symbols then return the result.
		if(!result.isEmpty())
		{
			result = result.replaceAll("[~]", "!");
			result = result.replaceAll("[/]", "|");
		}
		
		return result;
	}

	private void splitTopToken(Stack<SubFormula> filteredSubFormula,
			String parentFormula) {
		SubFormula topFormula = filteredSubFormula.pop();
		
		for(int i = topFormula.getLeftIndex() + 1; i < topFormula.getRightIndex(); i++)
		{
			char currentCharacter = parentFormula.charAt(i);
			
			//If this condition is satisfied, we must split the string
			if(currentCharacter == Symbols.NEXT.charAt(0) ||
					currentCharacter == Symbols.UNTIL.charAt(0) ||
					currentCharacter == Symbols.RIGHT_ARROW.charAt(0))
			{
				SubFormula leftFormula = new SubFormula(topFormula.getLeftIndex(), i + 1, parentFormula);
				SubFormula rightFormula = new SubFormula(i, topFormula.getRightIndex(), parentFormula);
				
				if(!leftFormula.toString().isEmpty())
				{
					filteredSubFormula.push(leftFormula);
				}
				
				if(!rightFormula.toString().isEmpty())
				{
					filteredSubFormula.push(rightFormula);
				}
				
				return;
			}
		}
		
		//Else we leave the token alone and put it back on the stack
		filteredSubFormula.push(topFormula);
	}

	private String processSubFormula(TraversalMode traversalMode, String rightHandSide,
			String parentFormula, Stack<SubFormula> filteredSubFormula) {
		
		int currentCharacter = parentFormula.length() - 1;
		String outputString = "";
		boolean lastTimeBasedExpression = true;
		
		while(!filteredSubFormula.isEmpty())
		{
			SubFormula currentFormula = filteredSubFormula.pop();
			
			//First, grab any characters that behind the specified
			//subformula in the parent formula.
			while(currentCharacter >= currentFormula.getRightIndex())
			{
				outputString = parentFormula.charAt(currentCharacter--) + outputString;
			}
			
			//Next, determine if we need to and the formula. If we do
			//and the expression else do not mutate the formula.
			if(useAnd(traversalMode, lastTimeBasedExpression))
			{
				outputString = matchPatternAndGenerateResultElseLogError(currentFormula, rightHandSide) + outputString;
			}
			else
			{
				outputString = currentFormula.toString() + outputString;
			}
			
			
			//Finally, set the current character equal to the front index
			//of the current sub formula.
			currentCharacter = currentFormula.getLeftIndex();
			lastTimeBasedExpression = false;
		}
		
		//Now let's be sure to grab the leading characters of the parent formula.
		while(currentCharacter >= 0)
		{
			outputString = parentFormula.charAt(currentCharacter--) + outputString;
		}
		
		return outputString;
	}
	
	private Stack<SubFormula> findSubFormula(String parentFormula)
	{
		Stack<SubFormula> subFormulaStack = new Stack<SubFormula>();
		
		for(int currentCharacter = 0; currentCharacter < parentFormula.length(); currentCharacter++)
		{
			//First, check if current character is an open parenthesis.
			int openParenthesisIndex = currentCharacter;
			
			if(parentFormula.charAt(openParenthesisIndex) != Symbols.OPEN_Parenth.charAt(0))
			{
				//This is not an open parenthesis. Continue.
				continue;
			}
			
			//Second, find the matching closing parenthesis.
			int innerPairCount = 0;
			int closeParenthesisIndex = openParenthesisIndex + 1;
			
			while(closeParenthesisIndex < parentFormula.length())
			{
				if(parentFormula.charAt(closeParenthesisIndex) == Symbols.CLOSE_Parenth.charAt(0))
				{
					if(innerPairCount == 0) break;
					
					innerPairCount--;
				}
				else if(parentFormula.charAt(closeParenthesisIndex) == Symbols.OPEN_Parenth.charAt(0))
				{
					innerPairCount++;
				}
				
				closeParenthesisIndex++;
			}
			
			//Finally, push the discovered sub formula to the stack.
			subFormulaStack.push(new SubFormula(openParenthesisIndex, closeParenthesisIndex, parentFormula));
		}
		
		return subFormulaStack;
	}
	
	

	private Stack<SubFormula> filterSubFormula(Stack<SubFormula> inputStack, String parentFormula)
	{
		Stack<SubFormula> processedFormula = new Stack<SubFormula>();
		
		while(!inputStack.isEmpty())
		{
			SubFormula currentFormula = inputStack.pop();
			
			//If we have a leading parenthesis, then it is a redundant
			//formula and we want to omit it.
			if(currentFormula.charAt(0) == Symbols.OPEN_Parenth.charAt(0))
			{
				continue;
			}
			
			//Else if we have a simple formula and we can push it as-is.
			if(currentFormula.isSimpleFormula())
			{
				processedFormula.push(currentFormula);
				continue;
			}
			
			//Else we have a compound formula. Grab the leading
			//portion of the formula then push that to the stack.
			processedFormula.push(currentFormula.extractLeadingFormula());
		}
		
		//We now must flip the stack in order to correct the order.
		Stack<SubFormula> returnedStack = new Stack<SubFormula>();
		
		while(!processedFormula.isEmpty())
		{
			returnedStack.push(processedFormula.pop());
		}
		
		return returnedStack;
	}
	
	private boolean useAnd(TraversalMode traversalMode, boolean isLastTimeBasedExpression)
	{
		if(traversalMode == TraversalMode.AND_MINUS_L)
			return !isLastTimeBasedExpression;
		else if(traversalMode == TraversalMode.AND_L)
			return isLastTimeBasedExpression;
		return true;
	}
	
	private String matchPatternAndGenerateResultElseLogError(SubFormula possiblePattern, String rightHandSide)
	{
		//Try to match
		String result = matchPatternAndGenerateResult(possiblePattern, rightHandSide, temporalPatterns);
		
		//If a result was generated, return the result.
		if(!result.isEmpty())
		{
			return result;
		}
		
		//If all else fails, log the pattern and have Robert take a look at it.
		try {
			FileWriter fw = new FileWriter("AndDiagnosticLog.txt", true);
			fw.write("Time: " + (new Date().toString()) + "\n");
			fw.write("The following pattern was unrecognized by the program: " + 
					possiblePattern.toString() + "\n");
			fw.write("Index Pair was as follows: " + 
					possiblePattern.getLeftIndex() + "," + possiblePattern.getRightIndex() + "\n");
			fw.write("-----------------------------------------------------------------------------\n");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return possiblePattern.toString();
	}
	
	private String matchPatternAndGenerateResult(SubFormula possiblePattern, String rightHandSide,
			PatternRecognizer[] patternList)
	{
		for(PatternRecognizer p : patternList)
		{
			if(p.recognizes(possiblePattern))
			{
				return p.replace(possiblePattern, rightHandSide);
			}
		}
		return "";
	}
}
