package bindingofisaac;

import javafx.scene.image.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RoomGenerator {

	private final int MIN_ENEMIES = 2;
	private final int MAX_ENEMIES = 8;

	private ArrayList<Item> items;
	private ArrayList<Enemy> enemies;
	private Enemy boss;
	private ImageView background;
	private LayoutGenerator layoutGen;

	private final Random rand = new Random();

	public RoomGenerator(LayoutGenerator gen, ArrayList<Enemy> possibleEnemies, ArrayList<Item> possibleItems, Enemy boss, ImageView background) {
		enemies = possibleEnemies;
		items = possibleItems;
		this.boss = boss;
	}

	public Room generateRegularRoom(boolean[] doors) {
		Room room = new Room(background, background);
		// randomize enemies
		for (int i = 0 ; i < MIN_ENEMIES + rand.nextInt(MAX_ENEMIES - MIN_ENEMIES) ; i++) {
			room.getEnemies().add(enemies.get(rand.nextInt(enemies.size())));
		}
		// setup doors
		room.setDoors(doors);

		return room;
	}

	public Room generateItemRoom(boolean[] doors) {
		Room room = new Room(background, background);
		// randomize item
		room.setItem(items.get(rand.nextInt(items.size())));
		// setup doors
		room.setDoors(doors);
		// set cleared to true. this will unlock all the doors
		room.setCleared(true);

		return room;
	}

	public Room generateFinalRoom(boolean[] doors) {
		Room room = new Room(background, background);
		// insert boss
		room.getEnemies().add(boss);
		// setup doors
		room.setDoors(doors);
		/*
		 * Todo: add/create final room staircase
		 */

		return room;
	}
}
