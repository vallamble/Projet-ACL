package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by alexis on 24/11/2016.
 */
public class PlayerShooterWithCooldown extends SimpleShooterWithCoolDown {

    public PlayerShooterWithCooldown(World w, float cd) {
        super(w, cd);
    }

    @Override
    public void shoot(Vector2 position) {
        if (!isInCooldown()) {
            timeSinceLastShot = 0f;
            for (ShooterBehavior shooterBehavior : shooterBehaviors) {
                Shot shot = world.obtainShotFromPool();
                shot = shooterBehavior.generateShotWithShift(shot);
                shot.getPosition().add(position);
                shot.updateBoundingBox();
                world.getPlayerShots().add(shot);
            }
        }
    }
}
