/*
 * Copyright © 2018 Austin Maiden and Nolan Ierardi.
 * All rights reserved.
 *
 * This code is licensed for private use. Any unauthorized distribution is prohibited.
 */

package bindingofisaac;

import javafx.scene.image.ImageView;
import static bindingofisaac.Constants.*;
import javafx.animation.Timeline;

/**
 * Represents a collectible item that upgrades the stats of the player
 * when collected.
 *
 * @author Austin Maiden and Nolan Ierardi
 */
public class Item {

	private int healthDelta;
	private double speedDelta;
	private int damageDelta;
	private double attackSpeedDelta;
	private String itemName;
	private String description;
	private ImageView itemImg;
	private int timerIndex;

	/**
	 * Constructor for Item.
	 * @param name a user-facing name for this item
	 * @param img the image of the item
	 * @param desc the description for the item
	 * @param health the health delta upgrade for the item
	 * @param speed the speed delta upgrade for the item
	 * @param damage the damage delta upgrade for the item
	 * @param attackSpeed the attack speed delta upgrade for the item
	 */
	public Item(String name, ImageView img, String desc, int health, double speed, int damage, double attackSpeed) {
		itemName = name;
		healthDelta = health;
		speedDelta = speed;
		damageDelta = damage;
		attackSpeedDelta = attackSpeed;
		itemImg = img;
		description = desc;
		itemImg.relocate(ROOM_WIDTH/ 2, ROOM_HEIGHT / 2);
		itemImg.setFitHeight(50);
		itemImg.setFitWidth(50);
		timerIndex = Main.player.getGame().getController().getTimer().addTick(10, Timeline.INDEFINITE, event -> {
        	if(Main.player.getImageView().getBoundsInParent().intersects(itemImg.getBoundsInParent())){
				pickUpItem();
				System.out.println(Main.player.getStats());
			}
        });
	}

	/**
	 * Starts this item's pickup tick timer.
	 */
	public void startTimer(){
		Main.player.getGame().getController().getTimer().play(timerIndex);
	}

	/**
	 * Stops this item's pickup tick timer.
	 */
	public void stopTimer(){
		Main.player.getGame().getController().getTimer().stop(timerIndex);
	}

	private void pickUpItem(){
		Main.player.addHealth(healthDelta);
		Main.player.addDamage(damageDelta);
		Main.player.addSpeed(speedDelta);
		Main.player.addAttackSpeed(attackSpeedDelta);
		Main.player.getCurrentRoom().getRoomPane().getChildren().remove(itemImg);
		Main.player.getGame().getLg().getMiniMap().updateHud();
		Main.player.getGame().displayTemporaryPane(true, new ItemDialogScreen(this).getPane());
		stopTimer();
	}

	/**
	 * Returns a description for the item.
	 * @return description for this item
	 */
	public String getDescription(){
		return description;
	}

	/**
	 * Returns the name of this item
	 * @return name of this item
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * Returns the health delta for this item.
	 * @return health delta for item
	 */
	public int getHealthDelta() {
		return healthDelta;
	}

	/**
	 * Returns the speed delta for this item.
	 * @return speed delta for this item
	 */
	public double getSpeedDelta() {
		return speedDelta;
	}

	/**
	 * Returns the damage delta for this item.
	 * @return damage delta for this item
	 */
	public int getDamageDelta() {
		return damageDelta;
	}

	/**
	 * Returns the attack speed delta for this item.
	 * @return attack speed delta for this item
	 */
	public double getAttackSpeedDelta() {
		return attackSpeedDelta;
	}

	/**
	 * Returns the ImageView for this item.
	 * @return ImageView for this item
	 */
	public ImageView getImageView() {
		return itemImg;
	}
}
