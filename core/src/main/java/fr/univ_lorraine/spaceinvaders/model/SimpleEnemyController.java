package fr.univ_lorraine.spaceinvaders.model;

/**
 * Created by alexis on 05/12/2016.
 */
public class SimpleEnemyController extends AbstractEnemyController {

    @Override
    public void control() {
        // On fait tirer les ennemis des que possible
        for (Enemy enemy : enemies)
            enemy.shoot();
    }

}
