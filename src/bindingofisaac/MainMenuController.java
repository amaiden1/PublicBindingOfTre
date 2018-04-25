/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingofisaac;

import static bindingofisaac.Constants.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Controller class for MainMenu.
 *
 * @author Austin Maiden and Nolan Ierardi
 */
public class MainMenuController {

    private Stage primaryStage;
    
    @FXML
    private Button newGameButton;
    @FXML
    private Button loadGameButton;
    @FXML
    private Button leaderboardsButton;
    @FXML
    private Button quitButton;

    /**
     * Constructor for MainMenuController.
     * @param givenStage the Stage to use for display
     */
    public MainMenuController(Stage givenStage){
        primaryStage = givenStage;
    }

    /**
     * Shows leaderboard window.
     */
    @FXML
    public void leaderboardsButtonPressed() {
        Stage temporaryStage = new Stage();
        Pane primaryPane = new Pane();
        Scene lbScene = new Scene(primaryPane);
        temporaryStage.setScene(lbScene);
        
        Leaderboard board = new Leaderboard();
        primaryPane.getChildren().add(board.display());
        temporaryStage.show();
    }

    /**
     * Underline effect when mouse enters Leaderboard button.
     */
    @FXML
    public void leaderboardsMouseEntered() {
        leaderboardsButton.setUnderline(true);
    }

    /**
     * Underline effect when mouse enters Leaderboard button.
     */
    @FXML
    public void leaderboardsMouseExited() {
        leaderboardsButton.setUnderline(false);
    }

    /**
     * Starts a new game.
     */
    @FXML
    public void newGameButtonPressed() {
        Main.player = new Player(ROOM_WIDTH / 2, ROOM_HEIGHT / 2, primaryStage);
	Main.player.getGame().nextFloor();
    }

    /**
     * Underline effect when mouse enters New Game button.
     */
    @FXML
    public void newGameMouseEntered() {
        newGameButton.setUnderline(true);
    }

    /**
     * Underline effect when mouse enters New Game button.
     */
    @FXML
    public void newGameMouseExited() {
        newGameButton.setUnderline(false);
    }

    /**
     * Closes the game.
     */
    @FXML
    public void quitButtonPressed() {
        Platform.exit();
    }

    /**
     * Underline effect when mouse enters Quit button.
     */
    @FXML
    public void quitMouseEntered() {
        quitButton.setUnderline(true);
    }

    /**
     * Underline effect when mouse enters Quit button.
     */
    @FXML
    public void quitMouseExited() {
        quitButton.setUnderline(false);
    }

}
