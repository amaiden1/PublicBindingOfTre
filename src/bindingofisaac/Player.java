package bindingofisaac;

import static bindingofisaac.Constants.ROOM_WIDTH;
import static bindingofisaac.Constants.UP;
import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Player {

	private double
			x,  // current x position
			y;  // current y position
	private final ImageView
			UP_IMG = new ImageView(new Image("/img/Player.png"));       // image for dir 0
			//RIGHT_IMG = ImageHelper.imageFromSource("playerRight.png"), // image for dir 1
			//DOWN_IMG = ImageHelper.imageFromSource("playerDown.png"),   // image for dir 2
			//LEFT_IMG = ImageHelper.imageFromSource("playerLeft.png");   // image for dir 3
	private ImageView currentImg;
	private ArrayList<Integer /* replace with proper data type */> inventory;
	private int
			/* replace with proper data type */ currentItem,
			direction,
			health,
			speed,
			attackStrength,
			attackSpeed;

	public Player(double startX,
	              double startY) {
            
            UP_IMG.setFitHeight(70);
            UP_IMG.setFitWidth(70);
            x = startX;
            y = startY;
            currentImg = UP_IMG;
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

	public ImageView getImageView() {
		return currentImg;
	}

	/*
	Item methods not implemented yet. The player uses his fists.
	*/

}
