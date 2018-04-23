/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import javafx.scene.image.ImageView;

/**
 *
 * @author Austin
 */
public class EnemyBullet extends Bullet {

	public EnemyBullet(double startX, double startY, double destinationX, double destinationY, int travelTime, int tickLength, String imageSrc){
		super(startX, startY, destinationX, destinationY, travelTime, tickLength, imageSrc);
	}

	@Override
	protected void checkCollision(){
		if((Main.player.getImageView().getBoundsInParent().intersects(super.getBulletSprite().getBoundsInParent())) && Main.player != null){
			despawn();
		}
	}

}
