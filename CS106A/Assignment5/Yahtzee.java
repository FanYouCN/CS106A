/*
 * File: Yahtzee.java
 * ------------------
 * This program will eventually play the Yahtzee game.
 */

import acm.io.*;
import acm.program.*;
import acm.util.*;

public class Yahtzee extends GraphicsProgram implements YahtzeeConstants {
	
	public static void main(String[] args) {
		new Yahtzee().start(args);
	}
	
	public void run() {
		IODialog dialog = getDialog();
		nPlayers = dialog.readInt("Enter number of players");
		playerNames = new String[nPlayers];
		for (int i = 1; i <= nPlayers; i++) {
			playerNames[i - 1] = dialog.readLine("Enter name for player " + i);
		}
		display = new YahtzeeDisplay(getGCanvas(), playerNames);
		playGame();
	}

	private void playGame() {
        initGame();
        for(int i = 0; i < N_SCORING_CATEGORIES; i ++){
            for(int j = 1; j <= nPlayers; j ++){
                playRound(j);
            }
        }
        endGame();
	}

    private void initGame(){
        for (int i = 0; i < MAX_PLAYERS; i++){
            for (int j = 0; j < N_CATEGORIES; j ++){
                categoryScoreExist[i][j] = 0;
                playerScores[i][j] = 0;
            }
        }
    }

    private void playRound(int player){
        initRound();
        firstRoll(player);
        secondRoll(player);
        thirdRoll(player);
        finishRound(player);
    }

    private void endGame(){
        int maxTotalScore = 0;
        int maxPlayer = 0;
        for (int i = 1;i < nPlayers;i ++){
            if (playerScores[i][TOTAL - 1] > maxTotalScore){
                maxTotalScore = playerScores[i][TOTAL - 1];
                maxPlayer = i;
            }
        }
        display.printMessage("Congratulations, " + playerNames[maxPlayer] + ", you\'re the winner with a total score of " + maxTotalScore + "!");
    }

    private void initRound(){
        for(int i = 0; i < N_DICE; i ++){
            diceToRoll[i] = 1;
        }
    }

    private void firstRoll(int player){
        display.printMessage(playerNames[player - 1] + "\'s first round! Click \"Roll Dice\" to roll the dice.");
        display.waitForPlayerToClickRoll(player);
        rollDice(diceToRoll);
        display.displayDice(dice);
    }

    private void secondRoll(int player){
        display.printMessage(playerNames[player - 1] + "'s second roll! Select the dice you wish to re-roll and click \"Roll Again\".");
        selectDiceToRoll();
        rollDice(diceToRoll);
        display.displayDice(dice);
    }

    private void thirdRoll(int player){
        display.printMessage(playerNames[player - 1] + "'s third roll! Select the dice you wish to re-roll and click \"Roll Again\".");
        selectDiceToRoll();
        rollDice(diceToRoll);
        display.displayDice(dice);
    }

    private void rollDice(int[] diceToRoll){
        for(int i = 0; i < N_DICE; i ++){
            if (diceToRoll[i] == 1){
                dice[i] = randomGenerator.nextInt(1,6);
            }
        }
    }

    private void selectDiceToRoll(){
        display.waitForPlayerToSelectDice();
        for(int i = 0; i < N_DICE; i ++){
            if(display.isDieSelected(i)){
                diceToRoll[i] = 1;
            } else diceToRoll[i] = 0;
        }
    }

    private void finishRound(int player){
        display.printMessage("You have finished this round, " + playerNames[player - 1] + "! Select a category for this round.");
        while(true){
            int categorySelected = display.waitForPlayerToSelectCategory();
            if(categoryScoreExist[player - 1][categorySelected] == 1){
                display.printMessage("Invalid selection! You have already choose this category. Please select a different category.");
            } else{
                categoryScoreExist[player - 1][categorySelected] = 1;
                int score = checkScore(categorySelected,dice);
                playerScores[player - 1][categorySelected - 1] = score;
                display.updateScorecard(categorySelected,player,score);
                updateUpperScore(player);
                updateUpperBonus(player);
                updateLowerScore(player);
                updateTotalScore(player);
                break;
            }
        }
    }

