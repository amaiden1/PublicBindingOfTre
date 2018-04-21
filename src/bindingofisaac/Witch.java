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

import java.sql.Time;

import static bindingofisaac.Constants.*;

/**
 *
 * @author Austin
 */
public class Witch extends Enemy{
	
	private int deltaY;
	private String bulletImg;
	private int bulletTimerIndex;
	
	public Witch(int floorLevel, Room givenRoom){
		deltaY = 1;
		currentRoom = givenRoom;
		sprite = new ImageView("/img/Witch.png");
		sprite.setFitHeight(70);
		sprite.setFitWidth(70);
		health = 10 + (10 * floorLevel);
		damage = 5 + (2 * (floorLevel - 1));
		speed = 1;
		bulletImg = "/img/lightning_bolt.png";
		
		//System.out.println("witch location: " + x + " , " + y);
		//System.out.println("sprite location: " + sprite.getX() + " , " + sprite.getY());
		
		timerIndex = Main.player.getGame().getController().getTimer().addTick(10 , Timeline.INDEFINITE, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                updatePos();
				checkCollision();
            }
        });
		bulletTimerIndex = Main.player.getGame().getController().getTimer().addTick(1000, Timeline.INDEFINITE, new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				shoot();
			} //currently this teleports image of bullet to same spot on map. The pythagorean method does not work properly
		});
		
	}
	
	@Override
	public void updatePos(){
		//System.out.println("X: " + x + ", Y: + " + y);
		deltaY = (y <= 100)? 1 : (deltaY = (y >= 600)? -1 : deltaY);
		y += deltaY;
		sprite.relocate(x, y);
	}
	
	@Override
	public void shoot() {
		System.out.println("shooting!!!!");
		Bullet bullet = new Bullet(x, y, Main.player.getX(), Main.player.getY(), bulletImg);
	}

	public int getBulletTimerIndex() {
		return bulletTimerIndex;
	}
}
