package bindingofisaac;

import static bindingofisaac.Constants.*;
import java.util.ArrayList;
import java.util.Random;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class LayoutGenerator {

	public final Background ROOM = new Background(new BackgroundFill(Paint.valueOf("BLUE"), CornerRadii.EMPTY, Insets.EMPTY));
	private final int MAX = 3;
	private final int MIN = -3;
	private final int STARTING_X = 0;
	private final int STARTING_Y = 0;
	private ArrayList<Integer> possibleConnectedRooms;
	
	private ArrayList<Integer> roomsXList;
	private ArrayList<Integer> roomsYList;
	private int numberOfRooms;
	private int currentNumberOfRooms;
	private MiniMap miniMap;
	private ArrayList<Room> floorRooms;
	Floor floor;
	
	//starting room will always be 0,0
	
	private int determineNumberOfRooms(){
		int result = 12;
		Random numGen = new Random();
		result += numGen.nextInt(5);
		return result;
	}
	
	private boolean isInBounds(int givenNum){
		boolean result = true;
		if(givenNum < MIN || givenNum > MAX)
			result = false;
		return result;
	}
	
	private boolean containsRoom(int x, int y){
		boolean result = false;
        for(int i = 0; i < roomsXList.size(); i++){
            if(roomsXList.get(i) == x && roomsYList.get(i) == y)
			result = true;
        }
		return result;
	}
	
	private void findNextRoom(){
		boolean isValid = false;
		int thisX, nextX;
		int thisY, nextY;
        int index;
		do{
			Random numGen = new Random();
			index = numGen.nextInt(roomsXList.size());
			thisX = roomsXList.get(index);
			thisY = roomsYList.get(index);
			int direction = numGen.nextInt(4); // 0 = N, 1 = E, 2 = S, 3 = W
			
			nextX = thisX;
			nextY = thisY;
			
			switch(direction){
				case 0: nextY++;
				isValid = isInBounds(nextY);
					break;
				case 1: nextX++;
				isValid = isInBounds(nextX);
					break;
				case 2: nextY--;
				isValid = isInBounds(nextY);
					break;
				case 3: nextX--;
				isValid = isInBounds(nextX);
					break;
				default: System.out.println("this is not a valid direction");
					break;
			}
			System.out.println("nextX, nextY = (" + nextX + "," + nextY + ")");
                        
		}while(!isValid || containsRoom(nextX, nextY));
		System.out.println("loop complete");
		roomsXList.add(nextX);
		roomsYList.add(nextY);
		miniMap.setTile(ROOM, nextX, nextY);
	}
	
	public LayoutGenerator() throws Exception{
		miniMap = new MiniMap();
		roomsXList = new ArrayList<Integer>();
		roomsYList = new ArrayList<Integer>();
		floorRooms = new ArrayList<Room>();
		boolean isValidMap = false;
        roomsXList.clear();
        roomsYList.clear();
                    
        numberOfRooms = determineNumberOfRooms();
        roomsXList.add(STARTING_X);
        roomsYList.add(STARTING_Y);
        while(currentNumberOfRooms < numberOfRooms){
			findNextRoom();
			currentNumberOfRooms = roomsXList.size();
        }
		addRooms();
		addFloorDoors();
		floor = new Floor(floorRooms);
	}
        
    private void addRooms(){
        for(int i = 0; i < roomsXList.size(); i++){
            Room room = new Room(roomsXList.get(i), roomsYList.get(i));
			floorRooms.add(room);
        }
    }
		
	public Floor getFloor(){
		return floor;
	}
	
	public MiniMap getMiniMap(){
		return miniMap;
	}
	
	/***********************************************CREATING DOORS**********************************************************************/
		
	
	public void addFloorDoors() throws Exception{
		boolean[] doorValues = new boolean[4];
		for(Room thisRoom : floorRooms){ 
			doorValues[0] = isRoomInDirection(thisRoom, 0);
			doorValues[1] = isRoomInDirection(thisRoom, 1);
			doorValues[2] = isRoomInDirection(thisRoom, 2);
			doorValues[3] = isRoomInDirection(thisRoom, 3);
			thisRoom.setDoors(doorValues);
		}
	}
	
	private boolean isRoomInDirection(Room room, int direction) throws Exception{
		boolean result = false;
		int x = room.getX();
		int y = room.getY();
		switch(direction){
			case UP: result = containsRoom(x, y+1);
				break;
			case RIGHT: result = containsRoom(x+1, y);
				break;
			case DOWN: result = containsRoom(x, y-1);
				break;
			case LEFT: result = containsRoom(x-1, y);
				break;
			default:
				throw new DirectionNotFoundException("Not a valid direction");
		}
		return result;
	}

}
