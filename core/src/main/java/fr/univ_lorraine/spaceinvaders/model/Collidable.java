package fr.univ_lorraine.spaceinvaders.model;

/**
 * Interface pour les elements susceptible d'entrer en collision.
 */
public interface Collidable {

    public enum CollisionType {PLAYER, ENEMY, SHOT, HEAL_BONUS};

    public CollisionType getCollisionType();

    /**
     * Methode permettant de gerer la reaction a une collision.
     * @param element L'element avec lequel s'est produit la collision.
     */
    public void handleCollision(GameElement element);

    /**
     * Methode verifiant si il y a collision entre deux elements.
     * @param element L'autre element.
     * @return Vrai si il y a collision.
     */
    public boolean hasCollision(GameElement element);

}
