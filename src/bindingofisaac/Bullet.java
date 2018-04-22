/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

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

	public Bullet(double startX, double startY, double destinationX, double destinationY, int travelTime, int tickLength, String imageSrc){

		try{
			bulletSprite = new ImageView(imageSrc);
			bulletSprite.setPreserveRatio(true);
			bulletSprite.setFitHeight(10);
		}
		catch(Exception e){
			System.out.println("bullet image not found... using default");
			bulletSprite = new ImageView("/img/Stairs.png");
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

		System.out.println("======== INIT VALUES ========");
		System.out.println("startX: " + startX);
		System.out.println("startY: " + startY);
		System.out.println("endX: " + endX);
		System.out.println("endY: " + endY);
		System.out.println("cornerX: " + cornerX);
		System.out.println("cornerY: " + cornerY);
		System.out.println("tickLength: " + tickLength);
		System.out.println("travelTime: " + travelTime);
		System.out.println("steps: " + steps);
		System.out.println("xDist: " + xDist);
		System.out.println("yDist: " + yDist);
		System.out.println("xStep: " + xStep);
		System.out.println("yStep: " + yStep);

		totalProgress = 0;
		timerIndex = Main.player.getGame().getController().getTimer().addTickAndPlay(tickLength, steps, event -> {
			totalProgress += tickLength;
			updateBullet();
		});

	}

	private void updateBullet(){

		x += xStep;
		y += yStep;
		bulletSprite.relocate(x, y);
		checkCollision();
		if (totalProgress >= travelTime) {
			despawn();
		}

	}

	private void checkCollision(){
		if((Main.player.getImageView().getBoundsInParent().intersects(bulletSprite.getBoundsInParent())) && Main.player != null){
			despawn();
		}
	}

	private void despawn() {
		Main.player.getGame().getController().getTimer().removeNull(timerIndex);
		Main.player.getCurrentRoom().getRoomPane().getChildren().remove(bulletSprite);
	}
}
