package fr.univ_lorraine.spaceinvaders.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alexis on 05/12/2016.
 */
public abstract class AbstractEnemyController {

    protected List<Enemy> enemies;

    public AbstractEnemyController() {
        enemies = new ArrayList<Enemy>();
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public abstract void control(float delta, World world);

    public void removeEnemy(Enemy enemy) {
        enemies.remove(enemy);
    }

}
