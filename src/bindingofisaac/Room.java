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

public class Room {

	private boolean
			isOccupied,
			isCleared;
	private ImageView background;
	/*
	private Door
			door0,
			door1,
			door2,
			door3;
	private Item item;
	private ArrayList<Enemy> enemies;
	*/
	private StackPane basePane;
	private Pane
			outerPane,
			innerPane;

	public Room(ImageView background) {
		isOccupied = false;
		isCleared = false;
		this.background = background;
		/*
		door0 = null;
		door1 = null;
		door2 = null;
		door3 = null;
		item = null;
		enemies = new ArrayList<>();
		*/
		basePane = new StackPane();
		basePane.setPrefSize(Constants.ROOM_WIDTH, Constants.ROOM_HEIGHT);
		outerPane = new Pane();
		outerPane.setPrefSize(Constants.ROOM_WIDTH, Constants.ROOM_HEIGHT);
		innerPane = new Pane();
		innerPane.setPrefSize(Constants.ROOM_WIDTH - Constants.ROOM_INNER_OFFSET, Constants.ROOM_HEIGHT - Constants.ROOM_INNER_OFFSET);
		basePane.getChildren().add(outerPane);  // Two seperate calls here to ensure explicit
		basePane.getChildren().add(innerPane);  // stack order
	}

	public void setDoors(boolean d0, boolean d1, boolean d2, boolean d3) {
		// code to create doors and add them
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
