/*
 *  Temporary version of Door by Nolan Ierardi.
 */

package bindingofisaac;

import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import static bindingofisaac.Constants.*;

public class Door {

	private int
		direction,
		doorX,
		doorY,
		hitboxX,
		hitboxY;
	//private Room destination;
	private boolean isOpen;
	private ImageView img;  // this image is first provided UP
	private Rectangle hitbox;
	private int numDoors;

	public Door(int dir) throws Exception {
		this.direction = dir;
		switch (dir) {
			case 0:
				//img.setRotate(0);  // rotate to face
				doorX = ROOM_WIDTH / 2;
				doorY = ROOM_INNER_OFFSET / 2;
				hitbox = new Rectangle(60,60); //problem line
				hitboxX = ROOM_WIDTH / 2;
				hitboxY = (int)(ROOM_INNER_OFFSET + (hitbox.getHeight() / 2));
				break;
			case 1:
				//img.setRotate(90);  // rotate to face
				doorX = ROOM_WIDTH - (ROOM_INNER_OFFSET / 2);
				doorY = ROOM_HEIGHT / 2;
				hitbox = new Rectangle(60,60);
				hitboxX = (int)(ROOM_WIDTH - ROOM_INNER_OFFSET - (hitbox.getWidth() / 2));
				hitboxY = ROOM_HEIGHT / 2;
				break;
			case 2:
				//img.setRotate(180);  // rotate to face DOWN
				doorX = ROOM_WIDTH / 2;
				doorY = (int)(ROOM_HEIGHT - (ROOM_INNER_OFFSET / 2));
				hitbox = new Rectangle(60,60);
				hitboxX = ROOM_WIDTH / 2;
				hitboxY = (int)(ROOM_HEIGHT - ROOM_INNER_OFFSET - (hitbox.getHeight() / 2));
				break;
			case 3:
				//img.setRotate(270);  // rotate to face LEFT
				doorX = ROOM_INNER_OFFSET / 2;
				doorY = ROOM_HEIGHT / 2;
				hitbox = new Rectangle(60,60);
				hitboxX = (int)(ROOM_INNER_OFFSET + (hitbox.getWidth() / 2));
				hitboxY = ROOM_HEIGHT / 2;
				break;
			default:
				// Direction isn't 0, 1, 2, 3
				throw new DirectionNotFoundException("[ERROR][Door.constructor] Invalid direction specified! Must be 0-3");
		}
	}

	public void setOpen(boolean open) {
		isOpen = open;
	}

	public boolean isOpen() {
		return isOpen;
	}
	/*
	public Room getDestination() {
		return destination;
	}

	public void setDestination(Room dest) {
		destination = dest;
	}
	*/

	public ImageView getImageView() {
		return img;
	}

}
