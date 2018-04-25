/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingofisaac;

import javafx.scene.image.Image;

/**
 * Represents a Bullet shot from an Enemy.
 * @author Austin
 */
public class EnemyBullet extends Bullet {

	private int damage;

	/**
	 * Constructor for Bullet class.
	 *
	 * @param startX the x position this Bullet originates from
	 * @param startY the y position this Bullet originates from
	 * @param destinationX the x position this Bullet should move toward
	 * @param destinationY the y position this Bullet should move toward
	 * @param travelTime the total time (ms) this Bullet should take to travel
	 * @param tickLength the time (ms) per each update (tick)
	 * @param imageSrc the Image that should be used
	 * @param damage the damage that this bullet does
	 */
	public EnemyBullet(double startX, double startY, double destinationX, double destinationY, int travelTime, int tickLength, Image imageSrc, int damage){
		super(startX, startY, destinationX, destinationY, travelTime, tickLength, imageSrc);
		this.damage = damage;
	}

	/**
	 * Checks collision between this bullet and the player.
	 */
	@Override
	protected void checkCollision(){
		if((Main.player.getImageView().getBoundsInParent().intersects(super.getBulletSprite().getBoundsInParent())) && Main.player != null){
			Main.player.takeDamage(damage);
			despawn();
		}
	}

}
