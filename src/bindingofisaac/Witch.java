/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static bindingofisaac.Constants.*;

/**
 *
 * @author Austin
 */
public class Witch extends Enemy{
	
	private int deltaY;
	private Image bulletImg;
	private int bulletTimerIndex;
	
	public Witch(int floorLevel, Room givenRoom){
		deltaY = 1;
		super.setCurrentRoom(givenRoom);
		ImageView sprite = new ImageView(WITCH);
		sprite.setFitHeight(70);
		sprite.setFitWidth(70);
		super.setSprite(sprite);
		super.setHealth(10 + (10 * floorLevel));
		super.setDamage(5 + (2 * (floorLevel - 1)));
		super.setSpeed(1);
		bulletImg = LIGHTNING_BOLT;
		
		super.setTimerIndex(Main.player.getGame().getController().getTimer().addTick(10 , Timeline.INDEFINITE, event -> {
			updatePos();
			checkCollision();
        }));
		bulletTimerIndex = Main.player.getGame().getController().getTimer().addTick(1000, Timeline.INDEFINITE, event -> {
			shoot();
		});
		
	}

	@Override
	public void updatePos(){
		deltaY = (super.getY() <= 100)? 1 : (deltaY = (super.getY() >= 600)? -1 : deltaY);
		super.setY(super.getY() + deltaY);
		super.getSprite().relocate(super.getX(), super.getY());
	}
	
	@Override
	public void shoot() {
		double playerX = Main.player.getX() + (Main.player.getImageView().getBoundsInParent().getWidth() / 2);
		double playerY = Main.player.getY() + (Main.player.getImageView().getBoundsInParent().getHeight() / 2);
		EnemyBullet bullet = new EnemyBullet(super.getX(), super.getY(), playerX, playerY, 1000, 10, bulletImg, super.getDamage());
	}

	public int getBulletTimerIndex() {
		return bulletTimerIndex;
	}
        
        @Override
        public void start(){
            Main.player.getGame().getController().getTimer().play(super.getTimerIndex());
            Main.player.getGame().getController().getTimer().play(bulletTimerIndex);
        }
        
        @Override
        public void stop(){
            Main.player.getGame().getController().getTimer().removeNull(super.getTimerIndex());
            Main.player.getGame().getController().getTimer().removeNull(bulletTimerIndex);
        }
}
