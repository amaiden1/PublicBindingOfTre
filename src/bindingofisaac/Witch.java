/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import static bindingofisaac.Constants.*;

/**
 *
 * @author Austin
 */
public class Witch extends Enemy{
	
	private int deltaY;
	
	public Witch(int floorLevel, Room givenRoom){
		deltaY = 1;
		currentRoom = givenRoom;
		sprite = new ImageView("/img/Witch.png");
		sprite.setFitHeight(70);
		sprite.setFitWidth(70);
		health = 10 + (10 * floorLevel);
		damage = 5 + (2 * (floorLevel - 1));
		speed = 1;
		
		System.out.println("witch location: " + x + " , " + y);
		System.out.println("sprite location: " + sprite.getX() + " , " + sprite.getY());
		
		timerIndex = Main.player.getGame().getController().getTimer().addTick(5 , Timeline.INDEFINITE, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                updatePos();
				checkCollision();
            }
        });
		
	}
	
	@Override
	public void updatePos(){
		deltaY += (y <= 100)? 1 : (deltaY = (y >= 600)? -1 : deltaY);
		y += deltaY;
		sprite.relocate(x, y);
	}
	
	
}
