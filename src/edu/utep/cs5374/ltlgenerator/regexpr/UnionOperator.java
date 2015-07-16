package edu.utep.cs5374.ltlgenerator.regexpr;

/**
 * The UnionOperator class is used to generate the union of a given language. 
 * A new non final node is instantiated (Switch state). Once this is done epsilon 
 * edges are created between the switch node and the two nodes which you intend 
 * to generate the union for. Once this is done the resulting graph is returned.
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */

public class UnionOperator extends Operator{

	@Override
	public Node evaluate() {
		return union(operandList[0], operandList[1]);
	}
	
	private Node union(Node first, Node second)
	{
		Node nodeSwitch = new Node();
		nodeSwitch.createEdge("epsilon_U0", first);
		nodeSwitch.createEdge("epsilon_U1", second);
		return nodeSwitch;
	}

	@Override
	public char symbol() {
		return '|';
	}

	@Override
	public int operands() {
		return 2;
	}

	@Override
	public Operator clone() {
		return new UnionOperator();
	}

	@Override
	protected int defaultPrecedence() {
		return 0;
	}
}
