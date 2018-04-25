/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bindingofisaac;

import static bindingofisaac.Constants.*;
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
 *
 * @author amaiden1
 */
public class StartGame {
    
    private Stage primaryStage;
    private Timeline musicTimer;
    private int songIndex;
    Pane mainMenu;
    
    public StartGame(Stage givenStage) throws IOException{
        Random rand = new Random();
        songIndex = 0;
        primaryStage = givenStage;
        openMenu();
        musicTimer = new Timeline(new KeyFrame(Duration.millis(2000), event -> {
            System.out.println("Status: " + SONGS.get(songIndex).getStatus() + 
                    "\ncurrent time: " + SONGS.get(songIndex).getCurrentTime() + 
                    "\ntotal duration: " + SONGS.get(songIndex).getTotalDuration());
            if((SONGS.get(songIndex).getCurrentTime().greaterThanOrEqualTo(SONGS.get(songIndex).getTotalDuration()))||
                    (SONGS.get(songIndex).getStatus() == MediaPlayer.Status.READY)){
                System.out.println("Now playing: " + SONGS.get(songIndex));
                songIndex = rand.nextInt(SONGS.size());
                SONGS.get(songIndex).play();
            }
        }));
        musicTimer.setCycleCount(Timeline.INDEFINITE);
        musicTimer.play();
    }
    
    public void openMenu() throws IOException{
        
        MainMenuController controller = new MainMenuController(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
        loader.setController(controller);
        mainMenu = loader.load();
        
        primaryStage.setScene(new Scene(mainMenu));
        primaryStage.show(); 
    }
    
}
