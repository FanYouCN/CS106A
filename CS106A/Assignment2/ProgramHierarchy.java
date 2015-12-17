/*
 * File: ProgramHierarchy.java
 * Name:
 * Section Leader:
 * ---------------------------
 * This file is the starter file for the ProgramHierarchy problem.
 */

import acm.graphics.*;
import acm.program.*;
import java.awt.*;



public class ProgramHierarchy extends GraphicsProgram {

    private static final int BOX_WIDTH = 120;
    private static final int BOX_HEIGHT = 30;

    public void run() {
        GRect ProgramBox = new GRect(BOX_WIDTH,BOX_HEIGHT);
        GRect GraphicsProgramBox = new GRect(BOX_WIDTH,BOX_HEIGHT);
        GRect ConsoleProgramBox = new GRect(BOX_WIDTH,BOX_HEIGHT);
        GRect DialogProgramBox = new GRect(BOX_WIDTH,BOX_HEIGHT);

        ProgramBox.setLocation(getWidth()/2 - BOX_WIDTH/2,getHeight() / 2 - BOX_HEIGHT * 2.1);
        GraphicsProgramBox.setLocation(getWidth()/2 - 1.7 * BOX_WIDTH,getHeight() / 2 + BOX_HEIGHT * 0.3);
        ConsoleProgramBox.setLocation(getWidth()/2 - 0.5 * BOX_WIDTH,getHeight() / 2 + BOX_HEIGHT * 0.3);
        DialogProgramBox.setLocation(getWidth()/2 + 0.7 * BOX_WIDTH,getHeight() /2  + BOX_HEIGHT * 0.3);

        double ProgramBoxCenterX = ProgramBox.getLocation().getX() + BOX_WIDTH / 2;
        double ProgramBoxCenterY = ProgramBox.getLocation().getY() + BOX_HEIGHT;
        double GraphicsProgramBoxCenterX = GraphicsProgramBox.getLocation().getX() + BOX_WIDTH / 2;
        double GraphicsProgramBoxCenterY = GraphicsProgramBox.getLocation().getY();
        double ConsoleProgramBoxCenterX = ConsoleProgramBox.getLocation().getX() + BOX_WIDTH / 2;
        double ConsoleProgramBoxCenterY = ConsoleProgramBox.getLocation().getY();
        double DialogProgramBoxCenterX = DialogProgramBox.getLocation().getX() + BOX_WIDTH / 2;
        double DialogProgramBoxCenterY = DialogProgramBox.getLocation().getY();

        GLabel ProgramLabel = new GLabel("Program");
        GLabel GraphicsProgramLabel = new GLabel("GraphicsProgram");
        GLabel ConsoleProgramLabel = new GLabel("ConsoleProgram");
        GLabel DialogProgramLabel = new GLabel("DialogProgram");

        ProgramLabel.setLocation(ProgramBox.getLocation());
        GraphicsProgramLabel.setLocation(GraphicsProgramBox.getLocation());
        ConsoleProgramLabel.setLocation(ConsoleProgramBox.getLocation());
        DialogProgramLabel.setLocation(DialogProgramBox.getLocation());

        ProgramLabel.move((BOX_WIDTH - ProgramLabel.getWidth())/ 2,(BOX_HEIGHT + ProgramLabel.getAscent())/ 2 );
        GraphicsProgramLabel.move((BOX_WIDTH - GraphicsProgramLabel.getWidth())/ 2,(BOX_HEIGHT + GraphicsProgramLabel.getAscent())/ 2 );
        ConsoleProgramLabel.move((BOX_WIDTH - ConsoleProgramLabel.getWidth())/ 2,(BOX_HEIGHT + ConsoleProgramLabel.getAscent())/ 2 );
        DialogProgramLabel.move((BOX_WIDTH - DialogProgramLabel.getWidth())/ 2,(BOX_HEIGHT + DialogProgramLabel.getAscent())/ 2 );

        GLine ProgramGraphicsProgramLine = new GLine(ProgramBoxCenterX,ProgramBoxCenterY,GraphicsProgramBoxCenterX,GraphicsProgramBoxCenterY);
        GLine ProgramConsoleProgramLine = new GLine(ProgramBoxCenterX,ProgramBoxCenterY,ConsoleProgramBoxCenterX,ConsoleProgramBoxCenterY);
        GLine ProgramDialogProgramLine = new GLine(ProgramBoxCenterX,ProgramBoxCenterY,DialogProgramBoxCenterX,DialogProgramBoxCenterY);

        add(ProgramBox);
        add(GraphicsProgramBox);
        add(ConsoleProgramBox);
        add(DialogProgramBox);

        add(ProgramLabel);
        add(GraphicsProgramLabel);
        add(ConsoleProgramLabel);
        add(DialogProgramLabel);

        add(ProgramGraphicsProgramLine);
        add(ProgramConsoleProgramLine);
        add(ProgramDialogProgramLine);
    }
}

