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
		
		// read runtime arguments
		//Input via args[0] usw
		try {
			// TODO create grammar, lexicon, start symbol
			Grammar grammar = GrammarUtils.readGrammar(args[0]); // reads the path of the grammar
			Lexicon lexicon = GrammarUtils.readLexicon(args[1]); // reads the path of the lexicon
			NonTerminal startSymbol = new NonTerminal(args[2]); // reads the startSymbol
			String input = args[3]; // reads the user input sentence
			
			

			// create an Automaton object
			Automaton auto = new Automaton(grammar,lexicon,startSymbol);

			// print out whether the given input is in the language
			
			if (auto.recognize(input)==true)
                System.out.println("ACCEPTED");
            else
                System.out.println("NOT ACCEPTED");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}