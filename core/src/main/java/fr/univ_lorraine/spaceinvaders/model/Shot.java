package fr.univ_lorraine.spaceinvaders.model;

/**
 * Classe modelisant un tir.
 */
public class Shot extends GameMoveableElement {

    public Shot() {
        super();
    }

    public Shot(float x, float y, float w, float h, float s, int dam) {
        super(x, y, w, h, s);
        collisionDamages = dam;
    }

    public void init(Shot shot) {
        super.init(shot);
        collisionDamages = shot.collisionDamages;
    }

    public int getCollisionDamages() {
        return collisionDamages;
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
