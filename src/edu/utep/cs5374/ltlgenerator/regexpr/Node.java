package edu.utep.cs5374.ltlgenerator.regexpr;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.Map.Entry;

/**
 * The Node class is a realization of the vertices and edges that we
 * use while making NFA's and DFA's. Each node has a unique name which
 * is generated on creation, a boolean isFinal to indicate whether or
 * not a node is final, and Map containing key value pairs of edges and
 * target nodes. There is also an internal scratched variable which
 * is used during graph traversal. There are two traversal algorithms,
 * one for when the graph is deterministic and one for when it is
 * non-deterministic.
 * 
 * @author Robert McCain
 * @since 10/22/2014
 * @version 1.0
 *
 */
public class Node
		{
			private Map<String, Node> edges = new HashMap<String, Node>();
			private boolean isFinal;
			private boolean scratched = false;
			private String name;
			private static int number = 0;
			private NodeBehavior behavior;
			
			public Node()
			{
				this(false);
			}
			
			public Node(boolean isFinal)
			{
				this.isFinal = isFinal;
				this.name = "Node." + number++;
			}
			
			
			public void createEdge(String s, Node vertex)
			{
				edges.put(s, vertex);
			}
			
			public Node traverseEdge(String s)
			{
				return edges.get(s);
			}
			
			private boolean isScratched()
			{
				return scratched;
			}
			
			private void setScratched(boolean scratched)
			{
				this.scratched = scratched;
			}
			
			public boolean isFinal()
			{
				return isFinal;
			}
			
			public void setFinal(boolean isFinal)
			{
				this.isFinal = isFinal;
			}
			
			private Iterator<Entry<String, Node>> getEdgeIterator()
			{
				return edges.entrySet().iterator();
			}
			
			/**
			 * We compute the list of all nodes using the generateNodeList() method. 
			 * Next, we create a new list and copy over only the nodes that are final / 
			 * non-final depending on the method called. This new list is returned by 
			 * the method.
			 * @return List containing all final nodes in the graph.
			 */
			public List<Node> getFinalNodes()
			{
				List<Node> finalNodes = new LinkedList<Node>();
				List<Node> items = generateNodeList();
				
				for(Node n : items)
					if(n.isFinal())
						finalNodes.add(n); 
				
				return finalNodes;
			}
			
			/**
			 * We compute the list of all nodes using the generateNodeList() method. 
			 * Next, we create a new list and copy over only the nodes that are final / 
			 * non-final depending on the method called. This new list is returned by 
			 * the method.
			 * @return List containing all non final nodes in the graph.
			 */
			public List<Node> getNonFinalNodes()
			{
				List<Node> nonFinalNodes = new LinkedList<Node>();
				List<Node> items = generateNodeList();
				
				for(Node n : items)
					if(!n.isFinal())
						nonFinalNodes.add(n); 
				
				return nonFinalNodes;
			}
			
			/**
			 * This method is used to obtain all the final nodes of a given graph 
			 * (this node is assumed to be starting state). It utilizes a stack and an 
			 * internal variable known as scratched. First, the starting state is pushed 
			 * into the stack and scratched. Then, while there are nodes in the stack, nodes
			 * are popped from the stack. Each node is added to a list when visited 
			 * (popped from the stack). An iterator containing all edges of the current 
			 * node is then generated. These edges are traversed and if the nodes are not 
			 * scratched they are then scratched and pushed to the stack. Once the full 
			 * operation is done the list containing all nodes (states) is returned. This 
			 * method is reused in many other methods to help generate different structures.
			 * This is essentially a depth first search algorithm.
			 * @return List containing all nodes in the graph
			 */
			public List<Node> generateNodeList()
			{
				Stack<Node> stack = new Stack<Node>();
				List<Node> list = new LinkedList<Node>();
				stack.push(this);
				setScratched(true);
				while(!stack.isEmpty())
				{
					Node current = stack.pop();
					//Add the node to our list
					list.add(current);
					//We iterate over all edges of the current node
					Iterator<Entry<String, Node>> iter = current.getEdgeIterator();
					while(iter.hasNext())
					{
						Node current2 = iter.next().getValue();
						//If it hasn't been scratched already,
						//push it to the stack and scratch it.
						if(!current2.isScratched())
						{
							stack.push(current2);
							current2.setScratched(true);
						}
					}
				}
				
				//Be sure to unscratch all the nodes.
				for(Node n : list)
					n.setScratched(false);
				
				return list;
			}
			
			/**
			 * First, a list of all nodes in the graph is generated using the 
			 * generateNodeList() method. Next, a new Map is created.
			 * We iterate over the list we generated and add each node to the map using 
			 * its name as the key and the node itself as the value. Once this is done 
			 * the map is returned.
			 * @return A map based on the criteria stated above.
			 */
			public Map<String, Node> generateGraphMap()
			{
				Map<String, Node> graphMap = new HashMap<String, Node>();
				List<Node> nodeList = generateNodeList();
				
				for(Node n : nodeList)
					graphMap.put(n.getName(), n);
				
				return graphMap;
			}
			
			public String toString()
			{
				StringBuilder sb = new StringBuilder();
				List<Node> nodeList = generateNodeList();
				for(Node n : nodeList)
				{
					sb.append(n.getName() + "(" + n.isFinal() + ")\n");
					Iterator<Entry<String, Node>> iter = n.getEdgeIterator();
					while(iter.hasNext())
					{
						Entry<String, Node> entry = iter.next();
						sb.append("\t" + entry.getKey() + " -> " + entry.getValue().getName() + "\n");
					}
				}
				return sb.toString();
			}
			
			public String getName()
			{
				return name;
			}
			
			public void setName(String name)
			{
				this.name = name;
			}
			
			/**
			 * This method is used to compute the epsilon closure of the current node. 
			 * The way it works is by using a Stack. It pushes itself into the stack 
			 * then, while the stack is not empty, checks all key-value pairs in its 
			 * transition table (map). If any of the keys contain “epsilon” then that 
			 * particular node is pushed into the stack. As nodes are popped from the 
			 * stack they are added to the set. Once the stack is empty the set containing 
			 * the epsilon closure is returned.
			 * 
			 * @return A set containing the epsilon closure of the current node.
			 */
			public Set<String> epsilonClosure()
			{
				Set<String> set = new HashSet<String>();
				Stack<Node> stack = new Stack<Node>();
				stack.push(this);
				
				while(!stack.isEmpty())
				{
					Node current = stack.pop();
					set.add(current.getName());
					Iterator<Entry<String, Node>> iter = current.getEdgeIterator();
					while(iter.hasNext())
					{
						Entry<String, Node> entry = iter.next();
						if(entry.getKey().contains("epsilon"))
						{
							//ensures we don't have an infinite loop
							if(entry.getValue().name.compareTo(current.name) != 0)
								stack.push(entry.getValue());
						}
					}
				}
				
				return set;
			}
			
			/**
			 * This method calculates whether a given string is accepted by the language. 
			 * This method is a helper method to recognizes(String s). It utilizes 
			 * recursion (tree) by both traversing epsilon edges freely and by traversing 
			 * characters in the string when recognized.
			 * 
			 * @param s Possible word of the language being tested.
			 * @return True or false, depending on criteria above.
			 */
			private boolean recognizesNFA(String s)
			{
				if(s.length() == 0)
				{
					if(isFinal())
						return isFinal();
				}
				
				boolean isRecognized = false;
				
				Iterator<Entry<String, Node>> iter = getEdgeIterator();
				while(iter.hasNext())
				{
					Entry<String, Node> current = iter.next();
					Node node = current.getValue();
					String edgeName = current.getKey();
					
					if(s.length() > 0 && edgeName.compareTo(s.charAt(0) + "") == 0)
						isRecognized |= node.recognizesNFA(s.substring(1));
					else if(edgeName.contains("epsilon"))
						if(node.name.compareTo(name) != 0) //ensures we don't have an infinite loop
							isRecognized |= node.recognizesNFA(s.substring(0));
				}
				return isRecognized;
			}
			
			/**
			 * This method is much faster than the recognizesNFA(String s) 
			 * method but only works with deterministic languages. It traverses nodes one 
			 * by one by iterating over each character of the string and providing that 
			 * character to the traverseEdge function. If current becomes null at any point 
			 * or the last node reached by the string is not final then the method returns 
			 * false.
			 * 
			 * @param s Possible word of the language being tested.
			 * @return True or false, depending on criteria above.
			 */
			private boolean recognizesDFA(String s)
			{
				Node current = this;
				
				for(int i = 0; i < s.length(); i++)
				{
					current = current.traverseEdge(s.charAt(i) + "");
				
					if(current == null)
						return false;
				}
				
				return current.isFinal();
			}
			
			/**
			 * Checks to see if String s is a word of the given language. Please
			 * refer to recognizesNFA(String s) and recognizesDFA(String s) for specifics
			 * on implementation.
			 * @param s Possible word of the language.
			 * @return True or false depending on if the word is a part of the language 
			 * or not
			 */
			public boolean recognizes(String s)
			{
				return recognizes(s, false);
			}
			
			public boolean recognizes(String s, boolean isDeterministic)
			{
				if(isDeterministic)
					return recognizesDFA(s);
				return recognizesNFA(s);
			}
			
			public int hashCode()
			{
				return name.hashCode();
			}
			
			public int nodeCount()
			{
				return generateNodeList().size();
			}

			public void setNodeBehavior(NodeBehavior behavior) {
				this.behavior = behavior;
			}
			
			public void onEnter()
			{
				if(behavior != null)
					behavior.onEnter();
			}
			
			public void onExit(String s)
			{
				if(behavior != null)
					behavior.onExit(s);
			}
			
			public void update(float deltaTime)
			{
				if(behavior != null)
					behavior.update(deltaTime);
			}
		}