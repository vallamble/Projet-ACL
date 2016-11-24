package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by alexis on 24/11/2016.
 */
public abstract class SimpleShooterWithCoolDown extends AbstractShooter {

    protected float cooldown;

    protected float timeStillInCooldown;

    public SimpleShooterWithCoolDown(World w, float cd) {
        super(w);
        cooldown = cd;
    }

    @Override
    public void update(float delta) {
        if (timeStillInCooldown > 0)
            timeStillInCooldown =- delta;
    }

    protected boolean isInCooldown() {
        return timeStillInCooldown > 0;
    }
}
