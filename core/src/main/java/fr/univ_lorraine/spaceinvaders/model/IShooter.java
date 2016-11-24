package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by alexis on 24/11/2016.
 */
public interface IShooter {

    public void shoot(Vector2 position);

    public void update(float delta);
}
