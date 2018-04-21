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
public class Bullet {
	
	private int timerIndex;
	private ImageView bulletSprite;
	private int direction;
	private double startX;
	private double startY;
	private double endX;
	private double endY;

	public Bullet(double startX, double startY, double destinationX, double destinationY, String imageSrc){
		bulletSprite = new ImageView(imageSrc);
		bulletSprite.setFitHeight(30);
		bulletSprite.setFitWidth(30);
		try{
			bulletSprite = new ImageView(imageSrc);
		}
		catch(Exception e){
			System.out.println("bullet image not found... using default");
			bulletSprite = new ImageView("/img/Stairs.png");
		}
		this.startX = startX;
		this.startY = startY;
		this.endX = Main.player.getX();
		this.endY = Main.player.getY();

		Main.player.getCurrentRoom().getRoomPane().getChildren().add(bulletSprite);
		bulletSprite.relocate(startX,startY);
		bulletSprite.setRotate(90);

		timerIndex = Main.player.getGame().getController().getTimer().addTickAndPlay(5, Timeline.INDEFINITE, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                updateBullet();
            }
        });
		
	}
	
	public void updateBullet(){

		startX += 1;
		startY += 1;
		double destX = Math.sqrt((startX * startX) + (endX * endX));
		double destY = Math.sqrt((startY * startY) + (endY * endY));

		bulletSprite.relocate(destX, destY);
	}
}
