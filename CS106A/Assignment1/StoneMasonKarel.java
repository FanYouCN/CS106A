/*
 * File: StoneMasonKarel.java
 * --------------------------
 * The StoneMasonKarel subclass as it appears here does nothing.
 * When you finish writing it, it should solve the "repair the quad"
 * problem from Assignment 1.  In addition to editing the program,
 * you should be sure to edit this comment so that it no longer
 * indicates that the program does nothing.
 */

import stanford.karel.*;

public class StoneMasonKarel extends SuperKarel {

    public void run() {
        while(frontIsClear()){
            repairColumn();
            returnToBottom();
            moveToNextColumn();
        }
        repairColumn();
        returnToBottom();
    }//main program

    private void repairColumn(){
        turnLeft();
        while(frontIsClear()){
            if (beepersPresent()){
                move();
            } else {
                putBeeper();
                move();
            }
        }
        turnAround();
        if (beepersPresent()){
            move();
        } else {
            putBeeper();
            move();
        }
    }//repair column from bottom to top

    private void returnToBottom(){
        while(frontIsClear()){
            move();
        }
        turnLeft();
    }//return to bottom

    private void moveToNextColumn() {
        move();
        move();
        move();
        move();
    }//move to next column
}
