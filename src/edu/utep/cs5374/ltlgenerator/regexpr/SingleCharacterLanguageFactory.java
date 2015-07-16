package edu.utep.cs5374.ltlgenerator.regexpr;

/**
 * This class includes a single static method called generateLanguage which 
 * generates a simple graph of two nodes. It creates one final and one non-final 
 * node. An edge is created between the non-final and final nodes with the given 
 * string as the edge key. Once the nodes are linked the root node (starting state)
 * is returned.
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */

public class SingleCharacterLanguageFactory {
	
	private SingleCharacterLanguageFactory(){};
	
	public static Node generateLanguage(String x)
	{
		Node one = new Node();
		Node two = new Node(true);
		one.createEdge(x, two);
		return one;
	}

}
