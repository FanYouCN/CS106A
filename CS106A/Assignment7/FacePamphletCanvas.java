/*
 * File: FacePamphletCanvas.java
 * -----------------------------
 * This class represents the canvas on which the profiles in the social
 * network are displayed.  NOTE: This class does NOT need to update the
 * display when the window is resized.
 */


import acm.graphics.*;

import java.awt.*;
import java.util.Iterator;

public class FacePamphletCanvas extends GCanvas
					implements FacePamphletConstants {
	
	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the display
	 */
	public FacePamphletCanvas(){}

	
	/** 
	 * This method displays a message string near the bottom of the 
	 * canvas.  Every time this method is called, the previously 
	 * displayed message (if any) is replaced by the new message text 
	 * passed in.
	 */
	public void showMessage(String msg) {
        message.setLabel(msg);
        message.setFont(MESSAGE_FONT);
        message.setLocation((getWidth() - message.getWidth()) / 2,getHeight() - BOTTOM_MESSAGE_MARGIN);
        add(message);
	}
	
	
	/** 
	 * This method displays the given profile on the canvas.  The 
	 * canvas is first cleared of all existing items (including 
	 * messages displayed near the bottom of the screen) and then the 
	 * given profile is displayed.  The profile display includes the 
	 * name of the user from the profile, the corresponding image 
	 * (or an indication that an image does not exist), the status of
	 * the user, and a list of the user's friends in the social network.
	 */
	public void displayProfile(FacePamphletProfile profile) {
        removeAll();
        this.profile = profile;
        displayName();
        displayImage();
        displayStatus();
        displayFriends();
	}

    private void displayName(){
        name = new GLabel(profile.getName());
        name.setFont(PROFILE_NAME_FONT);
        name.setColor(Color.blue);
        name.setLocation(LEFT_MARGIN,TOP_MARGIN + name.getAscent());
        add(name);
    }

    private void displayImage(){
        if (profile.getImage() != null){
            GImage image = profile.getImage();
            image.setLocation(LEFT_MARGIN,TOP_MARGIN + name.getAscent() + IMAGE_MARGIN);
            image.setSize(IMAGE_WIDTH,IMAGE_HEIGHT);
            add(image);
        } else {
            displayNoImage();
        }
    }

    private void displayStatus(){
        GLabel status = new GLabel(profile.getName()+ " is " + profile.getStatus());
        status.setFont(PROFILE_STATUS_FONT);
        status.setLocation(LEFT_MARGIN,TOP_MARGIN + name.getAscent() + IMAGE_MARGIN + IMAGE_HEIGHT + STATUS_MARGIN + status.getAscent());
        if (profile.getStatus() != null){
            add(status);
        }
    }

    private void displayFriends(){
        GLabel friendLabel = new GLabel("Friends:");
        labelHeight = friendLabel.getHeight() + 5;
        friendLabel.setFont(PROFILE_FRIEND_LABEL_FONT);
        friendLabel.setLocation(getWidth() / 2, TOP_MARGIN + name.getAscent() + IMAGE_MARGIN);
        Iterator<String> friend = profile.getFriends();
        int friendIndex = 0;
        while (friend.hasNext()){
            addFriendDisplay(friend.next(),++friendIndex);
        }
        add(friendLabel);
   }
    private void addFriendDisplay(String str,int i){
        GLabel label = new GLabel(str);
        label.setFont(PROFILE_FRIEND_FONT);
        label.setLocation(getWidth() / 2,TOP_MARGIN + name.getHeight() + IMAGE_MARGIN + i * labelHeight);
        add(label);
    }

    private void displayNoImage(){
        GLine leftBorder = new GLine(LEFT_MARGIN,TOP_MARGIN + name.getAscent() + IMAGE_MARGIN,LEFT_MARGIN,TOP_MARGIN + name.getAscent() + IMAGE_MARGIN + IMAGE_HEIGHT);
        GLine topBorder = new GLine(LEFT_MARGIN,TOP_MARGIN + name.getAscent() + IMAGE_MARGIN,LEFT_MARGIN + IMAGE_WIDTH,TOP_MARGIN + name.getAscent() + IMAGE_MARGIN);
        GLine rightBorder = new GLine(LEFT_MARGIN + IMAGE_WIDTH,TOP_MARGIN + name.getAscent() + IMAGE_MARGIN,LEFT_MARGIN + IMAGE_WIDTH,TOP_MARGIN + name.getAscent() + IMAGE_MARGIN + IMAGE_HEIGHT);
        GLine bottomBorder = new GLine(LEFT_MARGIN,TOP_MARGIN + name.getAscent() + IMAGE_MARGIN + IMAGE_HEIGHT,LEFT_MARGIN + IMAGE_WIDTH,TOP_MARGIN + name.getAscent() + IMAGE_MARGIN + IMAGE_HEIGHT);
        add(leftBorder);
        add(topBorder);
        add(rightBorder);
        add(bottomBorder);
        noImage.setFont(PROFILE_IMAGE_FONT);
        noImage.setLocation(LEFT_MARGIN + (IMAGE_WIDTH - noImage.getWidth()) / 2, TOP_MARGIN + name.getAscent() + IMAGE_MARGIN + (IMAGE_HEIGHT) / 2);
        add(noImage);
    }

    private GLabel message = new GLabel("");
    private FacePamphletProfile profile;
    private GLabel name;
    private GLabel noImage = new GLabel("No Image");
    private double labelHeight;


	
}
