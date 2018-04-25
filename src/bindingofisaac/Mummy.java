/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */
package bindingofisaac;

import static bindingofisaac.Constants.*;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 * represents an enemy that is our game's interpretation of a Mummy
 * @author Austin Maiden and Nolan Ierardi
 */
public class Mummy extends Enemy {

	private double xStep;
	private double yStep;
	private int surroundingCheckIndex;

     /**
     * creates the Mummy with it's stats according to the current floor
     * @param floorLevel int representing the floor the player is on. makes the Mummy's difficulty
     * scale with the floor
     * @param givenRoom room that the enemy is in. Used when comparing rooms with the player to initialize it's timer
     */
	public Mummy(int floorLevel, Room room) {
		super.setCurrentRoom(room);
		ImageView sprite = new ImageView(MUMMY);
		sprite.setFitHeight(70);
		sprite.setFitWidth(70);
		super.setSprite(sprite);
                super.setHealth(10 + (10 * floorLevel));
		super.setDamage(10 + (2 * floorLevel));
		super.setSpeed(1 + (0.05 * floorLevel));
		xStep = super.getSpeed();
		yStep = super.getSpeed();

		// player position (shooting-esque) stuff

		super.setTimerIndex(Main.player.getGame().getController().getTimer().addTick(10, Timeline.INDEFINITE, event -> {
			updatePos();
			checkCollision();
		}));
		surroundingCheckIndex = Main.player.getGame().getController().getTimer().addTick(800, Timeline.INDEFINITE, event -> {
			checkSurroundingEnemies();
		});
	}

    /**
     * makes the player take damage upon contact with the Mummy
     */
    @Override
	public void checkCollision(){
		if(super.getSprite().getBoundsInParent().intersects(Main.player.getImageView().getBoundsInParent())){
			Main.player.takeDamage(super.getDamage());
		}
		if(Main.player.getCurrentRoom() == super.getCurrentRoom()) {
			Main.player.getGame().getController().getTimer().play(super.getTimerIndex());
		}
	}

    /**
     * prevents mummies from overlapping each other
     */
    public void checkSurroundingEnemies() {
		ArrayList<Enemy> enemiesToCheck = Main.player.getCurrentRoom().getEnemies();
		for(int i = 0; i < enemiesToCheck.size() ; i++) {
			if(super.getSprite().getBoundsInParent().intersects(enemiesToCheck.get(i).getSprite().getBoundsInParent())) {
				if (enemiesToCheck.get(i).getSprite() != super.getSprite()) {
					/*
					My solution for handling mummy collisions.
					The mummies end up "vibrating" and moving around rapidly.
					 */
					Random random = new Random();
					int randInt = random.nextInt(4);
					int offset = 50;
					if (randInt == 0) super.setX(super.getX() + (xStep * offset));
					if (randInt == 1) super.setX(super.getX() - (xStep * offset));
					if (randInt == 2) super.setY(super.getY() + (yStep * offset));
					if (randInt == 3) super.setY(super.getY() + (yStep * offset));
				}
			}
		}
	}

     /**
     * updates the x and y coordinates of the witch
     */
	@Override
	public void updatePos() {
		double playerX = Main.player.getX();
		double playerY = Main.player.getY();
		if(playerX > super.getX()){
			super.setX(super.getX() + xStep);
		}
		if (playerX < super.getX()){
			super.setX(super.getX() - xStep);
		}
		if (playerY > super.getY()){
			super.setY(super.getY() + yStep);
		}
		if (playerY < super.getY()){
			super.setY(super.getY() - yStep);
		}
		super.getSprite().relocate(super.getX(),super.getY());
	}
	
        /**
        * begins all the timers for the witch
        */
        @Override
        public void start(){
            Main.player.getGame().getController().getTimer().play(super.getTimerIndex());
            Main.player.getGame().getController().getTimer().play(surroundingCheckIndex);
        }
        
        /**
        * begins all the timers for the witch
        */
        @Override
        public void stop(){
            Main.player.getGame().getController().getTimer().removeNull(super.getTimerIndex());
	        Main.player.getGame().getController().getTimer().removeNull(surroundingCheckIndex);
        }
}
