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
	private ImageView bulletImg;
	private int direction;
	private double x;
	private double y;
	
	public Bullet(double startX, double startY, String imageSrc, int givenDirection){
		direction = givenDirection;
		bulletImg.setFitHeight(30);
		bulletImg.setFitWidth(30);
		try{
			bulletImg = new ImageView(imageSrc);
		}
		catch(Exception e){
			System.out.println("bullet image not found... using default");
			bulletImg = new ImageView("/img/Stairs.png");
		}
		x = startX;
		y = startY;
		
		bulletImg.relocate(x,y);
		timerIndex = Main.player.getGame().getController().getTimer().addTickAndPlay(5 , Timeline.INDEFINITE, new EventHandler<ActionEvent>(){
            public void handle(ActionEvent ae){
                updateBullet();
            }
        });
		
	}
	
	public void updateBullet(){
		
	}
}
