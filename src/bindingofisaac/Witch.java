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
	
	private boolean moveUp;
	private boolean moveDown;
	
	public Witch(double givenX, double givenY, int floorLevel, Room givenRoom){
		moveUp = true;
		currentRoom = givenRoom;
		sprite = new ImageView("/img/Witch.png");
		sprite.setFitHeight(70);
		sprite.setFitWidth(70);
		health = 10 + (10 * floorLevel);
		damage = 5 + (2 * (floorLevel - 1));
		speed = 1;
		x = givenX;
		y = givenY;
		sprite.relocate(x, y);
		
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
		
		System.out.println(x + " , " + y);
		if(y <= 100){
			moveUp = false;
			moveDown = true;
		}
		if(y >= ROOM_HEIGHT){
			moveUp = true;
			moveDown = false;
		}
		if(moveUp)
			y--;
		if(moveDown)
			y++;
		sprite.setTranslateY(y);
	}
	
	
}
