/* 
 * File: FacePamphlet.java
 * -----------------------
 * When it is finished, this program will implement a basic social network
 * management system.
 */

import acm.program.*;
import acm.graphics.*;
import acm.util.*;
import java.awt.event.*;
import javax.swing.*;

public class FacePamphlet extends Program
					implements FacePamphletConstants {

    public static void main(String[] args) {
        new FacePamphlet().start(args);
    }

	/**
	 * This method has the responsibility for initializing the 
	 * interactors in the application, and taking care of any other 
	 * initialization that needs to be performed.
	 */
	public void init() {
        JLabel name = new JLabel("Name");
        tfName.setColumns(TEXT_FIELD_SIZE);
        tfChangeStatus.setColumns(TEXT_FIELD_SIZE);
        tfChangeStatus.addActionListener(this);
        tfChangePicture.setColumns(TEXT_FIELD_SIZE);
        tfChangePicture.addActionListener(this);
        tfAddFriend.setColumns(TEXT_FIELD_SIZE);
        tfAddFriend.addActionListener(this);
        currentProfile = null;
        canvas = new FacePamphletCanvas();
        add(canvas);
        add(name,NORTH);
        add(tfName,NORTH);
        add(add,NORTH);
        add(delete,NORTH);
        add(lookUp,NORTH);
        add(tfChangeStatus,WEST);
        add(changeStatus,WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(tfChangePicture,WEST);
        add(changePicture,WEST);
        add(new JLabel(EMPTY_LABEL_TEXT), WEST);
        add(tfAddFriend,WEST);
        add(addFriend,WEST);
        addActionListeners();
    }
    
  
    /**
     * This class is responsible for detecting when the buttons are
     * clicked or interactors are used, so you will have to add code
     * to respond to these actions.
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == add && ! tfName.getText().equals("")){
            String name = tfName.getText();
            if (profileDB.containsProfile(name)){
                canvas.showMessage("A profile with the name " + name + " already exists.");
            } else {
                FacePamphletProfile profile = new FacePamphletProfile(tfName.getText());
                profileDB.addProfile(profile);
                canvas.displayProfile(profileDB.getProfile(name));
                canvas.showMessage("New profile with the name " + name + " created.");
                currentProfile = profile;
            }
        }
        if (e.getSource() == delete && ! tfName.getText().equals("")) {
            String name = tfName.getText();
            if (! profileDB.containsProfile(name)){
                canvas.showMessage("A profile with the name " + name + " does not exist.");
            } else {
                profileDB.deleteProfile(name);
                canvas.showMessage("Profile of " + name + " deleted.");
                currentProfile = null;
            }
        }

        if (e.getSource() == lookUp && !tfName.getText().equals("")) {
            String name = tfName.getText();
            if (profileDB.containsProfile(name)){
                canvas.displayProfile(profileDB.getProfile(name));
                canvas.showMessage("Displaying " + name);
                currentProfile = profileDB.getProfile(name);
            } else {
                canvas.showMessage("A profile with the mane " + name + " does not exist");
                currentProfile = null;
            }
        }

        if ((e.getSource() == changeStatus || e.getSource() == tfChangeStatus) && ! tfChangeStatus.getText().equals("")){
            if (currentProfile != null){
                String status = tfChangeStatus.getText();
                profileDB.getProfile(currentProfile.getName()).setStatus(status);
                canvas.displayProfile(profileDB.getProfile(currentProfile.getName()));
                canvas.showMessage("Status updated.");
            } else {
                canvas.showMessage("Please select a profile to change status.");
            }
        }

        if ((e.getSource() == changePicture || e.getSource() == tfChangePicture) && ! tfChangePicture.getText().equals("")){
            if (currentProfile != null){
                String fileName = tfChangePicture.getText();
                GImage image;
                try {
                    image = new GImage(fileName);
                    profileDB.getProfile(currentProfile.getName()).setImage(image);
                    canvas.displayProfile(profileDB.getProfile(currentProfile.getName()));
                    canvas.showMessage("Picture updated.");
                } catch (ErrorException ex){
                    canvas.showMessage("Unable to open image file: " + fileName);
                }
            } else {
                canvas.showMessage("Please select a profile to change picture.");
            }
        }

        if ((e.getSource() == addFriend || e.getSource() == tfAddFriend) && ! tfAddFriend.getText().equals("")) {
            if (currentProfile != null){
                String friendName = tfAddFriend.getText();
                if (profileDB.containsProfile(friendName)){
                    if (! currentProfile.toString().contains(friendName)){
                        profileDB.getProfile(currentProfile.getName()).addFriend(friendName);
                        profileDB.getProfile(friendName).addFriend(currentProfile.getName());
                        canvas.displayProfile(profileDB.getProfile(currentProfile.getName()));
                        canvas.showMessage(friendName + " added as a friend.");
                    } else {
                        canvas.showMessage(currentProfile.getName() + " already has " + friendName + " as a friend.");
                    }
                } else {
                    canvas.showMessage(friendName + " does not exist.");
                }
            } else {
                canvas.showMessage("Please select a profile to add friend.");
            }
        }
	}

    /* instance variables */
    private JTextField tfName = new JTextField();
    private JButton add = new JButton("Add");
    private JButton delete = new JButton("Delete");
    private JButton lookUp = new JButton("Lookup");
    private JTextField tfChangeStatus = new JTextField();
    private JTextField tfChangePicture = new JTextField();
    private JTextField tfAddFriend = new JTextField();
    private JButton changeStatus = new JButton("Change Status");
    private JButton changePicture = new JButton("Change Picture");
    private JButton addFriend = new JButton("Add Friend");
    private FacePamphletDatabase profileDB = new FacePamphletDatabase();
    private FacePamphletProfile currentProfile;
    private FacePamphletCanvas canvas;

}
