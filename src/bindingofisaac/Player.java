package bindingofisaac;

import java.util.ArrayList;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Player {

	private Game thisGame;
	private Room currentRoom;
	private double
		x,  // current x position
		y;  // current y position
	private final ImageView
                playerImage = new ImageView("/img/Front_Tre.png");       // image for dir 0
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
                damage,
                attackSpeed,
				maxHealth;

	public Player (double startX, double startY, Stage mainStage) {
		
        playerImage.setFitHeight(70);
        playerImage.setFitWidth(70);
        x = startX;
        y = startY;
		currentImg = playerImage;
        direction = 1;
        health = 100;
		maxHealth = health;
        speed = 3;
        damage = 5;
        attackSpeed = 1;
		thisGame = new Game(mainStage, this);
		
	}
	
	public Game getGame(){
		return thisGame;
	}
	
	public void setImageView(String fileName){
		
	}
	
	public void setCurrentRoom(Room givenRoom){
		currentRoom = givenRoom;
	}
	
	public Room getCurrentRoom(){
		return currentRoom;
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

	public int getDamage() {
		return damage;
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
