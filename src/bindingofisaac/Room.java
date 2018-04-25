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

import static bindingofisaac.Constants.*;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Room {

	private boolean isOccupied;
        private BooleanProperty isCleared;
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
	
	public Room(int x, int y){
		ix = x; iy = y;
		hasDoors = new boolean[]{false,false,false,false}; //N, E, S, W
		numDoors = 0;
		doorsList = new ArrayList<>();
		roomPane = new Pane();
		roomPane.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);
		
		outerBack = new ImageView(ROOM_WALLS);
		innerBack = new ImageView(ROOM_PLAYABLE_AREA);
		
		roomPane.getChildren().add(outerBack);
		roomPane.getChildren().add(innerBack);
		innerBack.relocate(80,82);
                
		isOccupied = false;
		isCleared = new SimpleBooleanProperty(false);
                isCleared.addListener(new ChangeListener<Boolean>(){
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                            for(Door door : doorsList){
                                door.setOpen(newValue);
                            }
                    }
                });
                
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

	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	public ArrayList<Enemy> getEnemies() {
		// code to get enemies
		return enemies;
	}

	public ArrayList<ImageView> getEnemyImageViews() {
		// code to get enemy image views
		return new ArrayList<>();
	}

	public void removeEnemy(Enemy enemy) {
		try{
                    enemies.remove(enemy);
		}
		catch(Exception e){
                    System.out.println(e.getMessage());
		}
	}

	public boolean isOccupied() {
		return isOccupied;
	}

	public void setOccupied(boolean occupied) {
            if(occupied && enemies != null && enemies.size() > 0){
                for(Enemy enemy : enemies){
                    enemy.start();
                }
            }
            if(occupied && item != null){
		item.startTimer();
            }
            if(!occupied && item != null){
		item.stopTimer();
            }
            if(occupied && enemies != null && enemies.size() > 0){
                for(Door thisDoor : doorsList){
                    thisDoor.setOpen(false);
                }
            }
            isOccupied = occupied;
	}

	public boolean isCleared() {
            return isCleared.get();
	}

	public void setCleared(boolean cleared) {
            isCleared.set(cleared);
	}

	public void setItem(Item givenItem) {
		item = givenItem;
                roomPane.getChildren().add(item.getImageView());
	}

	public Item getItem() {
		// code to return item
		return null;
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
