package bindingofisaac;

import static bindingofisaac.Constants.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class Player {

	private Game thisGame;
	private Room currentRoom;
	private double x;
	private double y;
	private final ImageView playerImage = new ImageView(PLAYER_FRONT);
	private ImageView currentImg;
	private int direction;
	private int health;
	private int damage;
	private int maxHealth;
	private double speed;
	private double attackSpeed;
	private int ifTickIndex;
	private boolean canTakeDamage;

	public Player(double startX, double startY, Stage mainStage) {
		
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
		canTakeDamage = true;
		
	}

	public void postInit() {
		ifTickIndex = Main.player.getGame().getController().getTimer().addTick(1000, 1, event -> {
			System.out.println("iframe timeline finished, can now take damage");
			canTakeDamage = true;
			playerImage.setOpacity(1);
		});
	}
	
	public Game getGame(){
		return thisGame;
	}
	
	public void setImageView(Image givenImg){
		currentImg.setImage(givenImg);
	}
	
	public void setCurrentRoom(Room givenRoom){
		if(currentRoom != null)
			currentRoom.setOccupied(false);
		currentRoom = givenRoom;
		currentRoom.setOccupied(true);
		//if currentroom has enemies, then start the tickTimers
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

	public double getAttackSpeed() {
		return attackSpeed;
	}

	public int getHealth() {
		return health;
	}

	public double getSpeed() {
		return speed;
	}

	public int getDirection() {
		return direction;
	}

	public ImageView getImageView() {
		return currentImg;
	}
	
	public void addHealth(int delta){
		maxHealth += delta;
		health += delta;
	}

	public void addDamage(int delta) {
		damage += delta;
	}
	
	public void addSpeed(double delta){
		speed += delta;
	}
	
	public void addAttackSpeed(double delta){
		attackSpeed += delta;
	}

	public void takeDamage(int delta){
		if (canTakeDamage) {
			health -= delta;
			System.out.println("took damage: health is now: " + health);
			if (health <= 0) {
				// player is dead. GAME OVER
				System.out.println("PLAYER IS DEAD");
				Main.player.getGame().gameOver();
			}
			canTakeDamage = false;
			Main.player.getGame().getLg().getMiniMap().updateHud();
			playerImage.setOpacity(0.5);
			Main.player.getGame().getController().getTimer().play(ifTickIndex);
		}
	}
	
	public void refillHealth(){
		health = maxHealth;
	}
	
	public String getStats(){
		return ("Player max health: " + maxHealth +
				"\nPlayer current health: " + health +
				"\nPlayer damage: " + damage +
				"\nPlayer speed: " + speed +
				"\nPlayer attackSpeed: " + attackSpeed);
	}

}
