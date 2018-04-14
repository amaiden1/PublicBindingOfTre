/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import static bindingofisaac.Constants.*;

/**
 *
 * @author Nolan
 */
public class Main extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception{

		Pane primaryPane = new Pane();
		Scene primaryScene = new Scene(primaryPane);
		primaryStage = new Stage();
		primaryStage.setScene(primaryScene);
		primaryPane.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);
		//primaryStage.setMaxHeight(ROOM_HEIGHT + 30);
		//primaryStage.setMaxWidth(ROOM_WIDTH);
		//primaryStage.setResizable(false);

		LayoutGenerator lg = new LayoutGenerator();
		Floor floor = lg.getFloor();
		System.out.println(floor);
		
		Room spawnRoom = floor.getSpawnRoom();
		primaryPane.getChildren().add(spawnRoom.getRoomPane());
		primaryPane.getChildren().add(lg.getMiniMap().getPane());
		
		primaryStage.show();
		
		/*
		* Actions to perform on application start
		*/

	}


	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
