/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingofisaac;

import static bindingofisaac.Constants.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents a bullet entity that has been fired
 * from either a Player or Enemy.
 * @author Austin Maiden and Nolan Ierardi
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

	/**
	 * Constructor for Bullet class.
	 *
	 * @param startX the x position this Bullet originates from
	 * @param startY the y position this Bullet originates from
	 * @param destinationX the x position this Bullet should move toward
	 * @param destinationY the y position this Bullet should move toward
	 * @param travelTime the total time (ms) this Bullet should take to travel
	 * @param tickLength the time (ms) per each update (tick)
	 * @param imgSrc the Image that should be used
	 */
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


	private void updateBullet(){

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

	/**
	 * Checks surrounding areas for a collision. By default, this will throw
	 * an UnsupportedOperationException, as this method is intended to be
	 * overridden in a class extending Bullet.
	 */
	protected void checkCollision(){
		throw new UnsupportedOperationException("The method checkCollision() should be overridden in the subclass.");
	}

	/**
	 * Removes this bullet from the screen and stops the tick.
	 */
	protected void despawn() {
		Main.player.getGame().getController().getTimer().removeNull(timerIndex);
		Main.player.getCurrentRoom().getRoomPane().getChildren().remove(bulletSprite);
	}

	/**
	 * Returns the sprite (ImageView) of this Bullet.
	 * @return ImageView of Bullet
	 */
	protected ImageView getBulletSprite() {
		return bulletSprite;
	}
}
