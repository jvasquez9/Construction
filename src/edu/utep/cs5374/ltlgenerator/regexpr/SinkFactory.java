package edu.utep.cs5374.ltlgenerator.regexpr;
import java.util.Set;

/**
 * This class includes a single static method called generate which accepts 
 * a Set<String>. This set should be equal to the full alphabet that is recognized 
 * by a given language. The method creates a new node then creates edges to itself 
 * by every character within the alphabet (sink state loops to itself). Once this 
 * is done the sink state is returned. It is primarily used by the DFAFactory 
 * class to generate sinks when needed.
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */

public class SinkFactory {
	private SinkFactory(){};
	private static int number = 0;
	
	public static Node generate(Set<String> alphabet)
	{
		Node sink = new Node();
		sink.setName("Sink." + number++);
		for(String s : alphabet)
		{
			sink.createEdge(s, sink);
		}
		return sink;
	}
}
