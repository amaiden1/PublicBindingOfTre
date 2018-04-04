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

	public Door(int dir) {

		this.direction = dir;
		img.setFitHeight(ROOM_INNER_OFFSET);

		if (dir == UP) {
			doorX = ROOM_WIDTH / 2;
			doorY = ROOM_INNER_OFFSET / 2;
			hitbox = new Rectangle(img.getFitWidth(),15);
			hitboxX = ROOM_WIDTH / 2;
			hitboxY = (int)(ROOM_INNER_OFFSET + (hitbox.getHeight() / 2));
			img = DOOR_UP;

		} else if (dir == RIGHT) {
			img.setRotate(90);  // rotate to face RIGHT
			doorX = ROOM_WIDTH - (ROOM_INNER_OFFSET / 2);
			doorY = Constants.ROOM_HEIGHT / 2;
			hitbox = new Rectangle(15, img.getFitHeight());
			hitboxX = (int)(ROOM_WIDTH - ROOM_INNER_OFFSET - (hitbox.getWidth() / 2));
			hitboxY = ROOM_HEIGHT / 2;
			img = DOOR_RIGHT;

		} else if (dir == DOWN) {
			img.setRotate(180);  // rotate to face DOWN
			doorX = ROOM_WIDTH / 2;
			doorY = (int)(ROOM_HEIGHT - (img.getFitHeight() / 2));
			hitbox = new Rectangle(15, img.getFitHeight());
			hitboxX = ROOM_WIDTH / 2;
			hitboxY = (int)(ROOM_HEIGHT - ROOM_INNER_OFFSET - (hitbox.getHeight() / 2));
			img = DOOR_DOWN;

		} else if (dir == LEFT) {
			img.setRotate(270);  // rotate to face LEFT
			doorX = ROOM_INNER_OFFSET / 2;
			doorY = ROOM_HEIGHT / 2;
			hitboxX = (int)(ROOM_INNER_OFFSET + (hitbox.getWidth() / 2));
			hitboxY = ROOM_HEIGHT / 2;
			img = DOOR_LEFT;

		} else {
			// Direction isn't 0, 1, 2, 3
			System.out.println("[ERROR][Door.constructor] Invalid direction specified! Must be 0-3");
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
