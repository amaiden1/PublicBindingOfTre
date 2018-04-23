package bindingofisaac;

import javafx.scene.image.ImageView;



public class Enemy {

	Room currentRoom;
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
		if(Main.player.getCurrentRoom() == currentRoom)
			Main.player.getGame().getController().getTimer().play(timerIndex);
		
		//this is where I left off. collision checker works, but checks every enemy on the floor at the same time. Might want to only trigger the room I'm in
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
			Main.player.getCurrentRoom().removeEnemy(this);
                        stop();
		}
	}
	public int getTickIndex(){
		return timerIndex;
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
	
	public void updatePos(){
		System.out.println("you need to override the updatePos method in Enemy you fool!");
	}

	public void shoot() {
		// override me!
	}
        
        public void start(){
            //override me!
        }
        
        public void stop(){
            //override me!
        }
}
