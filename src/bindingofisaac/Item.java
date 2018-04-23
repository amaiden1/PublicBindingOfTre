package bindingofisaac;

import javafx.scene.image.ImageView;

public class Item {

	private int healthDelta;
	private double speedDelta;
	private int damageDelta;
	private double attackSpeedDelta;
	private String itemName;
	private ImageView itemImg;

	public Item(String name, ImageView img, int health, double speed, int damage, double attackSpeed) {
		itemName = name;
		healthDelta = health;
		speedDelta = speed;
		damageDelta = damage;
		attackSpeedDelta = attackSpeed;
		itemImg = img;
	}

	public int getHealthDelta() {
		return healthDelta;
	}

	public double getSpeedDelta() {
		return speedDelta;
	}

	public int getDamageDelta() {
		return damageDelta;
	}

	public double getAttackSpeedDelta() {
		return attackSpeedDelta;
	}

	public ImageView getImageView() {
		return itemImg;
	}
}
