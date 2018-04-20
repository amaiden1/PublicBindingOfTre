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

/**
 *
 * @author Austin
 */
public class Witch extends Enemy{
	
	public Witch(double givenX, double givenY, int floorLevel){
		sprite = new ImageView("/img/Witch.png");
		sprite.setFitHeight(70);
		sprite.setFitWidth(70);
		health = 10 + (10 * floorLevel);
		damage = 5 + floorLevel;
		speed = 1;
		x = givenX;
		y = givenY;
		sprite.relocate(x, y);
		
		timerIndex = Main.player.getGame().getController().getTimer().addTickAndPlay(5 , Timeline.INDEFINITE, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                //updatePos();
				checkCollision();
            }
        });
		
	}
	
	
}
