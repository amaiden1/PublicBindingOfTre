/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */
package bindingoftre;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static bindingoftre.Constants.*;

/**
 * represents an enemy that is our game's interpretation of a witch
 * @author Austin Maiden and Nolan Ierardi
 */
public class Witch extends Enemy{
	
	private int deltaY;
	private Image bulletImg;
	private int bulletTimerIndex;
	
    /**
     * creates the witch with it's stats according to the current floor
     * @param floorLevel int representing the floor the player is on. makes the witch's difficulty
     * scale with the floor
     * @param givenRoom room that the enemy is in. Used when comparing rooms with the player to initialize it's timer
     */
    public Witch(int floorLevel, Room givenRoom){
		deltaY = 1;
		super.setCurrentRoom(givenRoom);
		ImageView sprite = new ImageView(WITCH);
		sprite.setFitHeight(70);
		sprite.setFitWidth(70);
		super.setSprite(sprite);
		super.setHealth(10 + (10 * floorLevel));
		super.setDamage(5 + (2 * (floorLevel - 1)));
		super.setSpeed(1);
		bulletImg = LIGHTNING_BOLT;
		
		super.setTimerIndex(Main.player.getGame().getController().getTimer().addTick(10 , Timeline.INDEFINITE, event -> {
			updatePos();
			checkCollision();
        }));
		bulletTimerIndex = Main.player.getGame().getController().getTimer().addTick(1000, Timeline.INDEFINITE, event -> {
			shoot();
		});
		
	}

    /**
     * updates the x and y coordinates of the witch
     */
    @Override
	public void updatePos(){
		deltaY = (super.getY() <= 100)? 1 : (deltaY = (super.getY() >= 600)? -1 : deltaY);
		super.setY(super.getY() + deltaY);
		super.getSprite().relocate(super.getX(), super.getY());
	}
	
    /**
     * shoots an instance of an enemy bullet in the direction of the player
     */
    @Override
	public void shoot() {
		double playerX = Main.player.getX() + (Main.player.getImageView().getBoundsInParent().getWidth() / 2);
		double playerY = Main.player.getY() + (Main.player.getImageView().getBoundsInParent().getHeight() / 2);
		EnemyBullet bullet = new EnemyBullet(super.getX(), super.getY(), playerX, playerY, 1000, 10, bulletImg, super.getDamage());
	}

    /**
     * getter method for the index of the timer that shoots player for reference
     * @return the index within the tick timer that controls the shooting
     */
    public int getBulletTimerIndex() {
		return bulletTimerIndex;
	}
        
    /**
     * begins all the timers for the witch
     */
    @Override
        public void start(){
            Main.player.getGame().getController().getTimer().play(super.getTimerIndex());
            Main.player.getGame().getController().getTimer().play(bulletTimerIndex);
        }
        
    /**
     * stops the timers for the witch
     */
    @Override
        public void stop(){
            Main.player.getGame().getController().getTimer().removeNull(super.getTimerIndex());
            Main.player.getGame().getController().getTimer().removeNull(bulletTimerIndex);
        }
}
