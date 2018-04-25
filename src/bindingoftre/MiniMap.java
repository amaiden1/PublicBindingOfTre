/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingoftre;

import javafx.scene.control.Button;
import javafx.scene.layout.*;

import static bindingoftre.Constants.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Represents a miniature map of floors.
 * @author Austin Maiden and Nolan Ierardi
 */
public class MiniMap {

	private final int MAP_SIZE = 28;
	private HBox outerBox;
	private VBox hudBox;
	private Pane miniMap;
	private Button[][] grid;
	private Scene mapStage;
	private Text healthLabel;
	private Text floorLabel;

	/**
	 * Constructor for MiniMap.
	 */
	public MiniMap(){

		outerBox = new HBox();
		outerBox.setPadding(new Insets(4,4,4,4));

		miniMap = new Pane();
		grid = new Button[7][7];
		for(int col = 0; col < grid.length; col++){
            for(int row = 0; row < grid[col].length; row++){
				grid[col][row] = new Button();
				grid[col][row].setBackground(EMPTY);
				miniMap.getChildren().add(grid[col][row]);
				grid[col][row].setPrefSize(MAP_SIZE, MAP_SIZE);
				grid[col][row].relocate((col - 1) * MAP_SIZE, (6 - row) * MAP_SIZE);
				grid[col][row].setOpacity(0);
            }
		}
		grid[3][3].setBackground(SPAWN);
		grid[3][3].setOpacity(.5);

		hudBox = new VBox();
		hudBox.setPadding(new Insets(2,2,2,2));
		healthLabel = new Text();
		healthLabel.setFont(Font.font(null, 13));
		healthLabel.setFill(Paint.valueOf("WHITE"));
		healthLabel.setText("health: yay");
		floorLabel = new Text();
		floorLabel.setFont(Font.font(null, 13));
		floorLabel.setFill(Paint.valueOf("WHITE"));
		floorLabel.setText("floor: yay");
		hudBox.getChildren().add(healthLabel);
		hudBox.getChildren().add(floorLabel);
		outerBox.getChildren().add(hudBox);
		outerBox.getChildren().add(miniMap);
		outerBox.relocate(900, 0);

	}

	/**
	 * Returns the displayable Pane.
	 * @return displayable Pane
	 */
	public Pane getPane(){
        return outerBox;
	}

	/**
	 * Updates the map for a new room.
	 * @param oldX old room X
	 * @param oldY old room Y
	 * @param x new room X
	 * @param y new room Y
	 */
	public void updateMap(int oldX, int oldY, int x, int y) {
		grid[oldX][oldY].setBackground(ROOM);
		grid[x][y].setBackground(CURRENT);
		if (Main.DEBUG) System.out.println("updateMap fired, grid x: " + x + " grid y: " + y);
	}

	/**
	 * Updates the stats shown on the HUD.
	 */
	public void updateHud() {
		healthLabel.setText("Health: " + Main.player.getHealth());
		floorLabel.setText("Floor: " + Main.player.getGame().getFloorLevel());
	}

	/**
	 * Sets the tile at a certain position.
	 * @param type the background to use
	 * @param x the x of tile to set
	 * @param y the y of tile to set
	 */
	public void setTile(Background type, int x, int y){
            int col = x + 3;
            int row = y + 3;
            grid[col][row].setBackground(type);
            grid[col][row].setOpacity(.5);
            //grid[col][row].setText(x + "," + y);
	}
}
