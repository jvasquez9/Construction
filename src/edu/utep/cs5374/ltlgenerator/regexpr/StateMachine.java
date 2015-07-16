package edu.utep.cs5374.ltlgenerator.regexpr;

import java.util.List;

/**
 * A state machine contains a Language l which is the minimization of the regular 
 * expression passed through the constructor. It also contains the node the state 
 * machine is currently on (called current). There is a traverse method that 
 * allows the machine to traverse to a new state and invokes method calls of the 
 * associated NodeBehavior, and an attachBehaviors() method that allows 
 * NodeBehaviors to be added to nodes within the graph (*Note: need to create a 
 * better way to attach behaviors to the nodes. In its current implementation you 
 * cannot make NodeBehavior assignments optional on a per node basis). 
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */

public class StateMachine {
	private Language l;	//Language the machine recognizes
	private Node current; //The current node we are on
	
	public StateMachine(String regexpr)
	{
		l = DFAMinimizerFactory.generate(regexpr);
		current = l.getRoot();
	}
	
	public void attachBehaviors(String traversalWord, List<NodeBehavior> behaviorList)
	{
		//Precondition, ensuring that traversalWord and behaviorList are compatible
		assert traversalWord.length() + 1 == behaviorList.size();
		//We start from the root and begin attaching behaviors
		Node tmp = l.getRoot();
		tmp.setNodeBehavior(behaviorList.get(0));
		//In the loop below we traverse the graph, attaching behaviors as we go.
		for(int i = 0; i < traversalWord.length(); i++)
		{
			tmp = tmp.traverseEdge(traversalWord.charAt(i) + "");
			tmp.setNodeBehavior(behaviorList.get(i+1));
		}
		//Finally, we invoke root's onEnter() method.
		l.getRoot().onEnter();
	}
	
	public void traverse(String s)
	{
		current.onExit(s);
		current = current.traverseEdge(s);
		current.onEnter();
	}
	
	public void update(float deltaTime)
	{
		current.update(deltaTime);
	}
	
	public Node getCurrent()
	{
		return current;
	}
}
