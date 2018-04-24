/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import com.sun.deploy.net.UpdateTracker;
import com.sun.jmx.snmp.agent.SnmpMibOid;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.layout.*;

import static bindingofisaac.Constants.*;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.omg.PortableInterceptor.INACTIVE;

import java.awt.font.TextHitInfo;

/**
 *
 * @author Austin
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
		//hudBox.setBackground(new Background(new BackgroundFill(Paint.valueOf("BLACK"), CornerRadii.EMPTY, Insets.EMPTY)));
		//hudBox.setOpacity(0.5);
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
	
	public Pane getPane(){
        return outerBox;
	}
	
	public Scene getStage(){
		mapStage = new Scene(miniMap);
		return mapStage;
	}

	public void updateMap(int oldX, int oldY, int x, int y) {
		grid[oldX][oldY].setBackground(ROOM);
		grid[x][y].setBackground(CURRENT);
		System.out.println("updateMap fired, grid x: " + x + " grid y: " + y);
	}

	public void updateHud() {
		healthLabel.setText("Health: " + Main.player.getHealth());
		floorLabel.setText("Floor: " + Main.player.getGame().getFloorLevel());
	}
	
	public void setTile(Background type, int x, int y){
            int col = x + 3;
            int row = y + 3;
            grid[col][row].setBackground(type);
            grid[col][row].setOpacity(.5);
            //grid[col][row].setText(x + "," + y);
	}
}
