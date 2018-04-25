/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */
package bindingoftre;

import static bindingoftre.Constants.ROOM_HEIGHT;
import static bindingoftre.Constants.ROOM_WIDTH;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

/**
 * class representing the game data
 * @author Austin Maiden and Nolan Ierardi
 */
public class Game {
	
	private Player player;
	private PlayerController controller;
	private Pane primaryPane;
	private LayoutGenerator lg;
	private Floor floor;
	private Room spawnRoom;
	private int floorLevel;
	private BooleanProperty isFloorFinished;
	private Scene primaryScene;
	private Stage primaryStage;
	private Leaderboard board;
	
        /**
	 * constructor for game
         * @param primaryStage the stage that displays the screen
         * @param player the player
	 */
	public Game(Stage primaryStage, Player player){
		this.player = player;
		
		this.primaryStage = primaryStage;
		primaryPane = new Pane();
		primaryScene = new Scene(primaryPane);
		primaryStage.setScene(primaryScene);
		primaryStage.setMaxHeight(ROOM_HEIGHT + 30);
		primaryStage.setMaxWidth(ROOM_WIDTH);
		//primaryStage.setResizable(false);
		primaryPane.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);
		primaryPane.setMaxSize(ROOM_WIDTH, ROOM_HEIGHT);
		
		floorLevel = 0;
		isFloorFinished = new SimpleBooleanProperty(false);
		isFloorFinished.addListener((observable, oldValue, newValue) -> {
			if(newValue){
				try{
					nextFloor();
				}
				catch (Exception e) {
					System.out.println("Cannot go to the next floor: " + e.getMessage());
				}
			}
		});
                
		board = new Leaderboard();
		
        controller = new PlayerController(primaryScene, player);
                
		primaryStage.show();
		primaryPane.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);
	}
	
        /**
	 * updates the pane to match what's happening to the player
         * @param newX the new X coordinate of the player
         * @param newY the new Y coordinate of the player
         * @param previousRoomX the player's X coordinates before switching rooms
         * @param previousRoomY the player's Y coordinates before switching rooms
	 */
	public void updatePane(double newX, double newY, int previousRoomX, int previousRoomY){
		primaryPane.getChildren().clear();
		player.getCurrentRoom().getRoomPane().getChildren().add(player.getImageView()); //adds player image to current room pane
		primaryPane.getChildren().add(player.getCurrentRoom().getRoomPane());
		player.setX(newX);
		player.setY(newY);
		lg.getMiniMap().updateMap(previousRoomX + 3, previousRoomY + 3, player.getCurrentRoom().getX() + 3, player.getCurrentRoom().getY() + 3);
		primaryPane.getChildren().add(lg.getMiniMap().getPane());
		lg.getMiniMap().updateHud();

	}
	
        /**
         * takes the player to the next floor
         */
	public void nextFloor() {
		if (floorLevel == 0) {
			player.postInit();
		}
		isFloorFinished.set(false);
		floorLevel++;
		lg = new LayoutGenerator();
		floor = lg.getFloor();
		spawnRoom = floor.getSpawnRoom();
		player.setCurrentRoom(spawnRoom);
		
		player.getCurrentRoom().getRoomPane().getChildren().add(player.getImageView()); //adds player image to current room pane
		primaryPane.getChildren().add(player.getCurrentRoom().getRoomPane());
		primaryPane.getChildren().add(lg.getMiniMap().getPane());
		floor.addEnemies();
		lg.getMiniMap().updateHud();

	}

        /**
         * displays a pane to provide the player with information
         * @param autoDismiss will the pan fade away on it's own?
         * @param pane the pane that will be modified
         */
	public void displayTemporaryPane(boolean autoDismiss, Pane pane) {
		primaryPane.getChildren().add(pane);
		pane.toFront();
		pane.setOpacity(0.8);
		if (autoDismiss) {
			final boolean[] firstTickDone = {false};
			controller.getTimer().addTickAndPlay(2000, 1, event -> {
				firstTickDone[0] = true;
			});
			controller.getTimer().addTickAndPlay(10, 500, event -> {
				if (firstTickDone[0]) pane.setOpacity(pane.getOpacity() - 0.01);
			});
			controller.getTimer().addTickAndPlay(5500, 1, event -> {
				primaryPane.getChildren().remove(pane);
			});

		}
	}

        /**
         * ends the game
         */
	public void gameOver() {
		Pane loseDialogPane = new LoseDialogScreen(board.rankPlayer(floorLevel), Main.player.getGame().getFloorLevel(), primaryStage).getPane();
		primaryPane.getChildren().add(loseDialogPane);
		getController().getTimer().removeAllTicks();
	}
	
        /**
         * @return the current floor
         */
	public Floor getFloor(){
		return floor;
	}

        /**
         * @return a reference to the player
         */
	public Player getPlayer() {
		return player;
	}
        
        /**
         * @return a reference to the player's controller
         */
	public PlayerController getController() {
		return controller;
	}

        /**
         * @return a reference to the primary pane
         */
	public Pane getPrimaryPane() {
		return primaryPane;
	}

        /**
         * @return a reference to the layout generator
         */
	public LayoutGenerator getLg() {
		return lg;
	}

        /**
         * @return a reference to the spawn room
         */
	public Room getSpawnRoom() {
		return spawnRoom;
	}

        /**
         * @return the floor number
         */
	public int getFloorLevel() {
		return floorLevel;
	}

        /**
         * @return a boolean property that represents if floor is complete
         */
	public BooleanProperty getIsFloorFinished() {
		return isFloorFinished;
	}

        /**
         * setter for the primary pane
         * @param primaryPane the new pane
         */
	public void setPrimaryPane(Pane primaryPane) {
		this.primaryPane = primaryPane;
	}

        /**
         * setter for the floor
         * @param floor the new floor
         */
	public void setFloor(Floor floor) {
		this.floor = floor;
	}

        /**
         * setter for the spawn room
         * @param spawnRoom the new spawn room
         */
	public void setSpawnRoom(Room spawnRoom) {
		this.spawnRoom = spawnRoom;
	}

        /**
         * setter for the floor level
         * @param floorLevel the new floor level
         */
	public void setFloorLevel(int floorLevel) {
		this.floorLevel = floorLevel;
	}
        
        /**
         * setter for if floor is finished
         * @param value boolean representing is the floor is finished
         */
	public void setIsFloorFinished(boolean value) {
		isFloorFinished.set(true);
	}

        /**
         * getter method for the leaderboard
         * @return leaderboard
         */
	public Leaderboard getLeaderboard() {
		return board;
	}
}
