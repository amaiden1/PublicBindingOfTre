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
import static bindingofisaac.Constants.*;
import javafx.scene.image.Image;

public class Room {

	private boolean
			isOccupied,
			isCleared;
	private boolean[] hasDoors;
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
			roomPane,
			innerPane;
	private int
			numDoors,
			ix, // artificial index x
			iy; // artificial index y
	
/*******************************************************************************************************************************************
	public Room() { // Nolan's constructor
		// include door HashMap in constructor?
		isOccupied = false;
		isCleared = false;
		//this.innerBack = innerBack;
		//this.outerBack = outerBack; // each floor has diff background??
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
		basePane.getChildren().add(outerPane);  // Two separate calls here to ensure explicit
		basePane.getChildren().add(innerPane);  // stack order
	}
	/***************************************************************************************************************************************/
	
	public Room(int x, int y){ //Austin's constructor
		ix = x; iy = y;
		hasDoors = new boolean[]{false,false,false,false}; //N, E, S, W
		numDoors = 0;
		roomPane = new Pane();
		roomPane.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);
		
		Image walls = new Image("/img/Walls.png");
		outerBack = new ImageView();
		outerBack.setImage(walls);
		innerBack = new ImageView(new Image("/img/Playable_Area.png"));
		
		roomPane.getChildren().add(outerBack);
		roomPane.getChildren().add(innerBack);
		innerBack.relocate(80,82);
		
		isOccupied = false;
		isCleared = false;
		door0 = null;
		door1 = null;
		door2 = null;
		door3 = null;
		
	}

	public void setDoors(boolean[] doors) throws Exception{
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
			roomPane.getChildren().add(door0.getImageView());
			numDoors++;
		}
		if (doors[1]) {
			door1 = new Door(1);
			roomPane.getChildren().add(door1.getImageView());
			numDoors++;
		}
		if (doors[2]) {
			door2 = new Door(2);
			roomPane.getChildren().add(door2.getImageView());
			numDoors++;
		}
		if (doors[3]) {
			door3 = new Door(3);
			roomPane.getChildren().add(door3.getImageView());
			numDoors++;
		}

	}
	
	public Pane getRoomPane(){
		return roomPane;
	}
	
	public int getX(){
		return ix;
	}
	
	public int getY(){
		return iy;
	}
	
	public int getNumDoors(){
		return numDoors;
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
        
    public void setX(int x){
        ix = x;
    }
        
    public void setY(int y){
        iy = y;
    }

}
