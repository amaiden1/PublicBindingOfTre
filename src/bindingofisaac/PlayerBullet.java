/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bindingofisaac;

/**
 *
 * @author Austin
 */
public class PlayerBullet extends Bullet {

	public PlayerBullet(double startX, double startY, double destinationX, double destinationY, int travelTime, int tickLength, String imageSrc){
		super(startX, startY, destinationX, destinationY, travelTime, tickLength, imageSrc);
	}

	@Override
	protected void checkCollision(){
		System.out.println("checking collision");
		if (Main.player.getCurrentRoom().getEnemies() != null && Main.player.getCurrentRoom().getEnemies().size() > 0) {
			for (Enemy enemy : Main.player.getCurrentRoom().getEnemies()) {
				System.out.println("in for loop");
				if (super.getBulletSprite().getBoundsInParent().intersects(enemy.getSprite().getBoundsInParent())) {
					System.out.println("intersects");
					enemy.takeDamage(Main.player.getDamage());
					despawn();
				}
			}
		}
	}

}
