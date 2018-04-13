/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import static bindingofisaac.Constants.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Paint;

/**
 *
 * @author Austin
 */
public class MiniMap {
	
public final Background ROOM = new Background(new BackgroundFill(Paint.valueOf("BLUE"), CornerRadii.EMPTY, Insets.EMPTY));
public final Background EMPTY = new Background(new BackgroundFill(Paint.valueOf("WHITE"), CornerRadii.EMPTY, Insets.EMPTY));
public final Background SPAWN = new Background(new BackgroundFill(Paint.valueOf("YELLOW"), CornerRadii.EMPTY, Insets.EMPTY));
public final Background STAIRS = new Background(new BackgroundFill(Paint.valueOf("RED"), CornerRadii.EMPTY, Insets.EMPTY));
public final Background ITEM = new Background(new BackgroundFill(Paint.valueOf("GREEN"), CornerRadii.EMPTY, Insets.EMPTY));
	
	private Pane miniMap;
	private Button[][] grid;
	private Scene mapStage;
	
	public MiniMap(){
            miniMap = new Pane();
            grid = new Button[7][7];
		for(int col = 0; col < grid.length; col++){
            for(int row = 0; row < grid[col].length; row++){
				grid[col][row] = new Button();
				grid[col][row].setBackground(EMPTY);
				miniMap.getChildren().add(grid[col][row]);
				grid[col][row].setPrefSize(50, 50);
				grid[col][row].relocate(col *  50, (6 -row) * 50);
            }
		}
            grid[3][3].setBackground(SPAWN);
			miniMap.relocate(ROOM_WIDTH - miniMap.getWidth(), 0);
	}
	
	public Pane getPane(){
        return miniMap;
	}
	
	public Scene getStage(){
		mapStage = new Scene(miniMap);
		return mapStage;
	}
	
	public void setTile(Background type, int x, int y){
            int col = x + 3;
            int row = y + 3;
            grid[col][row].setBackground(type);
				grid[col][row].setText(x + "," + y);
	}
}
