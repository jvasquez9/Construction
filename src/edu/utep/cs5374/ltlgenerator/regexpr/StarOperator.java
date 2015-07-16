package edu.utep.cs5374.ltlgenerator.regexpr;

/**
 * The StarOperator class is used to generate the star of a given language. 
 * First, an instance of the PlusOperator class is created then the plus of the 
 * node is generated. Next, a new final state node is generated. Finally, an 
 * epsilon edge is created between the newly created final node and the plus 
 * node previously generated. Once this is done, the new graph is returned.
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */

public class StarOperator extends Operator{

	@Override
	public Node evaluate() {
		return star(operandList[0]);
	}
	
	private Node star(Node node)
	{
		Node newNode = new Node(true);
		Operator plus = new PlusOperator();
		plus.register(node);
		newNode.createEdge("epsilon_*", plus.evaluate());
		return newNode;
	}

	@Override
	public char symbol() {
		return '*';
	}

	@Override
	public int operands() {
		return 1;
	}

	@Override
	public Operator clone() {
		return new StarOperator();
	}

	@Override
	protected int defaultPrecedence() {
		return 2;
	}
}
