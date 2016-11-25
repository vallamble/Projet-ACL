package fr.univ_lorraine.spaceinvaders.model;

/**
 * Created by lupinski1u on 24/11/16.
 */
public interface Collidable {

    public enum CollisionType {PLAYER, ENEMY, SHOT};

    public CollisionType getCollisionType();

    public void handleCollision(GameElement element);

    public boolean hasCollision(GameElement element);

}
