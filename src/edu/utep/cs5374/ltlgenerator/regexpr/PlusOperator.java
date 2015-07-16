package edu.utep.cs5374.ltlgenerator.regexpr;
import java.util.List;

/**
 * The PlusOperator class is used to generate the plus of a language. 
 * It uses the node’s getFinalNodes() method in order to obtain all final 
 * nodes (states) within a given graph. Once the final states have been obtained, 
 * an epsilon edge is generated between the final states and the start state. 
 * Once this is done, the node is returned.
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */

public class PlusOperator extends Operator{
	
	private Node plus(Node node)
	{
		List<Node> list = node.getFinalNodes();
		for(Node n : list)
		{
			n.createEdge("epsilon_+", node);
		}
		return node;
	}

	@Override
	public Node evaluate() {
		return plus(operandList[0]);
	}

	@Override
	public char symbol() {
		return '+';
	}

	@Override
	public int operands() {
		return 1;
	}

	@Override
	public Operator clone() {
		return new PlusOperator();
	}

	@Override
	protected int defaultPrecedence() {
		return 2;
	}
}
