/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bindingofisaac;

import static bindingofisaac.Constants.*;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.security.Key;

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
    
    public PlayerController(Scene scene, Player player){
        this.player = player;
        canShoot = true;
        tt = new TickTimer();
        tt.addTickAndPlay(10, Timeline.INDEFINITE, event -> {
        	if(Main.player != null)
        		updatePlayer();
        });
	    shootTimerIndex = tt.addTick(1500 / player.getAttackSpeed(), 1, event -> {
		    canShoot = true;
	    });
        scene.setOnKeyPressed(event -> {
                
            if(event.getCode() == KeyCode.W){
                upPressed = true;
            }
            if(event.getCode() == KeyCode.D){
                rightPressed = true;
            }
            if(event.getCode() == KeyCode.S){
                downPressed = true;
            }
            if(event.getCode() == KeyCode.A){
                leftPressed = true;
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
            deltaX += Main.player.getSpeed();
            player.setImageView("/img/Right_Tre.png");
        }
        if(leftPressed){
            deltaX -= Main.player.getSpeed();
            player.setImageView("/img/Left_Tre.png");
        }
		if(upPressed){
			deltaY -= Main.player.getSpeed();
			player.setImageView("/img/Back_Tre.png");
			}
		if(downPressed){
			deltaY += Main.player.getSpeed();
			player.setImageView("/img/Front_Tre.png");
        }
		
		player.setX(player.getX() + deltaX);
		player.setY(player.getY() + deltaY);
		player.getImageView().relocate(player.getX(), player.getY());

		
		for(Door thisDoor : player.getCurrentRoom().getDoors()){
			thisDoor.checkCollision();
		}
		
		Main.player.getGame().getFloor().checkStairsCollision();
		
    }
    
    public boolean isInBounds(){
        return ((player.getX() >= 80 && player.getX() <= ROOM_WIDTH - 80));
    }
	
	public TickTimer getTimer(){
		return tt;
	}

	private void shoot(int direction) {

    	final int SHOOT_DISTANCE = 1200;

    	if (direction == UP) {
			Bullet bullet = new PlayerBullet(player.getX(), player.getY(), player.getX(), player.getY() - SHOOT_DISTANCE, 1000, 10, "/img/lightning_bolt.png");
	    }
		if (direction == RIGHT) {
			Bullet bullet = new PlayerBullet(player.getX(), player.getY(), player.getX() + SHOOT_DISTANCE, player.getY(), 1000, 10, "/img/lightning_bolt.png");
		}
		if (direction == DOWN) {
			Bullet bullet = new PlayerBullet(player.getX(), player.getY(), player.getX(), player.getY() + SHOOT_DISTANCE, 1000, 10, "/img/lightning_bolt.png");
		}
		if (direction == LEFT) {
			Bullet bullet = new PlayerBullet(player.getX(), player.getY(), player.getX() - SHOOT_DISTANCE, player.getY(), 1000, 10, "/img/lightning_bolt.png");
		}
	}
}