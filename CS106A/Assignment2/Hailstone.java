/*
 * File: Hailstone.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the Hailstone problem.
 */

import acm.program.*;

public class Hailstone extends ConsoleProgram {
	public void run() {
		int x = readInt("Enter a number: ");
        int n = 0;
        while (true){
            if(x == 1){
                println("The process took " + n + " to reach 1");
                break;
            } else {
                n ++ ;
                if ( x % 2 == 0){
                    println( x + " is even, so I take half: " + x/2);
                    x = x/2;
                } else {
                    println( x + " is odd, so I make 3n + 1: " + (3 * x + 1));
                    x = 3 * x + 1;
                }
            }
        }
	}
}

