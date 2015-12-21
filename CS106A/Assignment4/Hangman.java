/*
 * File: Hangman.java
 * ------------------
 * This program will eventually play the Hangman game from
 * Assignment #4.
 */

import acm.program.*;
import acm.util.*;

public class Hangman extends ConsoleProgram {

    public static void main(String[] args) {
        new Hangman().start(args);
    }

    /*Constant*/
    private static final int NUMBER_OF_GUESSES = 8;

    public void init(){
        setSize(800,800);
        canvas = new HangmanCanvas();
        add(canvas);
    }

    public void run() {
        setUp();
        playGame();
        endGame();
	}

    private void setUp(){
        gameOver = false;
        canvas.reset();
        int wordNumber = randomGen.nextInt(lexicon.getWordCount());
        word = lexicon.getWord(wordNumber);
        guessedWord = word.replaceAll("[a-zA-Z]","-");
        println("Welcome to Hangman!");
    }

    private void playGame(){
        while(!gameOver){
            println("The word now looks like this: " + guessedWord);
            println("You have " + guessesLeft + " guesses left.");
            canvas.displayWord(guessedWord);
            String guess = readLine("Your guess: ");
            guess = guess.toUpperCase();
            evaluateGuess(guess);
        }
    }

    private void evaluateGuess(String guess){
        if (!isSingleLetter(guess)){
            println("Invalid input, please enter a single letter");
        } else if (word.contains(guess)){
            println("That guess is correct");
            guessedWord = replaceGuessedLetter(guessedWord,word,guess);
        } else {
            println("There are no " + guess + "\'s in the word.");
            char incorrectLetter = guess.charAt(0);
            canvas.noteIncorrectGuess(incorrectLetter);
            guessesLeft -= 1;
        }
        if (guessesLeft == 0 || ! guessedWord.contains("-")){
            gameOver = true;
        }
    }



    private boolean isSingleLetter(String str){
        if(str.length() != 1){
            return false;
        } else{
            char ch = str.charAt(0);
            return Character.isLetter(ch);
        }
    }

    private String replaceGuessedLetter(String guessedWord,String word,String guess){
        while(word.contains(guess)){
            int letterIndex = word.indexOf(guess);
            word = word.substring(0,letterIndex) + "*" + word.substring(letterIndex + 1);
            guessedWord = guessedWord.substring(0,letterIndex) + guess + guessedWord.substring(letterIndex + 1);
        }
        return guessedWord;
    }

    private void endGame(){
        if (guessesLeft != 0){
            println("You guessed the word " + guessedWord);
            canvas.displayWord(word);
            println("You win.");
        } else {
            println("You\'re completely hung.");
            println("The word was " + word);
            println("You lose.");
        }
    }

    /*Instance Variables*/
    private HangmanLexicon lexicon = new HangmanLexicon();
    public RandomGenerator randomGen = RandomGenerator.getInstance();
    private String word;
    private String guessedWord;
    private int guessesLeft = NUMBER_OF_GUESSES;
    private boolean gameOver;

    private HangmanCanvas canvas;
}
