package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by alexis on 24/11/2016.
 */
public class ShooterBehavior {

    private Shot shotAttributes;

    private Vector2 shiftPosition;

    public ShooterBehavior(Shot s, float x, float y) {
        shotAttributes = s;
        shiftPosition = new Vector2(x, y);
    }

    public Shot generateShotWithShift(Shot shot) {
        shot.init(shotAttributes);
        shot.getPosition().add(shiftPosition);
        return shot;
    }
}
