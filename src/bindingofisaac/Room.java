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
	private ArrayList<Door> doorsList;
	private Item item;
	private ArrayList<Enemy> enemies;
	private Enemy enemy; // temperary for testing
	private Pane
			roomPane;
	private int
			numDoors,
			ix, // artificial index x
			iy; // artificial index y
	
	
	public Room(int x, int y){ //Austin's constructor
		ix = x; iy = y;
		hasDoors = new boolean[]{false,false,false,false}; //N, E, S, W
		numDoors = 0;
		doorsList = new ArrayList<>();
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

	public void setDoors(boolean[] doors, Floor floor, int roomX, int roomY) {
		// code to create doors and add them
		if (doors[0]) {
			door0 = new Door(0);
			roomPane.getChildren().add(door0.getImageView());
			numDoors++;
            door0.setDestination(floor.getRoomAtLocation(roomX, roomY + 1));
			doorsList.add(door0);
		}
		if (doors[1]) {
			door1 = new Door(1);
			roomPane.getChildren().add(door1.getImageView());
			numDoors++;
            door1.setDestination(floor.getRoomAtLocation(roomX + 1, roomY));
			doorsList.add(door1);
		}
		if (doors[2]) {
			door2 = new Door(2);
			roomPane.getChildren().add(door2.getImageView());
			numDoors++;
            door2.setDestination(floor.getRoomAtLocation(roomX, roomY - 1));
			doorsList.add(door2);
		}
		if (doors[3]) {
			door3 = new Door(3);
			roomPane.getChildren().add(door3.getImageView());
			numDoors++;
            door3.setDestination(floor.getRoomAtLocation(roomX - 1, roomY));
			doorsList.add(door3);
		}
		
	}
	public void addEnemies(){
		enemy = new Witch(400, 400, Main.player.getGame().getFloorLevel());
		roomPane.getChildren().add(enemy.getSprite());
	}
	
	public ArrayList<Door> getDoors(){
		return doorsList;
	}
	
    public double getHeight(){
        return roomPane.getHeight();
    }
        
    public double getWidth(){
        return roomPane.getWidth();
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
	
	public String toString(){
		return "Room co-ordinates: " + ix + " " + iy;
	}

}
