/*
 ***********
 *
 * This method contains many stub methods. This is due to
 * the classes needed to create those methods not existing
 * yet. These classes need to be created.
 *
 ***********
 */

package bindingofisaac;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import java.util.ArrayList;
import java.util.HashMap;

public class Room {

	private boolean
			isOccupied,
			isCleared;
	private ImageView
			innerBack,
			outerBack;
	private Door
			door0,
			door1,
			door2,
			door3;
	private Item item;
	private ArrayList<Enemy> enemies;
	private StackPane basePane;
	private Pane
			outerPane,
			innerPane;
	private int
			ix, // artificial index x
			iy; // artificial index y

	public Room(ImageView innerBack, ImageView outerBack) {
		// include door HashMap in constructor?
		isOccupied = false;
		isCleared = false;
		this.innerBack = innerBack;
		this.outerBack = outerBack;
		door0 = null;
		door1 = null;
		door2 = null;
		door3 = null;
		item = null;
		enemies = new ArrayList<>();
		basePane = new StackPane();
		basePane.setPrefSize(Constants.ROOM_WIDTH, Constants.ROOM_HEIGHT);
		outerPane = new Pane();
		outerPane.setPrefSize(Constants.ROOM_WIDTH, Constants.ROOM_HEIGHT);
		innerPane = new Pane();
		innerPane.setPrefSize(Constants.ROOM_WIDTH - Constants.ROOM_INNER_OFFSET, Constants.ROOM_HEIGHT - Constants.ROOM_INNER_OFFSET);
		basePane.getChildren().add(outerPane);  // Two seperate calls here to ensure explicit
		basePane.getChildren().add(innerPane);  // stack order
	}

	public void setDoors(boolean[] doors) {
		// code to create doors and add them
		/*
		if (doors.containsKey(0)) {
			door0 = new Door(0, doors.get(0));
		}
		if (doors.containsKey(1)) {
			door1 = new Door(1, doors.get(1));
		}
		if (doors.containsKey(0)) {
			door2 = new Door(2, doors.get(2));
		}
		if (doors.containsKey(0)) {
			door3 = new Door(3, doors.get(3));
		}
		*/
		if (doors[0]) {
			door0 = new Door(0);
		}
		if (doors[1]) {
			door1 = new Door(1);
		}
		if (doors[2]) {
			door2 = new Door(2);
		}
		if (doors[3]) {
			door3 = new Door(3);
		}

	}

	public void setEnemies(Enemy... enemies) {
		// code to add enemies
	}

	public ArrayList<Enemy> getEnemies() {
		// code to get enemies
		return new ArrayList<>();
	}

	public ArrayList<ImageView> getEnemyImageViews() {
		// code to get enemy image views
		return new ArrayList<>();
	}

	public void removeEnemy(Enemy enemy) {
		// code to remove an enemy
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean occupied) {
		isOccupied = occupied;
	}

	public boolean isCleared() {
		return isCleared;
	}

	public void setCleared(boolean cleared) {
		isCleared = cleared;
	}

	public void setItem(Item item) {
		// code to set item
	}

	public Item getItem() {
		// code to return item
		return new Item();
	}

}
