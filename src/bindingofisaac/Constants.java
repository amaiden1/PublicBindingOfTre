package bindingofisaac;

import javafx.scene.image.ImageView;

public abstract class Constants {

	/*
	 * CONSTANTS ONLY. All fields must be public and static.
	 *
	 * Nothing else should go in this file. It also
	 * should not be instantiated; the class's abstract
	 * property prevents this.
	 *
	 * For easier usage, add 'import static Constants' to
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
			LEFT = 3;

	public static final ImageView
			DOOR_UP = ImageHelper.imageFromSource("door_up.png"),
			DOOR_RIGHT = ImageHelper.imageFromSource("door_left.png"),
			DOOR_DOWN = ImageHelper.imageFromSource("door_down.png"),
			DOOR_LEFT = ImageHelper.imageFromSource("door_left.png");




}