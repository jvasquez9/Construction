package edu.utep.cs5374.ltlgenerator.cp;

public class AtleastOneH extends CompositePropositionParent {

	public static String compute(int aCount)
	{
		//AtleastOneH and AtleastOneC are identical.
		return AtleastOneC.compute(aCount);
	}

}
