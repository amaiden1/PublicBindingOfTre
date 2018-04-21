/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.security.interfaces.DSAPrivateKey;
import java.util.ArrayList;

/**
 *
 * @author Austin
 */
public class Bullet {
	
	private int timerIndex;
	private ImageView bulletSprite;
	private int direction;
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	ArrayList<Double> xStepList;
	ArrayList<Double> yStepList;
	double distEnemyToCorner;
	double distCornerToPlayer;
	int triangleNum;
	double baseFactor;
	double heightFactor;
	int ticks;
	int currentPosIndex;

	public Bullet(double startX, double startY, double destinationX, double destinationY, String imageSrc){
		bulletSprite = new ImageView(imageSrc);
		bulletSprite.setFitHeight(30);
		bulletSprite.setFitWidth(30);
		try{
			bulletSprite = new ImageView(imageSrc);
		}
		catch(Exception e){
			System.out.println("bullet image not found... using default");
			bulletSprite = new ImageView("/img/Stairs.png");
		}
		this.startX = startX;
		this.startY = startY;
		this.endX = Main.player.getX();
		this.endY = Main.player.getY();

		Main.player.getCurrentRoom().getRoomPane().getChildren().add(bulletSprite);
		bulletSprite.relocate(startX,startY);
		bulletSprite.setRotate(90);

		ticks = 50;

		timerIndex = Main.player.getGame().getController().getTimer().addTickAndPlay(5, ticks, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                updateBullet();
            }
        });

		// shooting stuff
		xStepList = new ArrayList<>();
		yStepList = new ArrayList<>();
		distEnemyToCorner = getDistance(startX, startY, startX, endY);
		distCornerToPlayer = getDistance(startX, endY, endX, endY);
		baseFactor = distEnemyToCorner / ticks;
		heightFactor = distCornerToPlayer / ticks;
		for (int b = 0 ; b < distEnemyToCorner ; b++) {
			xStepList.add(baseFactor * b);
			System.out.println("x step loop ran!");
		}
		for (int h = 0 ; h < distCornerToPlayer ; h++) {
			yStepList.add(heightFactor * h);
			System.out.println("y step loop ran!");
		}
		System.out.println("dist enemy to corner: " + distEnemyToCorner);
		System.out.println("dist corner to player: " + distCornerToPlayer);
		System.out.println("x length: " + xStepList.size() + " y length: " + yStepList.size());
		currentPosIndex = 0;

		
	}

	/*
	SOLUTION IN PROGRESS I AM WORKING ON!
	It aims to divide the area up into a bunch of triangles.
	Please see the picture I sent you in text.
	See if you might be able to get this to work.
	 */

	private double getDistance(double x1, double y1, double x2, double y2) {
		double xDist = Math.pow((x2 - x1), 2);
		double yDist = Math.pow((y2 - y1), 2);
		double sqrt = Math.sqrt(xDist - yDist);
		return sqrt;
	}
	
	public void updateBullet(){

		bulletSprite.relocate(xStepList.get(currentPosIndex), yStepList.get(currentPosIndex));
		if (xStepList.get(currentPosIndex + 1) != null && yStepList.get(currentPosIndex + 1) != null) {
			// there is another position
			currentPosIndex++;
		} else {
			// not another position
			// remove the bullet, but for now do nothing
		}
	}
}
