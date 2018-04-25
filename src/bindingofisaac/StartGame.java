/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bindingofisaac;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author amaiden1
 */
public class StartGame {
    
    private Stage primaryStage;
    Pane mainMenu;
    
    public StartGame(Stage givenStage) throws IOException{
        primaryStage = givenStage;
        openMenu();
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
