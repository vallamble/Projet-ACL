package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 24/11/2016.
 */
public abstract class AbstractShooter implements IShooter {

    protected World world;

    protected List<ShooterBehavior> shooterBehaviors;

    public AbstractShooter(World w) {
        world = w;
        shooterBehaviors = new ArrayList<ShooterBehavior>();
    }

    public void addShooterBehavior(ShooterBehavior sb) {
        shooterBehaviors.add(sb);
    }
}
