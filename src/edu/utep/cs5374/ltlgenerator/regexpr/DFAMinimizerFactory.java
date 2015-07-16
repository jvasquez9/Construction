package edu.utep.cs5374.ltlgenerator.regexpr;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

/**
 * The DFAMinimizerFactory is used to convert a DFA into a minimized DFA. We use 
 * two Maps, a Stack, and a List. The first map (simply called map but outer map 
 * would of made more sense) uses nodes from the previous DFA as the keys and 
 * newly generated nodes for the new language as values. It is used to maintain 
 * associations between old nodes and new nodes. The stack (simply called stack) 
 * contains all Nodes that we have not computed final signatures for (can still 
 * be split). These lists contain nodes from the previous graph and not the new 
 * graph. The list, called minimization, is used to hold a list of nodes that have 
 * been fully computed (can no longer be split). The first element of the 
 * non-splitting set is placed in the list to be used as a key later. None of 
 * the other elements are needed once the splits have been computed.<br><br>
 * We first place all nodes into two separate lists, one for final nodes and one 
 * for non-final nodes. We place both of these lists into our stack. Next, while 
 * the stack is not empty, we compute the signature for each node in the list 
 * (there is a helper method called generateSignature). A signature is computed 
 * by traversing the edge of the current node then using the node as the key for 
 * the outer map. The concatenation of the names of the newly generated nodes 
 * (the values in the outer map) is used to compute the signature. We use the 
 * signature as the key for a second map known as the innerMap. Each value in the 
 * innerMap is a list of all nodes that have the same signature (they hashed the 
 * same). Once we do this for all nodes in the current list, there are two 
 * scenarios that can occur:
 * 
 * <ol><li>There is only one key-value pair in the innerMap. This means that no 
 * more splits can be computed. We store the first element of this fully split 
 * list into the minimization list to be used as a key later (we don’t need the 
 * other elements of this list so we omit them).</li>
 * 
 * <li>There is more than one key-value pair in the innerMap. This means that 
 * some of the states (nodes) split and we need to add the newly generated lists 
 * into the stack. Additionally, for every list other than the first, we need to 
 * generate a new node for the list then update the references in the outer map 
 * to link those nodes to the newly created node.</li></ol>
 * 
 * Finally, once we have computed all splits we connect the edges of the new nodes 
 * together in a final iteration and return the newly generated, minimized 
 * language.

 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */

public class DFAMinimizerFactory {
	private static DFAMinimizerFactory singleton = new DFAMinimizerFactory();
	private int finalCount = 0, nonFinalCount = 0;
	
	public static Language generate(String regexpr)
	{
		return generate(NFAFactory.generate(regexpr));
	}
	
	public static Language generate(Language l)
	{
		//If l is not deterministic, make it deterministic
		if(!l.isDeterministic())
			l = DFAFactory.generate(l);
		
		return singleton.minimize(l);
	}
	
	public Language minimize(Language l)
	{
		Map<Node, Node> map = new HashMap<Node, Node>();
		Set<String> alphabet = l.getAlphabet();
		List<Node> minimization = new LinkedList<Node>();
		
		Stack<List<Node>> stack = new Stack<List<Node>>();
		
		{
			List<Node> finalNodes = l.getFinalNodes();
			Node initialFinalNode = new Node(true);
			initialFinalNode.setName(generateName(true));
			for(Node n : finalNodes)
				map.put(n, initialFinalNode);
			stack.push(finalNodes);
			
			List<Node> nonFinalNodes = l.getNonFinalNodes();
			//We always have final nodes but not always non-final ones
			if(nonFinalNodes.size() > 0)
			{
				Node initialNonFinalNode = new Node();
				initialNonFinalNode.setName(generateName(false));
				for(Node n : nonFinalNodes)
					map.put(n, initialNonFinalNode);
				stack.push(nonFinalNodes);
			}
		}
		
		while(!stack.isEmpty())
		{
			List<Node> current = stack.pop();
			Map<String, List<Node>> innerMap = new HashMap<String, List<Node>>();
			
			for(Node n : current)
			{
				String signature = computeSignature(n, alphabet, map);
				if(!innerMap.containsKey(signature))
					innerMap.put(signature, new LinkedList<Node>());
				innerMap.get(signature).add(n);
			}
			
			if(innerMap.size() < 2) //There were no splits
				minimization.add(current.get(0));
			else //We must compute splits
			{
				boolean isFinalSet = false;
				boolean firstIteration = true;
				for(List<Node> set : innerMap.values())
				{
					stack.push(set);
					if(firstIteration)
					{
						firstIteration = false;
						isFinalSet = set.get(0).isFinal();
					}
					else
					{
						Node newNode = new Node(isFinalSet);
						newNode.setName(generateName(isFinalSet));
						for(Node n : set)
							map.put(n, newNode);
							
					}
				}
				
			}
		}
		
		//finally, generate the graph
		for(Node n : minimization)
		{
			Node current = map.get(n);
			for(String s : alphabet)
			{
				Node edgeNode = n.traverseEdge(s);
				edgeNode = map.get(edgeNode);
				current.createEdge(s, edgeNode);
			}
		}
		
		Node root = map.get(l.getRoot());
		return new Language(root, alphabet, l.getRegExpr(), true);
	}
	
	public String generateName(boolean isFinal)
	{
		if(isFinal)
			return "FINAL." + finalCount++;
		return "NONFINAL." + nonFinalCount++;
	}
	
	public String computeSignature(Node n, Set<String> alphabet, Map<Node, Node> map)
	{
		String returnedString = "";
		for(String s : alphabet)
		{
			returnedString += map.get(n.traverseEdge(s)).getName() + " ";
		}
		return returnedString;
	}
}
