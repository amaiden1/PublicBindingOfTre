package bindingofisaac;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class Room {

	private final int ROOM_WIDTH = 1040; // 13 * 80
	private final int ROOM_HEIGHT = 560; // 7 * 80n // 1200x720
	private final ImageView BACKGROUND = ImageHelper.imageFromSource("room.png");
	private boolean isOccupied;

	public Room() {

	}

}
