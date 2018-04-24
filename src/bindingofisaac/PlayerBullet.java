/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 *
 * @author Austin
 */
public class PlayerBullet extends Bullet {

	private boolean alreadyDespawned;

    public PlayerBullet(double startX, double startY, double destinationX, double destinationY, int travelTime, int tickLength, Image imageSrc){
            super(startX, startY, destinationX, destinationY, travelTime, tickLength, imageSrc);
            this.alreadyDespawned = false;
    }

    @Override
    protected void checkCollision(){
        if (Main.player.getCurrentRoom().getEnemies() != null && Main.player.getCurrentRoom().getEnemies().size() > 0) {
	        ArrayList<Enemy> enemiesToCheck = Main.player.getCurrentRoom().getEnemies();
	        // Must be a regular for loop to avoid ConcurrentModificationExceptions
            for (int i = 0 ; i < enemiesToCheck.size() ; i++) {
                if (super.getBulletSprite().getBoundsInParent().intersects(enemiesToCheck.get(i).getSprite().getBoundsInParent())) {
                    System.out.println("intersects");
	                enemiesToCheck.get(i).takeDamage(Main.player.getDamage());
                    System.out.println(this +" despawn called to collision");
                    if (!this.alreadyDespawned) {
                    	despawn();
                    	this.alreadyDespawned = true;
                    }
                    if (enemiesToCheck.get(i).isDead()) {
                        Main.player.getCurrentRoom().getEnemies().remove(enemiesToCheck.get(i));
                        if(Main.player.getCurrentRoom().getEnemies().size() == 0){ // != 0
                            Main.player.getCurrentRoom().setCleared(true);
                        }
                    }
                }
            }
        }
    }

}
