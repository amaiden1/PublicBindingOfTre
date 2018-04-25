/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingoftre;

import static bindingoftre.Constants.*;
import java.io.IOException;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Launches the game from the main menu. Also manages the audio.
 * @author Austin Maiden and Nolan Ierardi
 */
public class GameStarter {
    
    private Stage primaryStage;
    private Timeline musicTimer;
    private int songIndex;
    private Pane mainMenu;

	/**
	 * Constructor for GameStarter.
	 * @param givenStage the Stage to use
	 * @throws IOException
	 */
	public GameStarter(Stage givenStage) throws IOException{
        Random rand = new Random();
        songIndex = 0;
        primaryStage = givenStage;
        openMenu();
        musicTimer = new Timeline(new KeyFrame(Duration.millis(2000), event -> {
            if((SONGS.get(songIndex).getCurrentTime().greaterThanOrEqualTo(SONGS.get(songIndex).getTotalDuration()))||
                    (SONGS.get(songIndex).getStatus() == MediaPlayer.Status.READY)){
                SONGS.get(songIndex).stop();
	            if (Main.DEBUG) System.out.println("Now playing: " + SONGS.get(songIndex));
                songIndex = rand.nextInt(SONGS.size());
                SONGS.get(songIndex).play();
            }
        }));
        musicTimer.setCycleCount(Timeline.INDEFINITE);
        musicTimer.play();
    }
    
    private void openMenu() throws IOException{
        
        MainMenuController controller = new MainMenuController(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        loader.setController(controller);
        mainMenu = loader.load();
        
        primaryStage.setScene(new Scene(mainMenu));
        primaryStage.show(); 
    }
    
}
