/*
 * File: HangmanLexicon.java
 * -------------------------
 * This file contains a stub implementation of the HangmanLexicon
 * class that you will reimplement for Part III of the assignment.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class HangmanLexicon {

/** Returns the number of words in the lexicon. */
    public HangmanLexicon() {
        BufferedReader rd = null;
        try {
            rd = new BufferedReader(new FileReader("HangmanLexicon.txt"));
        } catch (IOException ex){
            System.err.println("invalid file name.");
        }
        try {
            while (rd != null){
                String word = rd.readLine();
                if (word == null) break;
                lexicon.add(word);
            }
        } catch (IOException ex) {
            System.err.println("Error reading file");
        }

    }

	public int getWordCount() {
        return lexicon.size();

	}

/** Returns the word at the specified index. */
	public String getWord(int index) {
        return lexicon.get(index);
	}

    private ArrayList<String> lexicon = new ArrayList<String>();
}
