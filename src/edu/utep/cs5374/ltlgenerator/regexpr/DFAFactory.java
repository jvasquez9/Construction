package edu.utep.cs5374.ltlgenerator.regexpr;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * The DFAFactory class is used to convert an NFA into a DFA. It utilizes a Stack 
 * and a Map.  The map contains all newly generated states while the Stack holds 
 * sets that haven�t been processed yet. First, the epsilon closure of the root 
 * node (start state) is computed then placed in a set. Next, a node is created 
 * for the set and placed into the map using the epsilon closure set as the key 
 * and the node itself as the value. After this the set is placed in the stack. 
 * There is a loop after this which iterates while the stack is not empty. We 
 * figure out what the current node is by using the set that is popped from the 
 * stack as the key. A second loop exists in the while loop which iterates over 
 * the alphabet of the provided language. In this loop, for every transition that 
 * occurs the epsilon closure is computed per alphabet character then saved in 
 * separate sets. Once we compute this, we check each set for the following cases.
 * 
 * <ol><li>If the set is empty we link the specified edge to a sink node. 
 * The sink node does not appear in the map and is only used when a sink is 
 * required for the conversion.</li>
 * 
 * <li>If it is a new state (key does not already exist in the Map) we 
 * create a new Node in the map using the set generated as the key value. 
 * We connect the current node to the new node (add an edge from one to the 
 * other) once this is done. Finally, we push the set to the stack to be 
 * processed on a separate iteration.</li>
 * 
 * <li>If the state already exists (key exists in the map already) we simply 
 * create an edge between the current node and the node at the key position of 
 * the map.</li></ol>
 * 
 * Once this is all done, we return the new language that was generated by the 
 * algorithm.
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */
public class DFAFactory {
	private DFAFactory(){}
	private static DFAFactory instance = new DFAFactory();
	
	public static Language generate(Language nfa)
	{
		return instance.generateDFA(nfa);
	}
	
	public static Language generate(String regexpr)
	{
		return generate(NFAFactory.generate(regexpr));
	}
	
	private Language generateDFA(Language nfa)
	{
		Node sink = SinkFactory.generate(nfa.getAlphabet());
		Stack<Set<String>> stack = new Stack<Set<String>>();
		Map<Set<String>, Node> nodeMap = new HashMap<Set<String>, Node>();
		
		Set<String> rootClosure = nfa.getRoot().epsilonClosure();
		Node newRoot = new Node(finalStateContainedIn(nfa, rootClosure));
		stack.push(rootClosure);
		nodeMap.put(rootClosure, newRoot);
		
		while(!stack.isEmpty())
		{
			Set<String> currentSet = stack.pop();
			Node currentNode = nodeMap.get(currentSet);
			
			for(String alphabet : nfa.getAlphabet())
			{
				Set<String> nextSet = new HashSet<String>();
				
				for(String nodeString : currentSet)
				{
					Node traversedNode = nfa.getNodeByName(nodeString).traverseEdge(alphabet);
					if(traversedNode != null)
					{
						nextSet.addAll(traversedNode.epsilonClosure());
					}
				}
				
				if(nextSet.size() == 0) //this must be a sink
				{
					currentNode.createEdge(alphabet, sink);
				}
				else if(!nodeMap.containsKey(nextSet)) //this is a new state
				{
					Node nextNode = new Node(finalStateContainedIn(nfa, nextSet));
					nodeMap.put(nextSet, nextNode);
					stack.push(nextSet);
					
					currentNode.createEdge(alphabet, nextNode);
				}
				else
				{
					currentNode.createEdge(alphabet, nodeMap.get(nextSet));
				}
			}
		}
		
		return new Language(newRoot, nfa.getAlphabet(), nfa.getRegExpr(), true);
	}
	
	private boolean finalStateContainedIn(Language nfa, Set<String> set)
	{
		boolean isFinal = false;
		for(String s : set)
			isFinal |= nfa.getNodeByName(s).isFinal();
		return isFinal;
	}
}
