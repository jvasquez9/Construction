package edu.utep.cs5374.ltlgenerator.cp;

public class AtleastOneH implements CompositePropositionParent {
	
	@Override
	public String compute(int numProposition)
	{
		return new AtleastOneC().compute(numProposition);
	}

}
