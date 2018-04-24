/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import static bindingofisaac.Constants.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
	private double x;
	private double y;

	private int tickLength;
	private int travelTime;
	private int steps;
	private double xDist;
	private double yDist;
	private double xStep;
	private double yStep;
	private double cornerX;
	private double cornerY;
	private int totalProgress;
	private boolean alreadyDespawned;

	public Bullet(double startX, double startY, double destinationX, double destinationY, int travelTime, int tickLength, Image imgSrc){

		try{
			bulletSprite = new ImageView(imgSrc);
			bulletSprite.setPreserveRatio(true);
			bulletSprite.setFitHeight(10);
		}
		catch(Exception e){
			System.out.println("bullet image not found... using default");
			bulletSprite = new ImageView(STAIRS);
		}
		this.startX = startX;
		this.startY = startY;
		this.endX = destinationX;
		this.endY = destinationY;
		this.x = startX;
		this.y = startY;

		Main.player.getCurrentRoom().getRoomPane().getChildren().add(bulletSprite);
		bulletSprite.relocate(startX,startY);

		// shooting stuff

		cornerX = endX;
		cornerY = startY;
		this.tickLength = tickLength;
		this.travelTime = travelTime;
		steps = travelTime / tickLength;

		xDist = cornerX - startX;
		yDist = endY - cornerY;
		xStep = xDist / steps;
		yStep = yDist / steps;
		
		totalProgress = 0;
		timerIndex = Main.player.getGame().getController().getTimer().addTickAndPlay(tickLength, steps, event -> {
			totalProgress += tickLength;
			updateBullet();
		});

	}

	protected void updateBullet(){

		x += xStep;
		y += yStep;
		bulletSprite.relocate(x, y);
		checkCollision();
		if (totalProgress >= travelTime) {
			System.out.println(this + " despawn called due to travel time");
			if (!alreadyDespawned) {
				despawn();
			}
		}

	}

	protected void checkCollision(){
		// override me
	}

	protected void despawn() {
		Main.player.getGame().getController().getTimer().removeNull(timerIndex);
		Main.player.getCurrentRoom().getRoomPane().getChildren().remove(bulletSprite);
	}

	protected ImageView getBulletSprite() {
		return bulletSprite;
	}
}
