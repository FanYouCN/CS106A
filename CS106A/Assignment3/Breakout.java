/*
 * File: Breakout.java
 * -------------------
 * Name:
 * Section Leader:
 * 
 * This file will eventually implement the game of Breakout.
 */

import acm.graphics.*;
import acm.program.*;
import acm.util.*;

import java.awt.*;
import java.awt.event.*;

public class Breakout extends GraphicsProgram {

    public static void main(String[] args) {
        new Breakout().start(args);
    }

/** Width and height of application window in pixels */
	public static final int APPLICATION_WIDTH = 400;
	public static final int APPLICATION_HEIGHT = 600;

/** Dimensions of game board (usually the same) */
	private static final int WIDTH = APPLICATION_WIDTH;
	private static final int HEIGHT = APPLICATION_HEIGHT;

/** Dimensions of the paddle */
	private static final int PADDLE_WIDTH = 60;
	private static final int PADDLE_HEIGHT = 10;

/** Offset of the paddle up from the bottom */
	private static final int PADDLE_Y_OFFSET = 30;

/** Number of bricks per row */
	private static final int NBRICKS_PER_ROW = 10;

/** Number of rows of bricks */
	private static final int NBRICK_ROWS = 10;

/** Separation between bricks */
	private static final int BRICK_SEP = 4;

/** Width of a brick */
	private static final int BRICK_WIDTH =
	  (WIDTH - (NBRICKS_PER_ROW - 1) * BRICK_SEP) / NBRICKS_PER_ROW;

/** Height of a brick */
	private static final int BRICK_HEIGHT = 8;

/** Radius of the ball in pixels */
	private static final int BALL_RADIUS = 10;

/** Offset of the top brick row from the top */
	private static final int BRICK_Y_OFFSET = 70;

/** Number of turns */
	private static final int NTURNS = 3;

    private static final int DELAY = 1;

/* Method: run() */
/** Runs the Breakout program. */
	public void run() {
		setUp();
        while(true){
            moveBall();
            checkForCollisions();
            pause(DELAY);
        }
	}

    private void setUp(){
        setUpBricks();
        setUpPaddle();
        addBall();
        startBall();
    }

    private void setUpBricks(){
        for (int i = 0; i < NBRICK_ROWS; i ++ ){
            if (i % 10 < 2){
                addRow(Color.red,BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP));
            }else if (i % 10 < 4){
                addRow(Color.orange,BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP));
            }else if (i % 10 < 6){
                addRow(Color.yellow,BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP));
            }else if (i % 10 < 8){
                addRow(Color.green,BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP));
            }else if (i % 10 < 10){
                addRow(Color.cyan,BRICK_Y_OFFSET + i * (BRICK_HEIGHT + BRICK_SEP));
            }
        }
    }

    private void addRow(Color color,int y ){
        for (int i = 0; i < NBRICKS_PER_ROW; i ++){
            int leftOverSpace = (WIDTH - (NBRICKS_PER_ROW * BRICK_WIDTH + (NBRICKS_PER_ROW - 1) * BRICK_SEP)) / 2;
            addBrick(i * (BRICK_WIDTH + BRICK_SEP) + leftOverSpace, y, color);
        }
    }

    private void addBrick(int x, int y, Color color){
        GRect newBrick = new GRect(x, y, BRICK_WIDTH, BRICK_HEIGHT);
        newBrick.setFilled(true);
        newBrick.setColor(color);
        add(newBrick);
    }

    private void setUpPaddle(){
        addPaddle();
        addMouseListeners();
    }

    private void addPaddle(){
        paddleLocationY = HEIGHT - PADDLE_Y_OFFSET - PADDLE_HEIGHT;
        paddle = new GRect((WIDTH - PADDLE_WIDTH) / 2, paddleLocationY,PADDLE_WIDTH,PADDLE_HEIGHT);
        paddle.setFilled(true);
        paddle.setFillColor(Color.black);
        add(paddle);
    }

    public void mouseMoved(MouseEvent e){
        if (e.getX() < 0){
            paddle.setLocation(0,paddleLocationY);
        } else if (e.getX() < WIDTH - PADDLE_WIDTH){
            paddle.setLocation(e.getX(),paddleLocationY);
        } else {
            paddle.setLocation(WIDTH - PADDLE_WIDTH,paddleLocationY);
        }
    }


    private void addBall(){
        ball = new GOval((WIDTH - 2 * BALL_RADIUS) / 2,(HEIGHT - 2 * BALL_RADIUS) / 2, 2 * BALL_RADIUS, 2 * BALL_RADIUS);
        ball.setFilled(true);
        ball.setFillColor(Color.black);
        add(ball);
    }

    private void startBall(){
        vx = randomGen.nextDouble(0.1, 0.3);
        if (randomGen.nextBoolean(0.5)){
            vx = -vx;
        }
        vy = 0.3;
    }

    private void moveBall(){
        if (ball.getX() < 0){
            ball.setLocation(0,ball.getY());
            vx = -vx;
        }
        if (ball.getX() > WIDTH - 2 * BALL_RADIUS){
            ball.setLocation(WIDTH - 2* BALL_RADIUS,ball.getY());
            vx = -vx;
        }
        if (ball.getY() < 0){
            ball.setLocation(ball.getX(),0);
            vy = -vy;
        }
        if (ball.getY() > HEIGHT - 2 * BALL_RADIUS){
            ball.setLocation(ball.getX(),HEIGHT - 2 * BALL_RADIUS);
            vy = -vy;
        }
        ball.move(vx,vy);
    }

    private void checkForCollisions(){
        GObject collider = getCollidingObject();
        if (collider != null){
            vy = -vy;
            if(collider == paddle){
                ball.setLocation(ball.getX(),paddleLocationY - 2 * BALL_RADIUS - 3);
            } else {
                remove(collider);
            }
        }
    }

    private GObject getCollidingObject(){
        if (getElementAt(ball.getX(),ball.getY()) != null){
            return getElementAt(ball.getX(),ball.getY());
        } else if (getElementAt(ball.getX() + 2 * BALL_RADIUS,ball.getY()) != null){
            return getElementAt(ball.getX() + 2 * BALL_RADIUS,ball.getY());
        } else if (getElementAt(ball.getX() + 2 * BALL_RADIUS,ball.getY() + 2 * BALL_RADIUS) != null){
            return getElementAt(ball.getX() + 2 * BALL_RADIUS,ball.getY() + 2 * BALL_RADIUS);
        } else if (getElementAt(ball.getX(),ball.getY() + 2 * BALL_RADIUS) != null){
            return getElementAt(ball.getX(),ball.getY() + 2 * BALL_RADIUS);
        } else return null;
    }


    private int paddleLocationY;
    private GRect paddle;
    private GOval ball;
    private RandomGenerator randomGen = RandomGenerator.getInstance();
    private double vx,vy;
}








































