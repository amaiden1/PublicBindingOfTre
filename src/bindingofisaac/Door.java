/*
 *  Temporary version of Door by Nolan Ierardi.
 */

package bindingofisaac;


import static bindingofisaac.Constants.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Door {

	private int
		direction;
        private double
		doorX,
		doorY,
		hitboxX,
		hitboxY;
	private Room destination;
	private boolean isOpen;
	private ImageView img;  // this image is first provided UP
	private Rectangle hitbox;
	private int numDoors;

	public Door(int dir) throws Exception {
		this.direction = dir;
		img = new ImageView("/img/Open_Door.png");
		switch (dir) {
			case 0:
				img.setRotate(0);  // rotate to face UP
				doorX = (ROOM_WIDTH / 2) - 10;
				doorY = 0;
				hitbox = new Rectangle(img.getFitWidth(),img.getFitHeight());
				hitboxX = doorX;
				hitboxY = doorY;
				break;
			case 1:
				img.setRotate(90);  // rotate to face RIGHT
				doorX = ROOM_WIDTH - 65;
				doorY = (ROOM_HEIGHT / 2 ) - 40;
				hitbox = new Rectangle(img.getFitHeight(),img.getFitWidth());
				hitboxX = doorX;
				hitboxY = doorY;
				break;
			case 2:
				img.setRotate(180);  // rotate to face DOWN
				doorX = (ROOM_WIDTH / 2) - 10;
				doorY = (ROOM_HEIGHT - ROOM_INNER_OFFSET);
				hitbox = new Rectangle(img.getFitWidth(),img.getFitHeight());
				hitboxX = doorX;
				hitboxY = doorY;
				break;
			case 3:
				img.setRotate(270);  // rotate to face LEFT
				doorX = ROOM_INNER_OFFSET - 65;
				doorY = (ROOM_HEIGHT / 2) - 40;
				hitbox = new Rectangle(img.getFitHeight(),img.getFitWidth());
				hitboxX = doorX;
				hitboxY = doorY;
				break;
			default:
				// Direction isn't 0, 1, 2, 3
				throw new DirectionNotFoundException("[ERROR][Door.constructor] Invalid direction specified! Must be 0-3");
		}
		img.relocate(doorX, doorY);
                
                img.boundsInParentProperty().addListener(new ChangeListener<Bounds>(){

                    @Override
                    public void changed(ObservableValue<? extends Bounds> observable, Bounds oldValue, Bounds newValue) {
                        if(img.intersects(player.getImageView().getBoundsInLocal()))
                    }
                });
                
	}

	public void setOpen(boolean open) {
		isOpen = open;
	}

	public boolean isOpen() {
		return isOpen;
	}
        
	public Room getDestination() {
		return destination;
	}

	public void setDestination(Room dest) {
		destination = dest;
	}
        
	public ImageView getImageView() {
		return img;
	}
	
	public Rectangle getHitbox(){
		return hitbox;
	}

}
