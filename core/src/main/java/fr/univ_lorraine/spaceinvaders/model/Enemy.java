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

    @Override
    public void handleCollision(GameElement element) {
        switch (element.getCollisionType()) {
            case PLAYER:
                this.life = 0;
                break;
            case SHOT:
                Shot s = (Shot) element;
                this.life -= s.getDamages();
                break;
        }
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.ENEMY;
    }
}
