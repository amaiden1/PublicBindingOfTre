/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import static bindingofisaac.Constants.*;

import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Austin
 */
public class Mummy extends Enemy {

	private double xStep;
	private double yStep;
	private int surroundingCheckIndex;

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
		surroundingCheckIndex = Main.player.getGame().getController().getTimer().addTick(200, Timeline.INDEFINITE, event -> {
			checkSurroundingEnemies();
		});
	}

	@Override
	public void checkCollision(){
		if(sprite.getBoundsInParent().intersects(Main.player.getImageView().getBoundsInParent())){
			Main.player.takeDamage(damage);
		}
		if(Main.player.getCurrentRoom() == currentRoom) {
			Main.player.getGame().getController().getTimer().play(timerIndex);
		}
	}

	public void checkSurroundingEnemies() {
		ArrayList<Enemy> enemiesToCheck = Main.player.getCurrentRoom().getEnemies();
		for(int i = 0; i < enemiesToCheck.size() ; i++) {
			if(sprite.getBoundsInParent().intersects(enemiesToCheck.get(i).getSprite().getBoundsInParent())) {
				if (enemiesToCheck.get(i).getSprite() != sprite) {
					/*
					My solution for handling mummy collisions.
					The mummies end up "vibrating" and moving around rapidly.
					 */
					System.out.println("mummy intersecting an enemy");
					Random random = new Random();
					int randInt = random.nextInt(4);
					int offset = 50;
					if (randInt == 0) x += xStep * offset;
					if (randInt == 1) x -= xStep * offset;
					if (randInt == 2) y += yStep * offset;
					if (randInt == 3) y -= yStep * offset;
				}
			}
		}
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
            Main.player.getGame().getController().getTimer().play(surroundingCheckIndex);
        }
        
        @Override
        public void stop(){
            Main.player.getGame().getController().getTimer().removeNull(timerIndex);
	        Main.player.getGame().getController().getTimer().removeNull(surroundingCheckIndex);
        }
}
