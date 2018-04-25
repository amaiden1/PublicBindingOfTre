/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingofisaac;

import static bindingofisaac.Constants.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

/**
 * Represents a door: that is, a portal to another room.
 * Doors have a destination that determines how the player
 * will progress to another room.
 *
 * @author Austin Maiden and Nolan Ierardi
 */
public class Door {

	private ImageView img;
	
	private int
		direction;
        private double
		doorX,
		doorY,
		nextRoomX,
		nextRoomY,
		imgWidth,
		imgHeight;
	private Room destination;
	private boolean isOpen;
	private Rectangle hitbox;
	private int numDoors;

	/**
	 * Constructor for Door.
	 * @param dir the direction of this door
	 */
	public Door(int dir) {
		direction = dir;
		img = new ImageView(OPEN_DOOR);
		imgWidth = img.getFitWidth();
		imgHeight = img.getFitHeight();
                isOpen = true;
		switch (dir) {
			case 0:
				img.setRotate(0);  // rotate to face UP
				doorX = (ROOM_WIDTH / 2) - 10;
				doorY = 0;
				hitbox = new Rectangle(img.getFitWidth(),img.getFitHeight());
				nextRoomX = doorX;
				nextRoomY = ROOM_HEIGHT - 150;
				break;
			case 1:
				img.setRotate(90);  // rotate to face RIGHT
				doorX = ROOM_WIDTH - 65;
				doorY = (ROOM_HEIGHT / 2 ) - 40;
				hitbox = new Rectangle(img.getFitHeight(),img.getFitWidth());
				nextRoomX = 150;
				nextRoomY = doorY;
				break;
			case 2:
				img.setRotate(180);  // rotate to face DOWN
				doorX = (ROOM_WIDTH / 2) - 10;
				doorY = (ROOM_HEIGHT - ROOM_INNER_OFFSET);
				hitbox = new Rectangle(img.getFitWidth(),img.getFitHeight());
				nextRoomX = doorX;
				nextRoomY = 150;
				break;
			case 3:
				img.setRotate(270);  // rotate to face LEFT
				doorX = ROOM_INNER_OFFSET - 65;
				doorY = (ROOM_HEIGHT / 2) - 40;
				hitbox = new Rectangle(img.getFitHeight(),img.getFitWidth());
				nextRoomX = ROOM_WIDTH - 150;
				nextRoomY = doorY;
				break;
			default:
				// Direction isn't 0, 1, 2, 3
				System.out.println("[ERROR][Door.constructor] Invalid direction specified! Must be 0-3");
		}
		img.relocate(doorX, doorY);
		
	}

	/**
	 * Checks this door for collisions with the player.
	 */
	public void checkCollision(){
		if((Main.player.getImageView().getBoundsInParent().intersects(img.getBoundsInParent())) && Main.player != null && isOpen){
			System.out.println(destination);
			Main.player.getCurrentRoom().getRoomPane().getChildren().remove(Main.player.getImageView());
			int previousRoomX = Main.player.getCurrentRoom().getX();
			int previousRoomY = Main.player.getCurrentRoom().getY();
			Main.player.setCurrentRoom(destination);
			Main.player.getGame().updatePane(nextRoomX, nextRoomY, previousRoomX, previousRoomY);
		}
	}

	/**
	 * Sets the open status of this door.
	 * @param open if true, door is open; if false, door is locked
	 */
	public void setOpen(boolean open) {
            img.setImage((open)?OPEN_DOOR : LOCKED_DOOR);
            isOpen = open;
	}

	/**
	 * Returns the open status of this door.
	 * @return if this door is open
	 */
	public boolean isOpen() {
		return isOpen;
	}

	/**
	 * Returns the Room this door leads to.
	 * @return the Room this door leads to
	 */
	public Room getDestination() {
		return destination;
	}

	/**
	 * Sets the destination of this door.
	 * @param dest the Room this door should lead to
	 */
	public void setDestination(Room dest) {
		destination = dest;
	}

	/**
	 * Returns the ImageView of this door.
	 * @return ImageView of door
	 */
	public ImageView getImageView() {
		return img;
	}

	/**
	 * Returns a Rectangle representing the collidable
	 * area of this door.
	 * @return hitbox of this door
	 */
	public Rectangle getHitbox(){
		return hitbox;
	}

}
