package bindingofisaac;

import javax.xml.soap.Node;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Random;

import static bindingofisaac.Constants.*;

public class LayoutGenerator {

	private RoomGenerator roomGen;
	private ArrayList<Room> roomList;
	private ArrayList<Node> components;
	private int
			prevIX,
			prevIY,
			currentIX,
			currentIY,
			nextIX,
			nextIY;
	private ArrayList<Integer>
			usedIX,
			usedIY;
	private Random rand = new Random();

	public LayoutGenerator() {
		roomList = new ArrayList<>();
		components = new ArrayList<>();
		prevIX = 0;
		prevIY = 0;
		currentIX = 0;
		currentIY = 0;
		nextIX = 0;
		nextIY = 0;
		usedIX = new ArrayList<>();
		usedIY = new ArrayList<>();
	}

	public Room nextRoom(int roomType) {

		if (currentIX == 0 && currentIY == 0) {
			// generate first room
			roomGen.generateRegularRoom(getDoors());
			return null;
		} else {

			return null;

		}

	}

	private boolean[] getDoors() {
		return null;
	}

	public HashMap<Integer, Room> finalDoorConfiguration(Room source) {
		/*
		 * stub method
		 */
		return null;
	}

}
