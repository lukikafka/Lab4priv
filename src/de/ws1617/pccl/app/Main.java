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
			Grammar grammar = GrammarUtils.readGrammar("input/grammar.txt");
			Lexicon lexicon = GrammarUtils.readLexicon("input/lexicon.txt");
			NonTerminal startSymbol = new NonTerminal("[S]");
			String input = args[3];
			
			
			

			// TODO create an Automaton object
			Automaton auto = new Automaton(grammar,lexicon,startSymbol);

			// TODO print out whether the given input is in the language
			
			if (obj.recognize(input)==true)
                System.out.println("ACCEPTED");
            else
                System.out.println("NOT ACCEPTED");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
