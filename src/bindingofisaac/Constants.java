package bindingofisaac;

import javafx.geometry.Insets;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

public final class Constants {

	/*
	 * CONSTANTS ONLY. All fields must be public and static.
	 *
	 * Nothing else should go in this file. It also should
	 * not be instantiated.
	 *
	 * For easier usage, add
	 * 'import static bindingofisaac.Constants.*' to
	 * your imports.
	 */
	public static final int
			ROOM_WIDTH = 1040, // 13 * 80
			ROOM_HEIGHT = 560, // 7 * 80 - 1200x720 for 1080p
			ROOM_INNER_OFFSET = 60,

			// UCD directions
			UP = 0,
			RIGHT = 1,
			DOWN = 2,
			LEFT = 3,

			ROOM_TYPE_REGULAR = 0,
			ROOM_TYPE_ITEM = 1,
			ROOM_TYPE_BOSS = 2;

	public static final ImageView // Locked_Door.png, Open_Door.png
			LOCKED_DOOR = ImageHelper.imageFromSource("Locked_Door.png"),
			DOOR_RIGHT = ImageHelper.imageFromSource("Open_Door.png"),
			ROOM_PLAYABLE_AREA = ImageHelper.imageFromSource("Playable_Area.png"),
			ROOM_WALLS = ImageHelper.imageFromSource("Walls.png");
	
	public static final Background 
			ROOM = new Background(new BackgroundFill(Paint.valueOf("BLUE"), CornerRadii.EMPTY, Insets.EMPTY)),
			EMPTY = new Background(new BackgroundFill(Paint.valueOf("WHITE"), CornerRadii.EMPTY, Insets.EMPTY)),
			SPAWN = new Background(new BackgroundFill(Paint.valueOf("YELLOW"), CornerRadii.EMPTY, Insets.EMPTY)),
			STAIRS = new Background(new BackgroundFill(Paint.valueOf("RED"), CornerRadii.EMPTY, Insets.EMPTY)),
			ITEM = new Background(new BackgroundFill(Paint.valueOf("GREEN"), CornerRadii.EMPTY, Insets.EMPTY));


	/*
	 * Forcibly prevents class instantation. The default
	 * constructor is public, so if we specify a private
	 * constructor, a default public constructor is not
	 * created. Thus it tries to use this one, and cannot,
	 * so an error is thrown.
	 */
	private Constants() {}


}
