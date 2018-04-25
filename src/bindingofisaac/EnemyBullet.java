/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingofisaac;

import javafx.scene.image.Image;

/**
 *
 * @author Austin
 */
public class EnemyBullet extends Bullet {

	private int damage;

	public EnemyBullet(double startX, double startY, double destinationX, double destinationY, int travelTime, int tickLength, Image imageSrc, int damage){
		super(startX, startY, destinationX, destinationY, travelTime, tickLength, imageSrc);
		this.damage = damage;
	}

	@Override
	protected void checkCollision(){
		if((Main.player.getImageView().getBoundsInParent().intersects(super.getBulletSprite().getBoundsInParent())) && Main.player != null){
			Main.player.takeDamage(damage);
			despawn();
		}
	}

}
