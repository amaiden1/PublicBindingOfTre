package bindingofisaac;

import javafx.scene.image.ImageView;

public class Item {

	private int healthDelta;
	private int speedDelta;
	private int damageDelta;
	private int attackSpeedDelta;
	private String itemName;
	private ImageView itemImg;

	public Item(ImageView img, int health, int speed, int damage, int attackSpeed, String name) {

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

	public int getSpeedDelta() {
		return speedDelta;
	}

	public int getDamageDelta() {
		return damageDelta;
	}

	public int getAttackSpeedDelta() {
		return attackSpeedDelta;
	}

	public ImageView getImageView() {
		return itemImg;
	}
}
