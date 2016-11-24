package fr.univ_lorraine.spaceinvaders.model;

/**
 * Created by lupinski1u on 24/11/16.
 */
public interface Collidable {

    public void handleCollision(GameElement element);

    public boolean hasCollision(GameElement element);

}
