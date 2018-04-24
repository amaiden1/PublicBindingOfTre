/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bindingofisaac;

import static bindingofisaac.Constants.*;

import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 *
 * @author amaiden1
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
    
    public PlayerController(Scene scene, Player player){
        this.player = player;
        bulletImg = LIGHTNING_BOLT;
        canShoot = true;
        tt = new TickTimer();
        tt.addTickAndPlay(10, Timeline.INDEFINITE, event -> {
        	if(Main.player != null)
        		updatePlayer();
        });
	    shootTimerIndex = tt.addTick((int)(1500 / player.getAttackSpeed()), 1, event -> {
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
	        if(event.getCode() == KeyCode.U) {
                Main.player.getCurrentRoom().setCleared(false);
            	Main.player.getCurrentRoom().setCleared(true);
	        }
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
    
    public boolean checkBounds(double x, double y){
    	if ((x <= 60 || x >= ROOM_WIDTH - 120 || y <= 70 || y >= ROOM_HEIGHT - 130)) {
    		return false;
	    }
        else return true;
    }
	
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