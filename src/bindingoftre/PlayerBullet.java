/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */
package bindingoftre;

import javafx.scene.image.Image;

import java.util.ArrayList;

/**
 * bullet that is used only by the player
 * @author Austin Maiden and Nolan Ierardi
 */
public class PlayerBullet extends Bullet {

	private boolean alreadyDespawned;

    /**
     * creates the players bullet
     * @param startX starting location on x axis of the bullet
     * @param startY starting location on y axis of the bullet
     * @param destinationX ending location on x axis of the bullet
     * @param destinationY ending location on x axis of the bullet
     * @param travelTime how long the bullet will travel for
     * @param tickLength how often the bullet updates
     * @param imageSrc the image of the bullet
     */
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
                    if (Main.DEBUG) System.out.println("intersects");
	                enemiesToCheck.get(i).takeDamage(Main.player.getDamage());
                    if (Main.DEBUG) System.out.println(this +" despawn called to collision");
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
