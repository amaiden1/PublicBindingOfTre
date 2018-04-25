/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingofisaac;

import javafx.scene.image.ImageView;

/**
 * Represents an enemy. Enemies are capable of causing damage to the
 * player, and taking damage from the player.
 *
 * @author Austin Maiden and Nolan Ierardi
 */
public class Enemy {

	private Room currentRoom;
	private int timerIndex;
	private ImageView sprite;
	private double speed;
	private int health;
	private int damage;
	private double x;
	private double y;


	public Enemy(){

	}

        /**
	 * getter for the current room of the enemy
         * @return the room containing this enemy
	 * Returns the current room.
	 * @return current room
>>>>>>> origin/master
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * Sets the current room.
	 * @param currentRoom room to set
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	/**
	 * Returns the timer index for ths enemy.
	 * @return enemy timer index
	 */
	public int getTimerIndex() {
		return timerIndex;
	}

	/**
	 * Sets the timer index for this enemy.
	 * @param timerIndex index to set
	 */
	public void setTimerIndex(int timerIndex) {
		this.timerIndex = timerIndex;
	}

	/**
	 * Returns the speed for this enemy.
	 * @return enemy speed
	 */
	public double getSpeed() {
		return speed;
	}

	/**
	 * Sets the speed for this enemy.
	 * @param speed new enemy speed
	 */
	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Sets the damage this enemy desals.
	 * @param damage new enemy damage
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}

	/**
	 * Checks for collisions with player.
	 */
	public void checkCollision(){

		if(sprite.getBoundsInParent().intersects(Main.player.getImageView().getBoundsInParent())){
			Main.player.takeDamage(damage);
		}
		if(Main.player.getCurrentRoom() == currentRoom) {
			Main.player.getGame().getController().getTimer().play(timerIndex);
		}
	}

	/**
	 * Sets the sprite of this enemy.
	 * @param givenSprite new enemy sprite
	 */
	public void setSprite(ImageView givenSprite){
		sprite = givenSprite;
	}

	/**
	 * Returns the sprite of this enemy.
	 * @return enemy sprite
	 */
	public ImageView getSprite(){
		return sprite;
	}

	/**
	 * Returns the health of this enemy.
	 * @return current enemy health
	 */
	public int getHealth(){
		return health;
	}

	/**
	 * Sets the health of this enemy.
	 * @param givenHealth new health
	 */
	public void setHealth(int givenHealth){
		health = givenHealth;
	}

	/**
	 * Damages the enemy as a result of player attack.
	 * @param damage the amount of damage to take
	 */
	public void takeDamage(int damage){
		health -= damage;
		if(isDead()){
			Main.player.getCurrentRoom().getRoomPane().getChildren().remove(sprite);
			stop();
			if (Main.DEBUG) System.out.println("enemy dead");
		}
	}

	/**
	 * Returns if the enemy is dead.
	 * @return if enemy is dead
	 */
	public boolean isDead(){
		return (health <= 0);
	}

	/**
	 * Returns the damage of the enemy.
	 * @return enemy damage
	 */
	public int getDamage(){
		return damage;
	}

	/**
	 * Returns the x position of the enemy.
	 * @return enemy x position
	 */
	public double getX() {
		return x;
	}

	/**
	 * Sets the x position of the enemy.
	 * @param x new x position
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Returns the y position of the enemy.
	 * @return enemy y position
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets the y position of the enemy.
	 * @param y new y position
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Updates the position of this enemy.
	 * Intended to be overridden by subclass.
	 */
	public void updatePos(){
		throw new UnsupportedOperationException("updatePos() must be overridden in the subclass.");
	}

	/**
	 * Updates the position of this enemy.
	 * If not overridden, simply does nothing (as not all enemies shoot).
	 */
	public void shoot() {

	}

	/**
	 * Starts all enemy operations.
	 * Intended to be overridden by subclass.
	 */
	public void start(){
		throw new UnsupportedOperationException("start() must be overridden in the subclass.");
	}

	/**
	 * Stops all enemy operations.
	 * Intended to be overridden by subclass.
	 */
	public void stop(){
		throw new UnsupportedOperationException("stop() must be overridden in the subclass.");
	}
}
