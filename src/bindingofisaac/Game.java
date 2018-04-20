/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import static bindingofisaac.Constants.ROOM_HEIGHT;
import static bindingofisaac.Constants.ROOM_WIDTH;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Austin
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
	
	public Game(Stage primaryStage, Player player){
		this.player = player;
		
		primaryPane = new Pane();
		Scene primaryScene = new Scene(primaryPane);
		primaryStage.setScene(primaryScene);
		primaryStage.setMaxHeight(ROOM_HEIGHT + 30);
		primaryStage.setMaxWidth(ROOM_WIDTH);
		primaryStage.setResizable(false);
		
		primaryPane.setPrefSize(ROOM_WIDTH, ROOM_HEIGHT);
		primaryPane.setMaxSize(ROOM_WIDTH, ROOM_HEIGHT);
		
		floorLevel = 0;
		isFloorFinished = new SimpleBooleanProperty(false);
		isFloorFinished.addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				if(newValue){
					try{
						nextFloor();
					}
					catch (Exception e){
						//do nothing
					}
				}
			}
		});
		
        controller = new PlayerController(primaryScene, player);
                
		primaryStage.show();
	}
	
	public void updatePane(double newX, double newY){
		primaryPane.getChildren().clear();
		player.getCurrentRoom().getRoomPane().getChildren().add(player.getImageView()); //adds player image to current room pane
		primaryPane.getChildren().add(player.getCurrentRoom().getRoomPane());
		player.setX(newX);
		player.setY(newY);
		primaryPane.getChildren().add(lg.getMiniMap().getPane());
		
	}
	
	public void nextFloor() {
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
	}
	
	public Floor getFloor(){
		return floor;
	}

	public Player getPlayer() {
		return player;
	}

	public PlayerController getController() {
		return controller;
	}

	public Pane getPrimaryPane() {
		return primaryPane;
	}

	public LayoutGenerator getLg() {
		return lg;
	}

	public Room getSpawnRoom() {
		return spawnRoom;
	}

	public int getFloorLevel() {
		return floorLevel;
	}

	public BooleanProperty getIsFloorFinished() {
		return isFloorFinished;
	}

	public void setPrimaryPane(Pane primaryPane) {
		this.primaryPane = primaryPane;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public void setSpawnRoom(Room spawnRoom) {
		this.spawnRoom = spawnRoom;
	}

	public void setFloorLevel(int floorLevel) {
		this.floorLevel = floorLevel;
	}

	public void setIsFloorFinished(boolean value) {
		isFloorFinished.set(true);
	}
	
	
	
	
}
