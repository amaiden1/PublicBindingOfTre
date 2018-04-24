/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;


import static bindingofisaac.Constants.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.scene.image.ImageView;

/**
 *
 * @author Austin
 */
public class Floor {
    private ArrayList<Room> thisFloor;
    private Room spawnRoom;
    private Room stairRoom;
    private Room itemRoom;
    private ArrayList<Item> possibleItems;
    private ArrayList<Enemy> possibleEnemies;
    private ImageView stairs = new ImageView(STAIRS);
    
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
        stairs.relocate((ROOM_WIDTH + stairs.getFitWidth()) / 2, (ROOM_HEIGHT + stairs.getFitHeight()) / 2);
        stairs.setVisible(true);

        
        
    }

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
        Random rand = new Random();
        Room chosenRoom = null;
        do{
            chosenRoom = thisFloor.get(rand.nextInt(thisFloor.size()));
            System.out.println("Item " + chosenRoom.toString());
        }while(chosenRoom == spawnRoom || chosenRoom == stairRoom);
        itemRoom = chosenRoom;
        System.out.println("list size: " + possibleItems.size());
        itemRoom.setItem(possibleItems.get(rand.nextInt(possibleItems.size()))); //there was an exception here
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
            int healthDelta = lineScan.nextInt();
            double speedDelta = lineScan.nextDouble();
            int damageDelta = lineScan.nextInt();
            double attackSpeedDelta = lineScan.nextDouble();
            possibleItems.add(new Item(name, itemImg, healthDelta, speedDelta, damageDelta, attackSpeedDelta));
        }
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
