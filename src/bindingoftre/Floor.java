/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */
package bindingoftre;


import static bindingoftre.Constants.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.image.ImageView;

/**
 * Represents a floor of the dungeon, containing many rooms.
 *
 * @author Austin Maiden and Nolan Ierardi
 */
public class Floor {
    private ArrayList<Room> thisFloor;
    private Room spawnRoom;
    private Room stairRoom;
    private Room itemRoom;
    private ArrayList<Item> possibleItems;
    private ArrayList<Enemy> possibleEnemies;
    private ImageView stairs = new ImageView(LOCKED_STAIRS);

	/**
	 * Constructor for Floor.
	 * @param givenRooms ArrayList of Rooms to add
	 */
	public Floor(ArrayList<Room> givenRooms){
        thisFloor = givenRooms;
        spawnRoom = thisFloor.get(0);
        possibleItems = new ArrayList<>();
        possibleEnemies = new ArrayList<Enemy>();
        createItems();
        setStairRoom();
        setItemRoom();
        stairs.setFitHeight(70);
        stairs.setFitWidth(70);
        stairRoom.getRoomPane().getChildren().add(stairs);
        stairRoom.setHasStairs(true);
        stairs.relocate((ROOM_WIDTH + stairs.getFitWidth()) / 2, (ROOM_HEIGHT + stairs.getFitHeight()) / 2);
        stairs.setVisible(true);
        
    }

    private void setStairRoom(){
        Random rand = new Random();
        Room chosenRoom = null;
        do{
            chosenRoom = thisFloor.get(rand.nextInt(thisFloor.size()));
            if (Main.DEBUG) System.out.println("Stair " + chosenRoom.toString());
        }while(chosenRoom == spawnRoom);
        stairRoom = chosenRoom;
    }

    private void setItemRoom(){
        Random rand = new Random();
        Room chosenRoom = null;
        do{
            chosenRoom = thisFloor.get(rand.nextInt(thisFloor.size()));
	        if (Main.DEBUG) System.out.println("Item " + chosenRoom.toString());
        }while(chosenRoom == spawnRoom || chosenRoom == stairRoom);
        itemRoom = chosenRoom;
	    if (Main.DEBUG) System.out.println("list size: " + possibleItems.size());
        itemRoom.setItem(possibleItems.get(rand.nextInt(possibleItems.size()))); //there was an exception here
    }

	/**
	 * Checks the stairs for collision with the player.
	 */
	public void checkStairsCollision()	{
        if(Main.player.getCurrentRoom().getRoomPane() == stairRoom.getRoomPane()&&
            (Main.player.getImageView().getBoundsInParent().intersects(stairs.getBoundsInParent()) && stairs.getImage() == STAIRS)){ // if the player is in stair room and is touching the (visible) stairs
            Main.player.getGame().setIsFloorFinished(true);
	        if (Main.DEBUG) System.out.println("floorLevel: " + Main.player.getGame().getFloorLevel());
        }
    }

	/**
	 * Returns the current floor level.
	 * @return the current floor level the player is on
	 */
	public ArrayList<Room> getFloor(){
        return thisFloor;
    }

	/**
	 * Returns a String representing this room.
	 * @return String representing this room
	 */
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

	/**
	 * Returns the Room the player spawns in.
	 * @return the Room the player spawns in
	 */
	public Room getSpawnRoom(){
            return spawnRoom;
    }

	/**
	 * Returns the room at a given location.
	 * @param x the x position of the room
	 * @param y the y position of the room
	 * @return the room at that location
	 */
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

	/**
	 * Adds predefined enemies to the room.
	 */
	public void addEnemies(){
        int[] xSpawns = new int[]{400, 800, 400, 800};
        int[] ySpawns = new int[]{200, 500, 500, 200};
        Random rand = new Random();
        for(Room room : thisFloor){
            if(room != spawnRoom && room != itemRoom){
                int numberOfEnemies = rand.nextInt(5);
                ArrayList<Enemy> enemies = new ArrayList<Enemy>();
                for(int i = 0; i < numberOfEnemies; i++){
                    int number = rand.nextInt(2);
                    Enemy enemy = null;
                    switch(number){
                        case 0:
                            enemy = new Witch(Main.player.getGame().getFloorLevel(), room);
                            break;
                        case 1:
                            enemy = new Mummy(Main.player.getGame().getFloorLevel(), room);
                            break;
                        default:
                            break;
                    }
                    enemies.add(enemy);
                    enemy.setX(xSpawns[i]);
                    enemy.setY(ySpawns[i]);
                    room.getRoomPane().getChildren().add(enemy.getSprite());
                    enemy.getSprite().relocate(enemy.getX(), enemy.getY());
                }
                room.setEnemies(enemies);
                if(enemies.size() == 0){
                    room.setCleared(true);
                }
            }
        }
    }

	private void createItems(){
        Scanner fileScan;
        Scanner lineScan;
        ImageView itemImg;
        String startImgSrc = "/img/";
        String endImgSrc = ".png";
        try{
        File file = new File("./src/files/items.txt");
        fileScan = new Scanner(file);
        
        while(fileScan.hasNextLine()){
            lineScan = new Scanner(fileScan.nextLine());
            lineScan.useDelimiter(",");
            String name = lineScan.next();
            String midImgSrc = lineScan.next();
            String imgSrc = startImgSrc + midImgSrc + endImgSrc;
            itemImg = new ImageView(imgSrc);
            String desc = lineScan.next();
            int healthDelta = lineScan.nextInt();
            double speedDelta = lineScan.nextDouble();
            int damageDelta = lineScan.nextInt();
            double attackSpeedDelta = lineScan.nextDouble();
            possibleItems.add(new Item(name, itemImg, desc, healthDelta, speedDelta, damageDelta, attackSpeedDelta));
        }
        }
        catch(Exception e){
	        if (Main.DEBUG) System.out.println(e.getMessage());
        }
        
    }

	/**
	 * Unlocks the stairs so that the player can go to the next floor.
	 */
	public void unlock(){
        stairs.setImage(STAIRS);
    }
}
