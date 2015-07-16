package edu.utep.cs5374.ltlgenerator.regexpr;

/**
 * The IntersectionOperator Class is a realization of Demorgan’s law. Both 
 * operands are complemented individually using a ComplementOperator, then they 
 * are joined using a UnionOperator. Lastly, the newly generated graph is 
 * complemented one last time before it is returned.
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */

public class IntersectionOperator extends Operator {

	@Override
	protected int defaultPrecedence() {
		return 0;
	}

	@Override
	public Node evaluate() {
		Operator 	complement = new ComplementOperator(),
					union = new UnionOperator();
		Operator[] operators = {complement, union};
		for(Operator o : operators)
			o.setAlphabet(alphabet);
		complement.register(operandList[0]);
		union.register(complement.evaluate());
		complement.register(operandList[1]);
		union.register(complement.evaluate());
		complement.register(union.evaluate());
		return complement.evaluate(); // !(!(lhs)U!(rhs))
	}

	@Override
	public char symbol() {
		return '^';
	}

	@Override
	public int operands() {
		return 2;
	}

	@Override
	public Operator clone() {
		return new IntersectionOperator();
	}

}
