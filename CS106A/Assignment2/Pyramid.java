/*
 * File: Pyramid.java
 * Name:
 * Section Leader:
 * ------------------
 * This file is the starter file for the Pyramid problem.
 * It includes definitions of the constants that match the
 * sample run in the assignment, but you should make sure
 * that changing these values causes the generated display
 * to change accordingly.
 */

import acm.graphics.*;
		import acm.program.*;
		import java.awt.*;

public class Pyramid extends GraphicsProgram {

	/** Width of each brick in pixels */
	private static final int BRICK_WIDTH = 30;

	/** Width of each brick in pixels */
	private static final int BRICK_HEIGHT = 12;

	/** Number of bricks in the base of the pyramid */
	private static final int BRICKS_IN_BASE = 14;

	public void run() {
		for (int row = BRICKS_IN_BASE; row > 0; row--){
            for (int column = 1;column <= row;column++){
                double x = (getWidth() - row * BRICK_WIDTH) / 2 + (column - 1) * BRICK_WIDTH ;
                double y = getHeight() - (BRICKS_IN_BASE - row + 1) * BRICK_HEIGHT;
                addRect(x,y);
            }
        }
	}

    private void addRect(double x, double y) {
        GRect brick = new GRect(BRICK_WIDTH,BRICK_HEIGHT);
        brick.setLocation(x, y);
        add(brick);
    }

}

