/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

import javafx.scene.image.ImageView;

/**
 *
 * @author Austin
 */
public class Mummy extends Enemy{
	
	public Mummy(double givenX, double givenY, int floorLevel){
		sprite = new ImageView("/img/Mummy.png");
		sprite.setFitHeight(70);
		sprite.setFitWidth(70);
		health = 10 + (10 * floorLevel);
		damage = 20 + (5 * ((floorLevel - 1) / 2));
		speed = (int) (2 + (0.3 * floorLevel));
		x = givenX;
		y = givenY;
	}
	
}
