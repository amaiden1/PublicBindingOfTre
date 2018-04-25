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
        Stage temporaryStage = new Stage();
        Pane primaryPane = new Pane();
        Scene lbScene = new Scene(primaryPane);
        temporaryStage.setScene(lbScene);
        
        Leaderboard board = new Leaderboard();
        primaryPane.getChildren().add(board.display());
        temporaryStage.show();
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
        Platform.exit();
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
