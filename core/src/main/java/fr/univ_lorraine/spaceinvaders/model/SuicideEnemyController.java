package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Created by alexis on 15/12/2016.
 */
public class SuicideEnemyController extends AbstractEnemyController {

    private static final float SHIFT = 2f;

    @Override
    public void control(World world) {
        for (Enemy enemy : enemies)
            changeDirection(enemy, world);
    }

    private void changeDirection(Enemy enemy, World world) {
        Vector2 playerPosition = world.getPlayer().getPosition();
        if (enemy.getPosition().x > playerPosition.x + SHIFT || enemy.getPosition().x >= world.getWidth() - enemy.getWidth())
            enemy.setDirection(GameMoveableElement.Direction.DOWNLEFT);
        else if (enemy.getPosition().x < playerPosition.x - SHIFT || enemy.getPosition().x <= 0f)
            enemy.setDirection(GameMoveableElement.Direction.DOWNRIGHT);
    }

}
