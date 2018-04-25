/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
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

/**
 * Represents a room that the player travels through.
 */
public class Room {

	private boolean isOccupied,
			hasStairs;
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

	/**
	 * Constructor for Room.
	 * @param x the x position of this room in the map
	 * @param y the y position of this room in the map
	 */
	public Room(int x, int y){
		ix = x; iy = y;
                hasStairs = false;
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

	/**
	 * Sets the doors in the room.
	 * @param doors boolean array of whether or not the door exists at that direction
	 * @param floor the floor that this room is on
	 * @param roomX the x position of the room
	 * @param roomY the y position of the room
	 */
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

	/**
	 * Returns an ArrayList of doors in the room.
	 * @return ArrayList of doors in the room
	 */
	public ArrayList<Door> getDoors(){
		return doorsList;
	}

	/**
	 * Returns the height of the room.
	 * @return height of room
	 */
    public double getHeight(){
        return roomPane.getHeight();
    }

	/**
	 * Returns the width of the room.
	 * @return width of room
	 */
	public double getWidth(){
        return roomPane.getWidth();
    }

	/**
	 * Returns the width of the room.
	 * @return width of room
	 */
	public Pane getRoomPane(){
		return roomPane;
	}

	/**
	 * Returns the x index of the room.
	 * @return x position of room
	 */
	public int getX(){
		return ix;
	}

	/**
	 * Returns the y index of the room.
	 * @return y position of room
	 */
	public int getY(){
		return iy;
	}

	/**
	 * Returns the number of doors in this room.
	 * @return number of doors in room
	 */
	public int getNumDoors(){
		return numDoors;
	}

	/**
	 * Sets the given ArrayList of Enemies to be the enemies.
	 * @param enemies Enemies to use
	 */
	public void setEnemies(ArrayList<Enemy> enemies) {
		this.enemies = enemies;
	}

	/**
	 * Returns an ArrayList of Enemies in this room.
	 * @return ArrayList of Enemies in room
	 */
	public ArrayList<Enemy> getEnemies() {
		// code to get enemies
		return enemies;
	}

	/**
	 * Sets whether or not this room is occupied.
	 * @param occupied whether or not this room is occupied
	 */
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

	/**
	 * Sets whether or not this room is cleared.
	 * @param cleared whether or not this room is cleared
	 */
	public void setCleared(boolean cleared) {
            if(hasStairs && cleared){
                Main.player.getGame().getFloor().unlock();
            }
            isCleared.set(cleared);
	}

	/**
	 * Sets the item contained in this room.
	 * @param givenItem the Item in this room
	 */
	public void setItem(Item givenItem) {
		item = givenItem;
                roomPane.getChildren().add(item.getImageView());
	}

	/**
	 * Sets the x position of this room.
	 * @param x x position of this room
	 */
	public void setX(int x){
        ix = x;
    }

	/**
	 * Sets the y position of this room.
	 * @param y y position of this room
	 */
	public void setY(int y){
        iy = y;
    }

	/**
	 * Returns a string containing the room's coordinates.
	 * @return room coordinates in displayable format
	 */
	public String toString(){
		return "Room coordinates: " + ix + " " + iy;
	}

	/**
	 * Sets if this room has stairs.
	 * @param newState whether or not this room has stairs
	 */
	public void setHasStairs(boolean newState){
        hasStairs = newState;
    }
}
