package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.utils.Pool;

/**
 * Classe decrivant un ennemi.
 */
public class Enemy extends GameMoveableElement implements Pool.Poolable {

    public Enemy() {
        super();
    }

    public Enemy(float x, float y, float w, float h, float s) {
        super(x, y, w, h, s);
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
