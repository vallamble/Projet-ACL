package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.utils.Pool;

/**
 * Created by alexis on 24/11/2016.
 */
public class Shot extends GameMoveableElement implements Pool.Poolable {

    private int damages;

    public Shot() {
        super();
    }

    public Shot(float x, float y, float w, float h, float s, int dam) {
        super(x, y, w, h, s);
        damages = dam;
    }

    public void init(Shot shot) {
        super.init(shot);
        damages = 0;
    }

    /**
     * Methode de l'interface Poolable qui reinitialise les attributs de l'objet.
     */
    @Override
    public void reset() {
        this.position.set(0f, 0f);
        this.height = 0f;
        this.width = 0f;
        updateBoundingBox();
        this.isMoving = false;
        this.direction = null;
    }
}