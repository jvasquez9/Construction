package edu.utep.cs5374.ltlgenerator.regexpr;
import java.util.Set;


/**
 * The Operator class is an abstract class that provides certain
 * facilities for operators. It provides a default precedence
 * to an operator, the ability to increase or decrease the precedence
 * of a given operator (for instance, when parenthesis are present),
 * a character symbol for the operator, and a method called operands
 * which returns the number of operands required for the operator.
 * 
 * @author Robert McCain
 * @since 10/22/2014
 * @version 1.0
 *
 */

public abstract class Operator {
	
	protected int weight = 0;
	protected int registeredNodes = 0;
	protected Set<String> alphabet;
	protected Node[] operandList;
	
	protected abstract int defaultPrecedence();
	public abstract Node evaluate();
	public abstract char symbol();
	public abstract int operands();
	public abstract Operator clone();
	
	public Operator()
	{
		operandList = new Node[operands()];
	}
	
	public void setWeightOffset(int x)
	{
		weight = x;
	}
	
	private int getWeight()
	{
		return weight;
	}
	
	public void setAlphabet(Set<String> alpha)
	{
		alphabet = alpha;
	}
	
	public void register(Node node) {
		operandList[registeredNodes % operandList.length] = node;
		registeredNodes++;
	}
	
	public int precedence() {
		return defaultPrecedence() + getWeight();
	}
}
