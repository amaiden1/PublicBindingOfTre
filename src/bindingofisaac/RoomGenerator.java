package bindingofisaac;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
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
}
