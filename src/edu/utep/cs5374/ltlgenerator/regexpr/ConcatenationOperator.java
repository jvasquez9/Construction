package edu.utep.cs5374.ltlgenerator.regexpr;
import java.util.List;

/**
 * The ConcatenationOperator class is used to generate the concatenation of a 
 * given language. First, the final nodes of the first node are computed (using 
 * node’s getFinalNodes() method). Next, an epsilon transition is generated between 
 * each final node of the first node and the second node. Once this is done, each 
 * final state is flagged as non-final after which the resulting graph is returned.
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */
public class ConcatenationOperator extends Operator{

	@Override
	public Node evaluate() {
		return concatenate(operandList[0], operandList[1]);
	}
	
	private Node concatenate(Node a, Node b)
	{
		List<Node> finalNodes = a.getFinalNodes();
		for(Node n : finalNodes)
		{
			n.createEdge("epsilon_.", b);
			n.setFinal(false);
		}
		return a;
	}

	@Override
	public char symbol() {
		return '.';
	}

	@Override
	public int operands() {
		return 2;
	}

	@Override
	public Operator clone() {
		return new ConcatenationOperator();
	}

	@Override
	protected int defaultPrecedence() {
		return 1;
	}
}
