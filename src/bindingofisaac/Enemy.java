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

	/**
	 * generic enemy for the game
	 */
	public Enemy(){

	}

        /**
	 * getter for the current room of the enemy
         * @return the room containing this enemy
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

        /**
	 * 
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public int getTimerIndex() {
		return timerIndex;
	}

	public void setTimerIndex(int timerIndex) {
		this.timerIndex = timerIndex;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public void checkCollision(){

		if(sprite.getBoundsInParent().intersects(Main.player.getImageView().getBoundsInParent())){
			Main.player.takeDamage(damage);
		}
		if(Main.player.getCurrentRoom() == currentRoom) {
			Main.player.getGame().getController().getTimer().play(timerIndex);
		}
	}
	
	public void setSprite(ImageView givenSprite){
		sprite = givenSprite;
	}
	
	public ImageView getSprite(){
		return sprite;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int givenHealth){
		health = givenHealth;
	}
	
	public void takeDamage(int damage){
		health -= damage;
		if(isDead()){
			Main.player.getCurrentRoom().getRoomPane().getChildren().remove(sprite);
			stop();
			if (Main.DEBUG) System.out.println("enemy dead");
		}
	}
	public int getTickIndex(){
		return timerIndex;
	}
	
	public boolean isDead(){
		return (health <= 0);
	}
	
	public int getDamage(){
		return damage;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	public void updatePos(){
		throw new UnsupportedOperationException("updatePos() must be overridden in the subclass.");
	}

	public void shoot() {

	}
        
	public void start(){
		throw new UnsupportedOperationException("start() must be overridden in the subclass.");
	}
        
	public void stop(){
		throw new UnsupportedOperationException("stop() must be overridden in the subclass.");
	}
}
