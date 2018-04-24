/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import static bindingofisaac.Constants.*;

import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.image.ImageView;

/**
 *
 * @author Austin
 */
public class Mummy extends Enemy {

	private double xStep;
	private double yStep;

	public Mummy(int floorLevel, Room room) {
		currentRoom = room;
		sprite = new ImageView(MUMMY);
		sprite.setFitHeight(70);
		sprite.setFitWidth(70);
		health = 10 + (10 * floorLevel);
		damage = 20 + (5 * ((floorLevel - 1) / 2));
		speed = (int) (1 + (0.3 * floorLevel));
		xStep = speed;
		yStep = speed;

		// player position (shooting-esque) stuff


		timerIndex = Main.player.getGame().getController().getTimer().addTick(10, Timeline.INDEFINITE, event -> {
			updatePos();
			checkCollision();
		});
	}

	@Override
	public void updatePos() {
            double playerX = Main.player.getX();
            double playerY = Main.player.getY();
            if(playerX > x){
                x += xStep;
            }
            if (playerX < x){
                x -= xStep;
            }
            if (playerY > y){
                y += yStep;
            }
            if (playerY < y){
                y -= yStep;
            }
            sprite.relocate(x,y);
	}
	
        @Override
        public void start(){
            Main.player.getGame().getController().getTimer().play(timerIndex);
        }
        
        @Override
        public void stop(){
            Main.player.getGame().getController().getTimer().removeNull(timerIndex);
        }
}
