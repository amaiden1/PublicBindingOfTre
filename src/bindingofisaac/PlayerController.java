/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingofisaac;

import static bindingofisaac.Constants.*;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;

/**
 * controller class for the player
 * @author Austin Maiden and Nolan Ierardi
 */
public class PlayerController {
    
    private Player player;
	private TickTimer tt;
	
    private boolean upPressed;
	private boolean rightPressed;
	private boolean downPressed;
	private boolean leftPressed;
	private boolean canShoot;
	private int shootTimerIndex;
	private Image bulletImg;
    
    /**
     * creates the controller and key-logger of the player
     * @param scene the scene that the player is on
     * @param player the player the controller is controlling
     */
    public PlayerController(Scene scene, Player player){
        this.player = player;
        bulletImg = LIGHTNING_BOLT;
        canShoot = true;
        tt = new TickTimer();
        tt.addTickAndPlay(10, Timeline.INDEFINITE, event -> {
        	if(Main.player != null)
        		updatePlayer();
        });
	    shootTimerIndex = tt.addTick((int)(1000 / player.getAttackSpeed()), 1, event -> {
		    canShoot = true;
	    });
        scene.setOnKeyPressed(event -> {
                
            if(event.getCode() == KeyCode.W){
                player.setImageView(PLAYER_BACK);
                upPressed = true;
            }
            if(event.getCode() == KeyCode.D){
                player.setImageView(PLAYER_RIGHT);
                rightPressed = true;
            }
            if(event.getCode() == KeyCode.A){
                player.setImageView(PLAYER_LEFT);
                leftPressed = true;
            }
            if(event.getCode() == KeyCode.S){
                player.setImageView(PLAYER_FRONT);
                downPressed = true;
            }

            if (event.getCode() == KeyCode.UP) {
            	if (canShoot) {
		            shoot(UP);
		            canShoot = false;
		            tt.play(shootTimerIndex);
	        }
            }
	        if (event.getCode() == KeyCode.RIGHT) {
            	if (canShoot) {
		            shoot(RIGHT);
		            canShoot = false;
		            tt.play(shootTimerIndex);
	            }
	        }
	        if (event.getCode() == KeyCode.DOWN) {
            	if (canShoot) {
		            shoot(DOWN);
		            canShoot = false;
		            tt.play(shootTimerIndex);
	            }
	        }
	        if (event.getCode() == KeyCode.LEFT) {
            	if (canShoot) {
		            shoot(LEFT);
		            canShoot = false;
		            tt.play(shootTimerIndex);
	            }
	        }
	        /*if(event.getCode() == KeyCode.U) {
                for(int i = Main.player.getCurrentRoom().getEnemies().size() - 1; i >= 0; i++){
					Main.player.getCurrentRoom().getEnemies().get(i).takeDamage(300000);
					Main.player.getCurrentRoom().getEnemies().remove(i);
				}
				Main.player.getCurrentRoom().setCleared(true);
	        }*/ //only for developers
        });
        
        scene.setOnKeyReleased(event -> {
            
            if(event.getCode() == KeyCode.W){
                upPressed = false;
            }
            if(event.getCode() == KeyCode.D){
                rightPressed = false;
            }
            if(event.getCode() == KeyCode.S){
                downPressed = false;
            }
            if(event.getCode() == KeyCode.A){
                leftPressed = false;
            }
        });

    }
    
    /**
     * updates the location of the player
     */
    public void updatePlayer() {
        double deltaX = 0;
        double deltaY = 0;
        
        if(rightPressed){
        	double proposedDeltaX = deltaX + Main.player.getSpeed();
        	if (checkBounds(player.getX() + proposedDeltaX, player.getY())) {
		        deltaX += Main.player.getSpeed();
	        }
        }
        if(leftPressed){
        	double proposedDeltaX = deltaX - Main.player.getSpeed();
        	if (checkBounds(player.getX() + proposedDeltaX, player.getY())) {
		        deltaX -= Main.player.getSpeed();
	        }
        }
		if(upPressed){
        	double proposedDeltaY = deltaY - Main.player.getSpeed();
        	if (checkBounds(player.getX(), player.getY() + proposedDeltaY)) {
		        deltaY -= Main.player.getSpeed();
	        }
        }
		if(downPressed){
        	double proposedDeltaY = deltaY + Main.player.getSpeed();
        	if (checkBounds(player.getX(), player.getY() + proposedDeltaY)) {
		        deltaY += Main.player.getSpeed();
	        }
        }
		
		player.setX(player.getX() + deltaX);
		player.setY(player.getY() + deltaY);
		player.getImageView().relocate(player.getX(), player.getY());

		
		for(Door thisDoor : player.getCurrentRoom().getDoors()){
			thisDoor.checkCollision();
		}
		
		Main.player.getGame().getFloor().checkStairsCollision();
		
    }
    
    /**
     * checks the bounds of the player to make sure they are not off the map
     * @param x the x position of the player
     * @param y the y position of the player
     * @return boolean representing whether or not the player is in the bounds of the room
     */
    public boolean checkBounds(double x, double y){
    	return !((x <= 60 || x >= ROOM_WIDTH - 120 || y <= 70 || y >= ROOM_HEIGHT - 130));
    }
	
    /**
     * getter for the tick timer
     * @return the TickTimer that contains all the timelines
     */
    public TickTimer getTimer(){
		return tt;
	}

	private void shoot(int direction) {

    	final int SHOOT_DISTANCE = 1200;

    	if (direction == UP) {
			Bullet bullet = new PlayerBullet(player.getX(), player.getY(), player.getX(), player.getY() - SHOOT_DISTANCE, 1000, 10, bulletImg);
	    }
		if (direction == RIGHT) {
			Bullet bullet = new PlayerBullet(player.getX(), player.getY(), player.getX() + SHOOT_DISTANCE, player.getY(), 1000, 10, bulletImg);
		}
		if (direction == DOWN) {
			Bullet bullet = new PlayerBullet(player.getX(), player.getY(), player.getX(), player.getY() + SHOOT_DISTANCE, 1000, 10, bulletImg);
		}
		if (direction == LEFT) {
			Bullet bullet = new PlayerBullet(player.getX(), player.getY(), player.getX() - SHOOT_DISTANCE, player.getY(), 1000, 10, bulletImg);
		}
	}
}