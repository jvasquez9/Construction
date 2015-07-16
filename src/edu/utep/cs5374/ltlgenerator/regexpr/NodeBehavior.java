package edu.utep.cs5374.ltlgenerator.regexpr;
/**
 * This interface allows us to attach behaviors to nodes upon transitions. 
 * There is an onEnter(), update(float dt),and onExit(String s) methods. The 
 * first is called when the state is entered, the second can be called during 
 * rendering cycles, and the third is called when exiting the state. Node’s 
 * setNodeBehavior(NodeBehavior nb) is used to associate behaviors with nodes.
 * 
 * @author Robert McCain
 * @since 12/2/2014
 * @version 1.0
 *
 */

public interface NodeBehavior {
	public void onEnter();
	public void update(float deltaTime);
	public void onExit(String s);
}
