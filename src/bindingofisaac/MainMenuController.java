package bindingofisaac;

import static bindingofisaac.Constants.ROOM_HEIGHT;
import static bindingofisaac.Constants.ROOM_WIDTH;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

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

    public MainMenuController(Stage givenStage){
        primaryStage = givenStage;
    }
    
    @FXML
    void leaderboardsButtonPressed(ActionEvent event) {
        
    }
    
    @FXML
    void leaderboardsMouseEntered(MouseEvent event) {
        leaderboardsButton.setUnderline(true);
    }

    @FXML
    void leaderboardsMouseExited(MouseEvent event) {
        leaderboardsButton.setUnderline(false);
    }
    
    @FXML
    void newGameButtonPressed(ActionEvent event) {
        Main.player = new Player(ROOM_WIDTH / 2, ROOM_HEIGHT / 2, primaryStage);
	Main.player.getGame().nextFloor();
    }
    
    @FXML
    void newGameMouseEntered(MouseEvent event) {
        newGameButton.setUnderline(true);
    }

    @FXML
    void newGameMouseExited(MouseEvent event) {
        newGameButton.setUnderline(false);
    }

    @FXML
    void quitButtonPressed(ActionEvent event) {
        
    }
    
    @FXML
    void quitMouseEntered(MouseEvent event) {
        quitButton.setUnderline(true);
    }

    @FXML
    void quitMouseExited(MouseEvent event) {
        quitButton.setUnderline(false);
    }

}
