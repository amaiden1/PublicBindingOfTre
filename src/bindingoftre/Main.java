/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingoftre;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Driver class for package.
 * @author Austin Maiden and Nolan Ierardi
 */
public class Main extends Application{

	/*
	 * Set to true to enable global debug mode.
	 */
	protected static boolean DEBUG = false;

	/*
	 * The field for Player is intentionally protected and static.
	 * This is necessary as many methods specific to the player are
	 * frequently accessed, and it is infeasible to ensure that the
	 * Player object can be passed to every object that needs it.
	 */
	protected static Player player;
	
	@Override
	public void start(Stage primaryStage) throws Exception{
		GameStarter start = new GameStarter(primaryStage);
	}
	
	/**
	 * Launches the application.
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
}
