package bindingofisaac;

import javafx.scene.image.ImageView;

public class Enemy {

	protected Room currentRoom;
	protected int timerIndex;
	protected ImageView sprite;
	protected int speed;
	protected int health;
	protected int damage;
	protected double x;
	protected double y;
	
	public Enemy(){

	}
	
	public void checkCollision(){

		if(sprite.getBoundsInParent().intersects(Main.player.getImageView().getBoundsInParent())){
			Main.player.takeDamage(damage);
		}
		if(Main.player.getCurrentRoom() == currentRoom) {
			Main.player.getGame().getController().getTimer().play(timerIndex);
		}
	}
	
	public void setSprite(ImageView givenSprite){
		sprite = givenSprite;
	}
	
	public ImageView getSprite(){
		return sprite;
	}
	
	public int getHealth(){
		return health;
	}
	
	public void setHealth(int givenHealth){
		health = givenHealth;
	}
	
	public void takeDamage(int damage){
		health -= damage;
		if(isDead()){
			Main.player.getCurrentRoom().getRoomPane().getChildren().remove(sprite);
			stop();
			System.out.println("dead");
		}
	}
	public int getTickIndex(){
		return timerIndex;
	}
	
	public boolean isDead(){
		return (health <= 0);
	}
	
	public int getDamage(){
		return damage;
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
	
	public void updatePos(){
		System.out.println("you need to override the updatePos method in Enemy you fool!");
	}

	public void shoot() {
		//override me!
	}
        
	public void start(){
		//override me!
	}
        
	public void stop(){
		//override me!
	}
}
