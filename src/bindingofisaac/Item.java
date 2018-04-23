package bindingofisaac;

import javafx.scene.image.ImageView;
import static bindingofisaac.Constants.*;
import javafx.animation.Timeline;

public class Item {

	private int healthDelta;
	private double speedDelta;
	private int damageDelta;
	private double attackSpeedDelta;
	private String itemName;
	private ImageView itemImg;
	private int timerIndex;

	public Item(String name, ImageView img, int health, double speed, int damage, double attackSpeed) {
		itemName = name;
		healthDelta = health;
		speedDelta = speed;
		damageDelta = damage;
		attackSpeedDelta = attackSpeed;
		itemImg = img;
        itemImg.relocate(ROOM_WIDTH/ 2, ROOM_HEIGHT / 2);
        itemImg.setFitHeight(30);
        itemImg.setFitWidth(30);
		timerIndex = Main.player.getGame().getController().getTimer().addTick(10, Timeline.INDEFINITE, event -> {
			System.out.println("item timer is running");
        	if(Main.player.getImageView().getBoundsInParent().intersects(itemImg.getBoundsInParent())){
				pickUpItem();
				System.out.println(Main.player.getStats());
			}
        });
	}
	
	public void startTimer(){
		Main.player.getGame().getController().getTimer().play(timerIndex);
	}
	
	public void stopTimer(){
		Main.player.getGame().getController().getTimer().stop(timerIndex);
	}
	
	public void pickUpItem(){
		Main.player.addHealth(healthDelta);
		Main.player.addDamage(damageDelta);
		Main.player.addSpeed(speedDelta);
		Main.player.addAttackSpeed(attackSpeedDelta);
		Main.player.getCurrentRoom().getRoomPane().getChildren().remove(itemImg);
		Main.player.getCurrentRoom().setItem(null);
		stopTimer();
	}

	/*public int getHealthDelta() {
		return healthDelta;
	}

	public double getSpeedDelta() {
		return speedDelta;
	}

	
	for int i; i < numberOfRows; i++
	for(boolean current : row i){
	if there is a queen{
		do stuff
	}
	
	
	
	public int getDamageDelta() {
		return damageDelta;
	}

	public double getAttackSpeedDelta() {
		return attackSpeedDelta;
	}*/

	public ImageView getImageView() {
		return itemImg;
	}
}
