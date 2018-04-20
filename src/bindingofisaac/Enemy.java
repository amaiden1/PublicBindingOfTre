package bindingofisaac;

import javafx.scene.image.ImageView;


public class Enemy {

	int timerIndex;
	ImageView sprite;
	int speed;
	int health;
	int damage;
	double x;
	double y;
	
	public Enemy(){
		
	}
	
	public void checkCollision(){
		if(sprite.getBoundsInParent().intersects(Main.player.getImageView().getBoundsInParent())){
			System.out.println("collision at: " + Main.player.getCurrentRoom());
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
			sprite = null;
		}
	}
	
	private boolean isDead(){
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

	
}
