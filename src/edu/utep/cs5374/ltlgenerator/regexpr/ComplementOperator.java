package edu.utep.cs5374.ltlgenerator.regexpr;
import java.util.List;

/**
 * The ComplementOperator class converts the given node (operand) into a 
 * deterministic finite automaton using DFAFactory’s generate method. Once this 
 * is done, a list of all the nodes from the DFA are obtained. Lastly, the final 
 * boolean in each node is toggled (final nodes become nonfinal and vice versa). 
 * Once that is done the node is returned.
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */

public class ComplementOperator extends Operator{

	@Override
	public Node evaluate() {
		Language l = DFAFactory.generate(new Language(operandList[0], alphabet, "", false));
		List<Node> items = l.getRoot().generateNodeList();
		for(Node n : items)
			n.setFinal(!n.isFinal());
		return l.getRoot();
	}

	@Override
	public char symbol() {
		return '!';
	}

	@Override
	public int operands() {
		return 1;
	}

	@Override
	public Operator clone() {
		return new ComplementOperator();
	}

	@Override
	protected int defaultPrecedence() {
		return 2;
	}
}
