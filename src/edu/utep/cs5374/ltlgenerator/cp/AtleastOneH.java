package edu.utep.cs5374.ltlgenerator.cp;

public class AtleastOneH implements CompositePropositionParent {
	
	@Override
	public String compute(int numProposition, char charValue)
	{
		return new AtleastOneC().compute(numProposition, charValue);
	}

}
