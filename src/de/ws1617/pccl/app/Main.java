package de.ws1617.pccl.app;

import java.io.IOException;

import de.ws1617.pccl.grammar.Grammar;
import de.ws1617.pccl.grammar.GrammarUtils;
import de.ws1617.pccl.grammar.Lexicon;
import de.ws1617.pccl.grammar.NonTerminal;
import de.ws1617.pccl.search.Automaton;

public class Main {

	public static void main(String[] args) {
		//slight change
		// some comment
		// read runtime arguments
		//Input via args[0] usw
		try {
			Grammar grammar = GrammarUtils.readGrammar("input/grammar.txt");
			Lexicon lexicon = GrammarUtils.readLexicon("input/lexicon.txt");
			NonTerminal startSymbol = new NonTerminal("[S]");
			//String input = args[3];
			Automaton auto = new Automaton(grammar,lexicon,startSymbol);
			// TODO create grammar, lexicon, start symbol

			// TODO create an Automaton object

			// TODO print out whether the given input is in the language

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
