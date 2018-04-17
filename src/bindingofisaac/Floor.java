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
        
        public ArrayList<Room> getFloor(){
            return thisFloor;
        }
	
	public String toString(){
		String result = "";
		int index = 0;
		for(Room current : thisFloor){
			result += "Room (" + current.getX() + " , " + current.getY() + "): Doors: " + current.getNumDoors() + "\n";
			index++;
		}
                this.getRoomAtLocation(0, 0).setOccupied(true);
		return result;
	}
	
	public Room getSpawnRoom(){
		return spawnRoom;
	}
        
        private void doorCollisionChecker(){
            
        }
	
        public Room getRoomAtLocation(int x, int y){
            Room room = null;
            boolean found = false;
            int index = 0;
            do{
                if(thisFloor.get(index).getX() == x && thisFloor.get(index).getY() == y){
                    found = true;
                    room = thisFloor.get(index);
                }
                if(index > thisFloor.size()){
                    found = true;
                }
                index++;
            }while(!found);
            return room;
        }
        
}
