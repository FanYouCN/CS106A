/*
 * File: HangmanCanvas.java
 * ------------------------
 * This file keeps track of the Hangman display.
 */

import acm.graphics.*;

import java.awt.font.GlyphJustificationInfo;

public class HangmanCanvas extends GCanvas {

/** Resets the display so that only the scaffold appears */
	public void reset() {
		addScaffold();
        addWordDisplay();
        addIncorrectMessage();
	}


/**
 * Updates the word on the screen to correspond to the current
 * state of the game.  The argument string shows what letters have
 * been guessed so far; unguessed letters are indicated by hyphens.
 */
	public void displayWord(String word) {
        label.setLabel(word);
        add(label);
	}

/**
 * Updates the display to correspond to an incorrect guess by the
 * user.  Calling this method causes the next body part to appear
 * on the scaffold and adds the letter to the list of incorrect
 * guesses that appears at the bottom of the window.
 */
	public void noteIncorrectGuess(char letter) {
        String str = "" + letter;
        numberOfWrongGuesses += 1;
        addBodyPart(numberOfWrongGuesses);

        if (!incorrectMessage.contains(str)) {
            incorrectMessage = incorrectMessage + str;
            message.setLabel(incorrectMessage);
            add(message);
        }
	}

    private void addWordDisplay(){
        label = new GLabel("");
        label.setLocation(getWidth() * 0.1, getHeight() * 0.8);
        label.setFont("SansSerif-40");
    }

    private void addIncorrectMessage(){
        message = new GLabel("");
        message.setLocation(getWidth() * 0.1, getHeight() * 0.9);
        label.setFont("Courier-24");
    }

    private void addBodyPart(int n){
        if (n == 1){
            addHead();
        }else if (n == 2){
            addBody();
        }else if (n == 3){
            addLeftArm();
        }else if (n == 4){
            addRightArm();
        }else if (n == 5){
            addLeftLeg();
        }else if (n == 6){
            addRightLeg();
        }else if (n == 7){
            addLeftFoot();
        }else if (n == 8){
            addRightFoot();
        }
    }

    private void addScaffold(){
        GLine scaffold = new GLine(getWidth()/2 - BEAM_LENGTH,getHeight()/6,getWidth()/2 - BEAM_LENGTH,getHeight()/6 + SCAFFOLD_HEIGHT);
        GLine beam = new GLine(getWidth()/2,getHeight()/6,getWidth()/2 - BEAM_LENGTH,getHeight()/6);
        GLine rope = new GLine(getWidth()/2,getHeight()/6,getWidth()/2,getHeight()/6 + ROPE_LENGTH);
        add(scaffold);
        add(beam);
        add(rope);
    }

    private void addHead(){
        GOval head = new GOval(2 * HEAD_RADIUS, 2 * HEAD_RADIUS);
        head.setLocation(getWidth()/2 - HEAD_RADIUS,getHeight()/6 + ROPE_LENGTH);
        add(head);
    }

    private void addBody(){
        GLine body = new GLine(getWidth()/ 2,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS,getWidth()/2,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
        add(body);
    }

    private void addLeftArm(){
        GLine upperLeftArm = new GLine(getWidth()/2,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,getWidth()/2 - UPPER_ARM_LENGTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
        GLine lowerLeftArm = new GLine(getWidth()/2 - UPPER_ARM_LENGTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth()/2 - UPPER_ARM_LENGTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
        add(upperLeftArm);
        add(lowerLeftArm);
    }

    private void addRightArm(){
        GLine upperRightArm = new GLine(getWidth()/2,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD,getWidth()/2 + UPPER_ARM_LENGTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD);
        GLine lowerRightArm = new GLine(getWidth()/2 + UPPER_ARM_LENGTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD, getWidth()/2 + UPPER_ARM_LENGTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + ARM_OFFSET_FROM_HEAD + LOWER_ARM_LENGTH);
        add(upperRightArm);
        add(lowerRightArm);
    }

    private void addLeftLeg(){
        GLine leftHip = new GLine(getWidth()/2,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,getWidth()/2 - HIP_WIDTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
        GLine leftLeg = new GLine(getWidth()/2 - HIP_WIDTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,getWidth()/2 - HIP_WIDTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
        add(leftHip);
        add(leftLeg);
    }

    private void addRightLeg(){
        GLine rightHip = new GLine(getWidth()/2,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,getWidth()/2 + HIP_WIDTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH);
        GLine rightLeg = new GLine(getWidth()/2 + HIP_WIDTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH,getWidth()/2 + HIP_WIDTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
        add(rightHip);
        add(rightLeg);
    }

    private void addLeftFoot(){
        GLine leftFoot = new GLine(getWidth()/2 - HIP_WIDTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,getWidth()/2 - HIP_WIDTH - FOOT_LENGTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
        add(leftFoot);
    }

    private void addRightFoot(){
        GLine rightFoot = new GLine(getWidth()/2 + HIP_WIDTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH,getWidth()/2 + HIP_WIDTH + FOOT_LENGTH,getHeight() / 6 + ROPE_LENGTH + 2 * HEAD_RADIUS + BODY_LENGTH + LEG_LENGTH);
        add(rightFoot);
    }


/* Constants for the simple version of the picture (in pixels) */
	private static final int SCAFFOLD_HEIGHT = 360;
	private static final int BEAM_LENGTH = 144;
	private static final int ROPE_LENGTH = 18;
	private static final int HEAD_RADIUS = 36;
	private static final int BODY_LENGTH = 144;
	private static final int ARM_OFFSET_FROM_HEAD = 28;
	private static final int UPPER_ARM_LENGTH = 72;
	private static final int LOWER_ARM_LENGTH = 44;
	private static final int HIP_WIDTH = 36;
	private static final int LEG_LENGTH = 108;
	private static final int FOOT_LENGTH = 28;


    private GLabel label;
    private GLabel message;
    private String incorrectMessage = "";
    private int numberOfWrongGuesses = 0;


}
