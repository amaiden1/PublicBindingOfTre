/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bindingofisaac;

import static bindingofisaac.Constants.*;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author amaiden1
 */
public class PlayerController {
    
    private Player player;
	private TickTimer tt;
	
    private boolean upPressed,
                    rightPressed,
                    downPressed,
                    leftPressed;
    
    public PlayerController(Scene scene, Player player){
        this.player = player;
        tt = new TickTimer();
        tt.addTickAndPlay(10, Timeline.INDEFINITE, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                updatePlayer();
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
                
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
        }
        });
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
            
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
        }
        });
    }
    
    public void updatePlayer() {
        int deltaX = 0;
        int deltaY = 0;
        
        if(rightPressed){
            deltaX += 3;
            player.setImageView("/img/Right_Tre.png");
        }
        if(leftPressed){
            deltaX -= 3;
            player.setImageView("/img/Left_Tre.png");
        }
		if(upPressed){
			deltaY -= 3;
			player.setImageView("/img/Back_Tre.png");
			}
		if(downPressed){
			deltaY += 3;
			player.setImageView("/img/Front_Tre.png");
        }
		
		player.setX(player.getX() + deltaX);
		player.setY(player.getY() + deltaY);
		player.getImageView().setTranslateX(player.getX());
		player.getImageView().setTranslateY(player.getY());
		
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
}