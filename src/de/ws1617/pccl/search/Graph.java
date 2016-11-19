package de.ws1617.pccl.search;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.ws1617.pccl.fsa.Edge;
import de.ws1617.pccl.grammar.Terminal;

public class Graph {

	private ArrayList<HashSet<Edge>> adj;
	private int v;
	// for each state indicate whether it is final
	private boolean[] finalStates;
	
	/**
	 * Initialize the adjacency and final state array.
	 * 
	 * @param v - the number of vertices in the graph.
	 */
	public Graph(int v) {
		this.v = v;
		adj = new ArrayList<HashSet<Edge>>(v); //for each vertex there is a HashSet of edges
		for (int i=0; i < v; i++)
			adj[i] = new HashSet<Edge>(); //initialize all HashSets to empty
		for (int j=0; j < v; j++)
			finalStates[j] = false; //initialize all finale states to false
	}

	/**
	 * add an edge from a vertex
	 * @param from - vertex
	 * @param edge - an adge to add
	 */
	public void addEdge(int from, Edge edge) {
		HashSet<Edge> hashSet = adj.get(from);// get the HashSet
		hashSet.add(edge); //add the edge
		adj.add(from, hashSet); //put the new HashSet in the ArrayList
		
	}

	public HashSet<Edge> getAdjacent(int from) {

		return adj.get(from);
	}

	/**
	 * Returns all edges that point from a certain state to adjacent states and can be reached when consuimg the given {@link Terminal}.
	 * 
	 * @param from the current state.
	 * @param toConsume the next terminal to consume.
	 * @return a set of edges adjacent to the from state reachable via the terminal toConsume.
	 */
	public HashSet<Edge> getAdjacent(int from, Terminal toConsume) {
	
		HashSet<Edge> getAdj = adj.get(from); //HashSet of all edges that point from the current state
		HashSet<Edge> result = new HashSet<Edge>(); //result HashSet
		for (Edge e : getAdj)
		{
			Terminal t = e.getTerminal();
			if (t.equals(toConsume))
				result.add(e);
		}
		return result;
	}

	/**
	 * Make a certain state at the given index a final state.
	 * @param index 
	 */
	public void setFinalState(int index) {
		finalStates[index] = true;
	}

	/**
	 * Checks whether the state at the given index is a final state.
	 * @param index
	 * @return
	 */
	public boolean isFinalState(int index) {
		return finalStates[index];
	}
}