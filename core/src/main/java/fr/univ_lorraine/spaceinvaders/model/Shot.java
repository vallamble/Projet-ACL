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
        damages = shot.damages;
    }

    public int getDamages() {
        return damages;
    }

    /**
     * Methode de l'interface Poolable qui reinitialise les attributs de l'objet.
     */
    @Override
    public void reset() {
        this.position.set(0f, 0f);
        this.height = 0f;
        this.width = 0f;
        this.life = 0;
        updateBoundingBox();
        this.isMoving = false;
        this.direction = null;
    }

    @Override
    public void handleCollision(GameElement element) {
        switch (element.getCollisionType()) {
            case SHOT:
                break;
            case ENEMY:
            case PLAYER:
                this.life -= 1;
                break;
        }
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.SHOT;
    }
}
