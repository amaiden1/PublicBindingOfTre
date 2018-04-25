/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import static bindingofisaac.Constants.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Nolan
 */
public class Main extends Application{
	
	protected static Player player;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		primaryStage = new Stage();
                
                MainMenuController controller = new MainMenuController(primaryStage);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainMenu.fxml"));
                loader.setController(controller);
                Pane mainMenu = loader.load();
                
                primaryStage.setScene(new Scene(mainMenu));
                primaryStage.show();
                

	}

	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
