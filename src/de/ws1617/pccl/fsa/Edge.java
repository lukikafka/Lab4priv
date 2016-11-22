package de.ws1617.pccl.fsa;

import de.ws1617.pccl.grammar.*;

/**
 * A directed edge (transition) between states.
 *
 */
public class Edge {

	int goal; 
	Terminal toConsume;
	
	/**
	 * Constructor for the class Edge
	 */
	public Edge (int goal, Terminal toConsume)
	{
		this.goal = goal;
		this.toConsume = toConsume;
	}
	
	public Terminal getTerminal()
	{
		return toConsume;
	}
	
	public int getGoal ()
	{
		return goal;
	}
	
}
