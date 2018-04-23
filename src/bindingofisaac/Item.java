package bindingofisaac;

import javafx.scene.image.ImageView;
import static bindingofisaac.Constants.*;

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
                itemImg.relocate(ROOM_WIDTH/ 2, ROOM_HEIGHT / 2);
                itemImg.setFitHeight(30);
                itemImg.setFitWidth(30);
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
