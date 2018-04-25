/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */
package bindingoftre;

import static bindingoftre.Constants.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * data representing the user's statistics, progress, and location
 * @author Austin Maiden and Nolan Ierardi
 */
public class Player {

	private Game thisGame;
	private Room currentRoom;
	private double x;
	private double y;
	private final ImageView playerImage = new ImageView(PLAYER_FRONT);
	private ImageView currentImg;
	private int direction;
	private int health;
	private int damage;
	private int maxHealth;
	private double speed;
	private double attackSpeed;
	private int ifTickIndex;
	private boolean canTakeDamage;

    /**
     * creates and initializes the player
     * @param startX the beginning location of the player on the x axis
     * @param startY the beginning location of the player on the y axis
     * @param mainStage the screen that displays everything
     */
    public Player(double startX, double startY, Stage mainStage) {
		
        playerImage.setFitHeight(70);
        playerImage.setFitWidth(70);
        x = startX;
        y = startY;
		currentImg = playerImage;
        direction = 1;
        health = 100;
		maxHealth = health;
        speed = 3;
        damage = 5;
        attackSpeed = 1;
		thisGame = new Game(mainStage, this);
		canTakeDamage = true;
		
	}

    /**
     * sets the timer for invincibility frame
     */
    public void postInit() {
		ifTickIndex = Main.player.getGame().getController().getTimer().addTick(1000, 1, event -> {
			canTakeDamage = true;
			playerImage.setOpacity(1);
		});
	}
	
    /**
     * the current game the player is in
     * @return a reference to the players game
     */
    public Game getGame(){
		return thisGame;
	}
	
    /**
     * sets the image of the player
     * @param givenImg the image of the player's sprite
     */
    public void setImageView(Image givenImg){
		currentImg.setImage(givenImg);
	}
	
    /**
     * sets the current room the player is in to the room he is walking into
     * @param givenRoom the room that the player is going to
     */
    public void setCurrentRoom(Room givenRoom){
		if(currentRoom != null)
			currentRoom.setOccupied(false);
		currentRoom = givenRoom;
		currentRoom.setOccupied(true);
		//if currentroom has enemies, then start the tickTimers
	}
	
    /**
     * getter method for the room that contains the player
     * @return a reference to the current room containing the player
     */
    public Room getCurrentRoom(){
		return currentRoom;
	}

    /**
     * getter for the player's current X position
     * @return the exact x location of the player
     */
    public double getX() {
		return x;
	}

    /**
     * setter for the player's current X position
     * @param x the new x position of the player
     */
    public void setX(double x) {
		this.x = x;
	}
    
     /**
     * getter for the player's current Y position
     * @return the exact y location of the player
     */
	public double getY() {
		return y;
	}
        
     /**
     * setter for the player's current Y position
     * @param y the new y position of the player
     */
	public void setY(double y) {
		this.y = y;
	}

    /**
     * getter method for the damage the player does
     * @return an int representing how much damage the player does
     */
    public int getDamage() {
		return damage;
	}

    /**
     * getter method for the rate at which the player fires shots
     * @return attack speed of the player
     */
    public double getAttackSpeed() {
		return attackSpeed;
	}

    /**
     * getter method for the player's current health
     * @return player's health
     */
    public int getHealth() {
		return health;
	}

    /**
     * getter method for speed
     * @return player's speed
     */
    public double getSpeed() {
		return speed;
	}

    /**
     * getter method for the direction the player is facing
     * @return int representing cardinal direction
     */
    public int getDirection() {
		return direction;
	}

    /**
     * getter for the player's imageview
     * @return the imageview of the player
     */
    public ImageView getImageView() {
		return currentImg;
	}
	
    /**
     * increases health of player
     * @param delta amount to add to the health
     */
    public void addHealth(int delta){
		maxHealth += delta;
		health += delta;
	}

    /**
     * increases damage of the player
     * @param delta the amount to increase damage by
     */
    public void addDamage(int delta) {
		damage += delta;
	}
	
    /**
     * increases the player's speed
     * @param delta the amount to increase speed by
     */
    public void addSpeed(double delta){
		speed += delta;
	}
	
    /**
     * increases the player's attack speed
     * @param delta the amount to increment speed by
     */
    public void addAttackSpeed(double delta){
		attackSpeed += delta;
	}

    /**
     * reduces players health
     * @param delta amount to reduce health by
     */
    public void takeDamage(int delta){
		if (canTakeDamage) {
			health -= delta;
			if (Main.DEBUG) System.out.println("took damage: health is now: " + health);
			if (health <= 0) {
				// player is dead. GAME OVER
				if (Main.DEBUG) System.out.println("PLAYER IS DEAD");
				Main.player.getGame().gameOver();
			}
			canTakeDamage = false;
			Main.player.getGame().getLg().getMiniMap().updateHud();
			playerImage.setOpacity(0.5);
			Main.player.getGame().getController().getTimer().play(ifTickIndex);
		}
	}
	
    /**
     * fills health to the max
     */
    public void refillHealth(){
		health = maxHealth;
	}
	
    /**
     * getter for the player's statistics in a string
     * @return string representing the stats of the player
     */
    public String getStats(){
		return ("Player max health: " + maxHealth +
				"\nPlayer current health: " + health +
				"\nPlayer damage: " + damage +
				"\nPlayer speed: " + speed +
				"\nPlayer attackSpeed: " + attackSpeed);
	}

}
