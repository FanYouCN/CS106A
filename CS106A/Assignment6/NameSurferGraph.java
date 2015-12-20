/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes or the window is resized.
 */

import acm.graphics.*;
import java.awt.event.*;
import java.util.*;
import java.awt.*;

public class NameSurferGraph extends GCanvas
	implements NameSurferConstants, ComponentListener {

	/**
	* Creates a new NameSurferGraph object that displays the data.
	*/
	public NameSurferGraph() {
		addComponentListener(this);
        update();
	}
	
	/**
	* Clears the list of name surfer entries stored inside this class.
	*/
	public void clear() {
        list.clear();
	}
	
	/* Method: addEntry(entry) */
	/**
	* Adds a new NameSurferEntry to the list of entries on the display.
	* Note that this method does not actually draw the graph, but
	* simply stores the entry; the graph is drawn by calling update.
	*/
	public void addEntry(NameSurferEntry entry) {
        list.add(entry);
	}
	
	
	
	/**
	* Updates the display image by deleting all the graphical objects
	* from the canvas and then reassembling the display according to
	* the list of entries. Your application must call update after
	* calling either clear or addEntry; update is also called whenever
	* the size of the canvas changes.
	*/
	public void update() {
        removeAll();
        drawBackground();
        for (int i = 0; i < list.size();i ++){
            if ( i % 4 == 0){
                drawEntry(list.get(i),Color.black);
            } else if (i % 4 == 1){
                drawEntry(list.get(i),Color.red);
            } else if (i % 4 == 2){
                drawEntry(list.get(i),Color.blue);
            } else if (i % 4 == 3){
                drawEntry(list.get(i),Color.magenta);
            }
        }
	}

    private void drawBackground(){
        drawTopLine();
        drawBottomLine();
        for (int i = 0;i < NDECADES;i++){
            double xPosition = getWidth() / NDECADES * i;
            drawVerticalLine(xPosition);
            int decade = START_DECADE + i * 10;
            drawDecadeLabel(decade,xPosition);
        }
    }


    private void drawTopLine(){
        GLine topLine = new GLine(0, GRAPH_MARGIN_SIZE,getWidth(),GRAPH_MARGIN_SIZE);
        add(topLine);
    }

    private void drawBottomLine(){
        GLine bottomLine = new GLine(0,getHeight() - GRAPH_MARGIN_SIZE,getWidth(),getHeight() - GRAPH_MARGIN_SIZE);
        add(bottomLine);
    }

    private void drawVerticalLine(double xPosition){
        GLine line = new GLine(xPosition,0,xPosition,getHeight());
        add(line);
    }

    private void drawDecadeLabel(int decade,double xPosition){
        GLabel decadeLabel = new GLabel(Integer.toString(decade));
        decadeLabel.setLocation(xPosition + 5,getHeight() - GRAPH_MARGIN_SIZE + decadeLabel.getAscent());
        add(decadeLabel);
    }

    private void drawEntry(NameSurferEntry entry, Color color){
        String name = entry.getName();
        for (int i = 1; i < NDECADES; i ++){
            int rank_current = entry.getRank(i);
            if (rank_current == 0) rank_current = 1000;
            int rank_previous = entry.getRank(i - 1);
            if (rank_previous == 0) rank_previous = 1000;
            double xStart = getWidth() / NDECADES * (i - 1);
            double xEnd = getWidth() / NDECADES * i;
            double yStart = (getHeight() - 2 * GRAPH_MARGIN_SIZE) * (rank_previous) / MAX_RANK + GRAPH_MARGIN_SIZE;
            double yEnd = (getHeight() - 2 * GRAPH_MARGIN_SIZE) * (rank_current) / MAX_RANK + GRAPH_MARGIN_SIZE;
            String str;
            if (rank_previous == 1000){
                str = name + " *";
            } else {
                str = name + " " + Integer.toString(rank_previous);
            }
            drawLine(xStart,yStart,xEnd,yEnd,str,color);
            if (i == NDECADES - 1){
                if (rank_current == 1000){
                    str = name + " *";
                } else {
                    str = name + " " + Integer.toString(rank_current);
                }
                GLabel label = new GLabel(str);
                label.setLocation(xEnd,yEnd);
                label.setColor(color);
                add(label);
            }
        }
    }


    private void drawLine(double xStart, double yStart, double xEnd, double yEnd, String str, Color color){
        GLine line = new GLine(xStart,yStart,xEnd,yEnd);
        line.setColor(color);
        add(line);
        GLabel label = new GLabel(str);
        label.setLocation(xStart,yStart);
        label.setColor(color);
        add(label);
    }
	
	/* Implementation of the ComponentListener interface */
	public void componentHidden(ComponentEvent e) { }
	public void componentMoved(ComponentEvent e) { }
	public void componentResized(ComponentEvent e) { update(); }
	public void componentShown(ComponentEvent e) { }


    /* ivar */
    private ArrayList<NameSurferEntry> list = new ArrayList<NameSurferEntry>();
}
