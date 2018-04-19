/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import static bindingofisaac.Constants.*;
import javafx.application.Application;
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
		
		player = new Player(ROOM_WIDTH / 2, ROOM_HEIGHT / 2, primaryStage);

	}

	
	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
