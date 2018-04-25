package bindingofisaac;

import java.io.File;
import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
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
			ROOM_WIDTH = 1200, // 13 * 80
			ROOM_HEIGHT = 720, // 7 * 80 - 1200x720 for 1080p
			ROOM_INNER_OFFSET = 80,

			// UCD directions
			UP = 0,
			RIGHT = 1,
			DOWN = 2,
			LEFT = 3,

			ROOM_TYPE_REGULAR = 0,
			ROOM_TYPE_ITEM = 1,
			ROOM_TYPE_BOSS = 2;

	public static final Image // Locked_Door.png, Open_Door.png
			LOCKED_DOOR = new Image("/img/Locked_Door.png"),
			LOCKED_STAIRS = new Image("/img/Locked_Stairs.png"),
			ROOM_PLAYABLE_AREA = new Image("/img/Playable_Area.png"),
			ROOM_WALLS = new Image("/img/Walls.png"),
			OPEN_DOOR = new Image("/img/Open_Door.png"),
			AERODYNAMICS = new Image("/img/Aerodynamics.png"),
			PLAYER_BACK = new Image("/img/Back_Tre.png"),
			PLAYER_FRONT = new Image("/img/Front_Tre.png"),
			PLAYER_LEFT = new Image("/img/Left_Tre.png"),
			PLAYER_RIGHT = new Image("/img/Right_Tre.png"),
			LOVE = new Image("/img/Love.png"),
			MUMMY = new Image("/img/Mummy.png"),
			PLAYER = new Image("/img/Player.png"),
			RUNNING_SHOES = new Image("/img/Running_Shoes.png"),
			STAIRS = new Image("/img/Stairs.png"),
			STEROIDS = new Image("/img/Steroids.png"),
			LIFT_WEIGHTS = new Image("/img/Weights.png"),
			WITCH = new Image("/img/Witch.png"),
			LIGHTNING_BOLT = new Image("/img/Lightning_Bolt.png");
			
	
	public static final Background 
			ROOM = new Background(new BackgroundFill(Paint.valueOf("BLUE"), CornerRadii.EMPTY, Insets.EMPTY)),
			EMPTY = new Background(new BackgroundFill(Paint.valueOf("WHITE"), CornerRadii.EMPTY, Insets.EMPTY)),
			SPAWN = new Background(new BackgroundFill(Paint.valueOf("YELLOW"), CornerRadii.EMPTY, Insets.EMPTY)),
			ITEM = new Background(new BackgroundFill(Paint.valueOf("GREEN"), CornerRadii.EMPTY, Insets.EMPTY)),
			CURRENT = new Background(new BackgroundFill(Paint.valueOf("GREEN"), CornerRadii.EMPTY, Insets.EMPTY));
	
        public static final Media
                SONG_1 = new Media(new File("./src/Sounds/Song1.mp3").toURI().toString()),
                SONG_2 = new Media(new File("./src/Sounds/Song2.mp3").toURI().toString()),
                SONG_3 = new Media(new File("./src/Sounds/Song3.mp3").toURI().toString());
        
        public static final MediaPlayer 
                SONG1_PLAYER = new MediaPlayer(SONG_1),
                SONG2_PLAYER = new MediaPlayer(SONG_2),
                SONG3_PLAYER = new MediaPlayer(SONG_3);

        public static final ArrayList<MediaPlayer> SONGS = new ArrayList<MediaPlayer>(){{
            add(SONG1_PLAYER);
            add(SONG2_PLAYER);
            add(SONG3_PLAYER);
        }};
	/*
	 * Forcibly prevents class instantation. The default
	 * constructor is public, so if we specify a private
	 * constructor, a default public constructor is not
	 * created. Thus it tries to use this one, and cannot,
	 * so an error is thrown.
	 */
	private Constants() {}


}
