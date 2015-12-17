/*
 * File: Target.java
 * Name: 
 * Section Leader: 
 * -----------------
 * This file is the starter file for the Target problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;

public class Target extends GraphicsProgram {	
	public void run() {
        double r1 = 72;
		GOval red1 = new GOval(r1,r1);
		red1.setColor(Color.red);
        red1.setFilled(true);
        red1.setFillColor(Color.red);
        red1.setLocation((getWidth() - r1)/2 ,(getHeight() - r1)/2);
        add(red1);

        double r2 = 0.65 * 72;
        GOval red2 = new GOval(r2,r2);
        red2.setColor(Color.white);
        red2.setFilled(true);
        red2.setFillColor(Color.white);
        red2.setLocation((getWidth() - r2)/2 ,(getHeight() - r2)/2 );
        add(red2);

        double r3 = 0.3 * 72;
        GOval red3 = new GOval(r3,r3);
        red3.setColor(Color.red);
        red3.setFilled(true);
        red3.setFillColor(Color.red);
        red3.setLocation((getWidth() - r3)/2 ,(getHeight() - r3)/2);
        add(red3);

	}
}
