/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bindingofisaac;

import static bindingofisaac.Constants.ROOM_WIDTH;
import static bindingofisaac.Constants.UP;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import static javafx.scene.input.KeyCode.DOWN;
import static javafx.scene.input.KeyCode.LEFT;
import static javafx.scene.input.KeyCode.RIGHT;
import static javafx.scene.input.KeyCode.UP;
import javafx.scene.input.KeyEvent;

/**
 *
 * @author amaiden1
 */
public class PlayerController {
    
    private Player player;
    private boolean upPressed,
                    rightPressed,
                    downPressed,
                    leftPressed;
    
    public PlayerController(Scene scene, Player player){
        this.player = player;
        TickTimer tt = new TickTimer();
        tt.addTickAndPlay(10, Timeline.INDEFINITE, new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent ae){
                updatePlayer();
            }
        });
        scene.setOnKeyPressed(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
                
            if(event.getCode() == KeyCode.UP){
                upPressed = true;
            }
            if(event.getCode() == KeyCode.RIGHT){
                rightPressed = true;
            }
            if(event.getCode() == KeyCode.DOWN){
                downPressed = true;
            }
            if(event.getCode() == KeyCode.LEFT){
                leftPressed = true;
            }
        }
        });
        
        scene.setOnKeyReleased(new EventHandler<KeyEvent>(){
        @Override
        public void handle(KeyEvent event){
            
            if(event.getCode() == KeyCode.UP){
                upPressed = false;
            }
            if(event.getCode() == KeyCode.RIGHT){
                rightPressed = false;
            }
            if(event.getCode() == KeyCode.DOWN){
                downPressed = false;
            }
            if(event.getCode() == KeyCode.LEFT){
                leftPressed = false;
            }
        }
        });
    }
    
    public void updatePlayer(){
        int deltaX = 0;
        int deltaY = 0;
        if(upPressed){
            deltaY -= 3;
        }
        if(rightPressed){
            deltaX += 3;
        }
        if(downPressed){
            deltaY += 3;
        }
        if(leftPressed){
            deltaX -= 3;
        }
        player.setX(player.getX() + deltaX);
        player.setY(player.getY() + deltaY);
        player.getImageView().relocate(player.getX(), player.getY());
    }
}