    private int checkScore(int category, int[] dice){
        int score = 0;
        switch(category){
            case ONES: score = scoreOnes(dice);
                break;
            case TWOS: score = scoreTwos(dice);
                break;
            case THREES:score = scoreThrees(dice);
                break;
            case FOURS: score = scoreFours(dice);
                break;
            case FIVES: score = scoreFives(dice);
                break;
            case SIXES: score = scoreSixes(dice);
                break;
            case THREE_OF_A_KIND: score = scoreThreeOfAKind(dice);
                break;
            case FOUR_OF_A_KIND: score = scoreFourOfAKind(dice);
                break;
            case FULL_HOUSE: score = scoreFullHouse(dice);
                break;
            case SMALL_STRAIGHT: score = scoreSmallStraight(dice);
                break;
            case LARGE_STRAIGHT: score = scoreLargeStraight(dice);
                break;
            case YAHTZEE: score = scoreYahtzee(dice);
                break;
            case CHANCE: score = scoreChance(dice);
                break;
        }
        return score;
    }

    private int scoreOnes(int[] dice){
        int score = 0;
        for(int i = 0;i < N_DICE;i ++){
            if (dice[i] == 1) score += 1;
        }
        return score;
    }

    private int scoreTwos(int[] dice){
        int score = 0;
        for(int i = 0;i < N_DICE;i ++){
            if (dice[i] == 2) score += 2;
        }
        return score;
    }

    private int scoreThrees(int[] dice){
        int score = 0;
        for(int i = 0;i < N_DICE;i ++){
            if (dice[i] == 3) score += 3;
        }
        return score;
    }

    private int scoreFours(int[] dice){
        int score = 0;
        for(int i = 0;i < N_DICE;i ++){
            if (dice[i] == 4) score += 4;
        }
        return score;
    }

    private int scoreFives(int[] dice){
        int score = 0;
        for(int i = 0;i < N_DICE;i ++){
            if (dice[i] == 5) score += 5;
        }
        return score;
    }

    private int scoreSixes(int[] dice){
        int score = 0;
        for(int i = 0;i < N_DICE;i ++){
            if (dice[i] == 6) score += 6;
        }
        return score;
    }

    private int scoreThreeOfAKind(int[] dice){
        int score = 0;
        int[] numberOfSides = new int[6];
        for (int i = 0; i < N_DICE; i++){
            numberOfSides[dice[i] - 1] += 1;
        }
        int maxOccur = getMaxOccur(numberOfSides);
        if (maxOccur >= 3){
            for (int i = 0; i < N_DICE; i++){
                score += dice[i];
            }
        }
        return score;
    }

    private int scoreFourOfAKind(int[] dice){
        int score = 0;
        int[] numberOfSideOccur = new int[6];
        for (int i = 0; i < N_DICE; i++){
            numberOfSideOccur[dice[i] - 1] += 1;
        }
        int maxOccur = getMaxOccur(numberOfSideOccur);
        if (maxOccur >= 4){
            for (int i = 0; i < N_DICE; i++){
                score += dice[i];
            }
        }
        return score;
    }

    private int scoreFullHouse(int[] dice){
        int score = 0;
        int[] numberOfSideOccur = new int[6];
        for (int i = 0; i < N_DICE; i++){
            numberOfSideOccur[dice[i] - 1] += 1;
        }
        int maxOccur = getMaxOccur(numberOfSideOccur);
        int secondMaxOccur = getSecondMaxOccur(numberOfSideOccur);
        if (maxOccur == 3 && secondMaxOccur == 2) score = 25;
        return score;
    }

    private int scoreSmallStraight(int[] dice){
        int score = 0;
        int[] numberOfSideOccur = new int[6];
        for (int i = 0; i < N_DICE; i++){
            numberOfSideOccur[dice[i] - 1] += 1;
        }
        int[] checkStraight = new int[3];
        for (int i = 0; i < 3; i ++){
            checkStraight[i] = numberOfSideOccur[i];
            for (int j = i + 1;j < i + 4;j ++){
                checkStraight[i] *= numberOfSideOccur[j];
            }
        }
        if (getMaxOccur(checkStraight) >= 1) score = 30;
        return score;
    }

