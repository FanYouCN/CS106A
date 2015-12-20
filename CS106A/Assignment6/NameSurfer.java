/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends Program implements NameSurferConstants {

/* Method: init() */
/**
 * This method has the responsibility for reading in the data base
 * and initializing the interactors at the bottom of the window.
 */
	public void init() {
	    // You fill this in, along with any helper methods //
        label = new JLabel("Name");
        tf = new JTextField("",20);
        jbGraph = new JButton("Graph");
        jbClear = new JButton("Clear");
        graph = new NameSurferGraph();
        dataBase = new NameSurferDataBase(NAMES_DATA_FILE);
        add(label,SOUTH);
        add(tf,SOUTH);
        add(jbGraph,SOUTH);
        add(jbClear,SOUTH);
        add(graph);
        addActionListeners();
	}

/* Method: actionPerformed(e) */
/**
 * This class is responsible for detecting when the buttons are
 * clicked, so you will have to define a method to respond to
 * button actions.
 */
	public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jbGraph){
            NameSurferEntry entry = dataBase.findEntry(tf.getText());
            graph.addEntry(entry);
            graph.update();
        }
        if (e.getSource() == jbClear){
            graph.clear();
            graph.update();
        }
	}

/* ivar */
	private JLabel label;
	private JTextField tf;
	private JButton jbGraph;
	private JButton jbClear;
    private NameSurferGraph graph;
    private NameSurferDataBase dataBase;
}
