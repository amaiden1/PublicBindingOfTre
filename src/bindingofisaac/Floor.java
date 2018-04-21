/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javafx.scene.image.ImageView;
import sun.jvm.hotspot.ui.EditorCommands;

import static bindingofisaac.Constants.*;

/**
 *
 * @author Austin
 */
public class Floor {
	private ArrayList<Room> thisFloor;
	private Room spawnRoom;
	private Room stairRoom;
	private Room itemRoom;
	private final ImageView stairs = new ImageView("/img/Stairs.png");
	private ArrayList<Item> possibleItems;
	
	public Floor(ArrayList<Room> givenRooms){
		thisFloor = givenRooms;
		spawnRoom = thisFloor.get(0);
		setStairRoom();
		setItemRoom();
		stairs.setFitHeight(70);
		stairs.setFitWidth(70);
		stairRoom.getRoomPane().getChildren().add(stairs);
		stairs.relocate((ROOM_WIDTH + stairs.getFitWidth()) / 2, (ROOM_HEIGHT + stairs.getFitHeight()) / 2);
		stairs.setVisible(true);

		possibleItems = new ArrayList<>();
		try {
			Scanner itemFileScanner = new Scanner(new File("Items.txt"));
			while (itemFileScanner.hasNextLine()) {
				Scanner itemLineScanner = new Scanner(itemFileScanner.nextLine());
				itemLineScanner.useDelimiter(",");
				while (itemLineScanner.hasNext()) {
					String name = itemLineScanner.next();
					ImageView img = new ImageView(itemLineScanner.next());
					int health = itemLineScanner.nextInt();
					int speed = itemLineScanner.nextInt();
					int damage = itemLineScanner.nextInt();
					int attackSpeed = itemLineScanner.nextInt();
					possibleItems.add(new Item(img, health, speed, damage, attackSpeed, name));
				}
			}
		} catch (Exception e) {
			System.out.println("Error parsing item file: " + e.getMessage());
		}
	}`
	
	public void setStairRoom(){
		Random rand = new Random();
		Room chosenRoom = null;
		do{
			chosenRoom = thisFloor.get(rand.nextInt(thisFloor.size()));
			System.out.println("Stair " + chosenRoom.toString());
		}while(chosenRoom == spawnRoom);
		stairRoom = chosenRoom;
	}
	
	public void setItemRoom(){
		Random rand = new Random(thisFloor.size());
		Room chosenRoom = null;
		do{
			chosenRoom = thisFloor.get(rand.nextInt(thisFloor.size()));
			System.out.println("Item " + chosenRoom.toString());
		}while(chosenRoom == spawnRoom);
		itemRoom = chosenRoom;
	}
	
	public void checkStairsCollision()	{
		if(Main.player.getCurrentRoom().getRoomPane() == stairRoom.getRoomPane()&&
			(Main.player.getImageView().getBoundsInParent().intersects(stairs.getBoundsInParent()) && stairs.isVisible())){ // if the player is in stair room and is touching the (visible) stairs
			Main.player.getGame().setIsFloorFinished(true);
			System.out.println("floorLevel: " + Main.player.getGame().getFloorLevel());
		}
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
        
	public void addEnemies(){
		for(Room room : thisFloor){
			room.addEnemies();
		}
	}
}