    private int scoreLargeStraight(int[] dice){
        int score = 0;
        int[] numberOfSideOccur = new int[6];
        for (int i = 0; i < N_DICE; i++){
            numberOfSideOccur[dice[i] - 1] += 1;
        }
        int[] checkStraight = new int[2];
        for (int i = 0; i < 2; i ++){
            checkStraight[i] = numberOfSideOccur[i];
            for (int j = i + 1;j < i + 5;j ++){
                checkStraight[i] *= numberOfSideOccur[j];
            }
        }
        if (getMaxOccur(checkStraight) >= 1) score = 40;
        return score;
    }

    private int scoreYahtzee(int[] dice){
        int score = 0;
        int[] numberOfSideOccur = new int[6];
        for (int i = 0; i < N_DICE; i++){
            numberOfSideOccur[dice[i] - 1] += 1;
        }
        int maxOccur = getMaxOccur(numberOfSideOccur);
        if (maxOccur == 5){
            score = 50;
        }
        return score;
    }

    private int scoreChance(int[] dice){
        int score = 0;
        for (int i = 0;i < N_DICE;i++){
            score += dice[i];
        }
        return score;
    }

    private int getMaxOccur(int[] arr){
        int max = arr[0];
        for (int i = 1;i < arr.length;i ++){
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    private int getSecondMaxOccur(int[] arr){
        int secondMax = arr[0];
        int max = getMaxOccur(arr);
        for (int i = 1;i < arr.length; i ++){
            if (arr[i] > secondMax && arr[i] < max) secondMax = arr[i];
        }
        return secondMax;
    }

    private void updateUpperScore(int player){
        int upperScore = playerScores[player -1][ONES - 1] + playerScores[player - 1][TWOS - 1] + playerScores[player - 1][THREES - 1] + playerScores[player - 1][FOURS - 1] + playerScores[player - 1][FIVES - 1] + playerScores[player - 1][SIXES - 1];
        playerScores[player - 1][UPPER_SCORE - 1] = upperScore;
        display.updateScorecard(UPPER_SCORE,player,upperScore);
    }

    private void updateUpperBonus(int player){
        int upperBonus = 0;
        if (playerScores[player - 1][UPPER_SCORE - 1] >= 63){
            upperBonus = 35;
            playerScores[player - 1][UPPER_BONUS - 1] = upperBonus;
        }
        display.updateScorecard(UPPER_BONUS,player,upperBonus);
    }

    private void updateLowerScore(int player){
        int lowerScore = playerScores[player - 1][THREE_OF_A_KIND - 1] + playerScores[player - 1][FOUR_OF_A_KIND - 1] + playerScores[player - 1][FULL_HOUSE - 1] + playerScores[player - 1][SMALL_STRAIGHT - 1] + playerScores[player - 1][LARGE_STRAIGHT - 1] + playerScores[player - 1][YAHTZEE - 1] + playerScores[player - 1][CHANCE - 1];
        playerScores[player - 1][LOWER_SCORE - 1] = lowerScore;
        display.updateScorecard(LOWER_SCORE,player,lowerScore);
    }

    private void updateTotalScore(int player){
        int totalScore = playerScores[player - 1][UPPER_SCORE - 1] + playerScores[player - 1][UPPER_BONUS - 1] + playerScores[player - 1][LOWER_SCORE - 1];
        playerScores[player - 1][TOTAL - 1] = totalScore;
        display.updateScorecard(TOTAL,player,totalScore);
    }




/* Private instance variables */
	private int nPlayers;
	private String[] playerNames;
	private YahtzeeDisplay display;
	private RandomGenerator randomGenerator = new RandomGenerator();
    private int[] dice = new int[N_DICE];
    private int[] diceToRoll = new int[N_DICE];
    private int[][] categoryScoreExist = new int[MAX_PLAYERS][N_CATEGORIES];
    private int[][] playerScores = new int[MAX_PLAYERS][N_CATEGORIES];


}
