package de.ws1617.pccl.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import de.ws1617.pccl.fsa.Edge;
import de.ws1617.pccl.grammar.Grammar;
import de.ws1617.pccl.grammar.Lexicon;
import de.ws1617.pccl.grammar.NonTerminal;
import de.ws1617.pccl.grammar.Symbol;
import de.ws1617.pccl.grammar.Terminal;

public class Automaton {

	private Stack<Hypothesis> agenda;

	private List<NonTerminal> nonTerminals;

	private NonTerminal startSymbol;

	private Graph graph;

	public Automaton(Grammar grammar, Lexicon lexicon, NonTerminal startSymbol) {
		super();
		
		this.startSymbol = startSymbol; 

		Set<NonTerminal> allNonTerminals = grammar.getNonTerminals();
		allNonTerminals.addAll(lexicon.getNonTerminals());
		nonTerminals = new ArrayList<>();
        nonTerminals.add(startSymbol);
        allNonTerminals.remove(startSymbol);
        nonTerminals.addAll(allNonTerminals);
		
		graph = new Graph (nonTerminals.size() + 1); //a state for every nonterminal plus one final state
		addRules (grammar, lexicon);
	}

	/**
	 * Returns whether the give input is licensed by the grammar or not.
	 * 
	 * @param input
	 * @return
	 */
	public boolean recognize(String input){
		Hypothesis first = new Hypothesis (0, 0);
		agenda = new Stack<>();
		agenda.addAll(successors(first,initialize(input))); //adds all successors of the first hypothesis
		while (!agenda.isEmpty()){ // while  loop till the agenda with hypothesises is emtpy 
			Hypothesis h = agenda.pop();
			if (isFinalState(h,initialize(input))) // checks if hypothesis is final state
				return true; // returns true if so
			for (Hypothesis workH:successors(h,initialize(input))){ //goes over all hypothesises from the successors
				if (workH.getInputIndex()<=initialize(input).size()) //checks if the actuall index is lower or equal to the size of words
					agenda.add(workH);
			}
		}
		return false;
	}
	/**
	 * Generates all successors for a given hypothesis and input.
	 * 
	 * @param h
	 * @param input
	 * @return
	 */
	private ArrayList<Hypothesis> successors(Hypothesis h, ArrayList<Terminal> input) {

		ArrayList<Hypothesis> result = new ArrayList<>();
		Hypothesis workingHyp = h; // stores the actual hypothesis in a new variable
		int inputIndex = workingHyp.getInputIndex(); //stores the index of the actuall hypothesis
		if (inputIndex < input.size()) //checks if the index is before the end of the sentence
		{
		HashSet<Edge> edges = graph.getAdjacent(workingHyp.getState(), input.get(inputIndex)); //check if there are successors
		if (!edges.isEmpty()) // checks if there are edges to process
		{

			for (Edge edge : edges)
			{
				workingHyp = new Hypothesis (edge.getGoal(), inputIndex+1);//create a new hypothesis 
				result.add(workingHyp);//add the hypothesis as a successor
			}
		}}
		return result;
	}

	/**
	 * Initializes the agenda and prepares the input by splitting it and making
	 * terminals from a string..
	 * 
	 * @param s
	 *            the input string to be processed.
	 * @return a list of terminals based on the input s split by whitespaces.
	 */
	private ArrayList<Terminal> initialize(String s) {
		
		String [] splited = s.split(" ");
		ArrayList<Terminal> result = new ArrayList<>();
		for (int i = 0; i < splited.length; i++)
		{
			Terminal t = new Terminal (splited[i].toLowerCase());
			result.add(i, t);
		}
			
		return result;
		
	}

	/**
	 * Checks whether for a given hypothesis and input the automaton is in a
	 * final state and licenses the string. Two conditions have to be met: (a)
	 * all symbols have been processed (b) the current state is final.
	 * 
	 * @param h
	 * @param input
	 * @return
	 */
	public boolean isFinalState(Hypothesis h, List<Terminal> input) {
		
		return (graph.isFinalState(h.getState()) && (h.getInputIndex()==(input.size())));
	}

	/**
	 * Adds edges for the rules to the automaton based on the grammar and
	 * lexicon.
	 * 
	 * @param gr
	 *            a Grammar.
	 * @param lex
	 *            a Lexicon.
	 */
	public void addRules(Grammar gr, Lexicon lex) {

		for (int i = 0; i < nonTerminals.size(); i++) //for every NonTerminal
		{
			HashSet<ArrayList<Symbol>> lhs = gr.getRuleForLHS (nonTerminals.get(i)); // get grammar derivations for the NonTerminal
			
			for (ArrayList<Symbol> list : lhs) //for every derivation
			{
				Symbol terminal = list.get(0);
				Symbol nonterminal = list.get(1);
				Edge edge = new Edge (nonTerminals.indexOf(nonterminal), (Terminal) terminal); //create an edge for the rule
				graph.addEdge(i,edge);	
			}
			
			HashSet<ArrayList<Terminal>> term = lex.getRules (nonTerminals.get(i)); //get lexical rules
			
			if (term != null)
			{
				for (ArrayList<Terminal> list : term) //for every rule
				{
					Terminal thisTerminal = list.get(0);
					Edge edge = new Edge (nonTerminals.size(), thisTerminal); //greate an edge to the final state
					graph.addEdge (i, edge); //add the edge to the final state
					graph.setFinalState(nonTerminals.size()); //set the state final
				}
			}
		}

	}

}