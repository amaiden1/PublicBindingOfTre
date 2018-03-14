package bindingofisaac;

import javafx.scene.image.ImageView;
import java.util.ArrayList;

public class Player {

	private double
			x,  // current x position
			y;  // current y position
	private final ImageView
			UP_IMG = ImageHelper.imageFromSource("playerUp.png"),       // image for dir 0
			RIGHT_IMG = ImageHelper.imageFromSource("playerRight.png"), // image for dir 1
			DOWN_IMG = ImageHelper.imageFromSource("playerDown.png"),   // image for dir 2
			LEFT_IMG = ImageHelper.imageFromSource("playerLeft.png");   // image for dir 3
	private ImageView currentImg;
	private ArrayList<Integer /* replace with proper data type */> inventory;
	private int
			/* replace with proper data type */ currentItem,
			direction,
			health,
			speed,
			attackStrength,
			attackSpeed;

	public Player(int startX,
	              int startY) {
		x = startX;
		y = startY;
		currentImg = RIGHT_IMG;
		direction = 1;
		health = 100;
		speed = 1;
		attackStrength = 1;
		attackSpeed = 1;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public int getAttackStrength() {
		return attackStrength;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public int getHealth() {
		return health;
	}

	public int getSpeed() {
		return speed;
	}

	public int getDirection() {
		return direction;
	}

	public ImageView getImg() {
		return currentImg;
	}

	/*
	Item methods not implemented yet. The player uses his fists.
	*/

}
