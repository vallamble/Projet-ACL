package fr.univ_lorraine.spaceinvaders.model;

/**
 * Interface representant un generateur d'ennemi permettant ainsi de gerer plusieurs logique de generation d'ennemi.
 */
public interface IEnemyGenerator {

    public void generateEnemy(World world, float delta);

}
