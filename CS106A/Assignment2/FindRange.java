/*
 * File: FindRange.java
 * Name: 
 * Section Leader: 
 * --------------------
 * This file is the starter file for the FindRange problem.
 */

import acm.program.*;

public class FindRange extends ConsoleProgram {

    private static final int SENTINEL_VALUE = 0;

	public void run() {
        println("This program finds the largest and smallest numbers.");

        int x = readInt("? ");
        if(x == SENTINEL_VALUE){
            println("No values have been entered.");
        } else {
            int max = x;
            int min = x;

            while(true){
                int a = readInt("? ");
                if (a == SENTINEL_VALUE){
                    println("smallest:" + min);
                    println("largest:" + max);
                    break;
                } else{
                    if (a > max){
                        max = a;
                    }
                    if (a < min){
                        min = a;
                    }
                }
            }
        }

	}
}

