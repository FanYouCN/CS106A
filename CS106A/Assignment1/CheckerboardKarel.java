/*
 * File: CheckerboardKarel.java
 * ----------------------------
 * When you finish writing it, the CheckerboardKarel class should draw
 * a checkerboard using beepers, as described in Assignment 1.  You
 * should make sure that your program works for all of the sample
 * worlds supplied in the starter folder.
 */

import stanford.karel.*;

public class CheckerboardKarel extends SuperKarel {
    public void run() {
        while(noBeepersPresent()){
            putBeeper();
            moveTwoStepsToNext();
        }

    }

    private void moveOneStepToNext() {
        while (frontIsBlocked()){
            if (facingEast()){
                turnLeft();
            } else if (facingWest()){
                turnRight();
            }
        }
        move();
    }
    private void moveTwoStepsToNext(){
        adjustFaceDirection();
        moveOneStepToNext();
        adjustFaceDirection();
        moveOneStepToNext();
    }

    private void adjustFaceDirection () {
        if (facingNorth()){
            if (rightIsBlocked()){
                turnLeft();
            } else {
                turnRight();
            }
        }
        if (facingSouth()){
            if (rightIsBlocked()){
                turnLeft();
            } else {
                turnRight();
            }
        }
    }
}
