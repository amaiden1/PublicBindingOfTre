/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import java.util.ArrayList;

/**
 *
 * @author Austin
 */
public class Floor {
	private ArrayList<Room> thisFloor;
	private Room spawnRoom;
	
	public Floor(ArrayList<Room> givenRooms){
		thisFloor = givenRooms;
		spawnRoom = thisFloor.get(0);
	}
	
	public String toString(){
		String result = "";
		int index = 0;
		for(Room current : thisFloor){
			result += "Room (" + current.getX() + " , " + current.getY() + "): Doors: " + current.getNumDoors() + "\n";
			index++;
		}
		return result;
	}
	
	public Room getSpawnRoom(){
		return spawnRoom;
	}
	
}
