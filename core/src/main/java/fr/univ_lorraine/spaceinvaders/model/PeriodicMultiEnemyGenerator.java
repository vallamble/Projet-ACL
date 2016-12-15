package fr.univ_lorraine.spaceinvaders.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generateur d'ennemi qui genere plusieurs types d'ennemi
 * de maniere periodique
 * tout en haut du monde et positionner aleatoirement horizontalement.
 */
public class PeriodicMultiEnemyGenerator implements IEnemyGenerator {

    protected Random rng;

    protected List<EnemyPeriodicGeneration> enemyPeriodicGenerations;

    public PeriodicMultiEnemyGenerator() {
        rng = new Random();
        enemyPeriodicGenerations = new ArrayList<EnemyPeriodicGeneration>();
    }

    public void addEnemyPeriodicGeneration(EnemyPeriodicGeneration enemyPeriodicGeneration) {
        enemyPeriodicGenerations.add(enemyPeriodicGeneration);
    }

    @Override
    public void generateEnemy(World world, float delta) {
        // Pour chaque type d'ennemi
        for (EnemyPeriodicGeneration enemyPeriodicGeneration : enemyPeriodicGenerations) {
            Enemy enemyAttributes = enemyPeriodicGeneration.getEnemyAttributes();
            AbstractEnemyController enemyController = enemyPeriodicGeneration.getEnemyController();

            float maxX = world.getWidth() - enemyAttributes.getWidth();
            float minX = 0;
            float posY = world.getHeight();

            // Si c'est le moment de generer un ennemi,
            // on genere le nombre d'ennemis requis
            for (int i = 1; i <= enemyPeriodicGeneration.calculateNumberOfEnemyToGenerate(delta); i++) {
                float posX = rng.nextFloat() * (maxX - minX) + minX;
                Enemy newEnemy = world.obtainEnemyFromPool();
                newEnemy.init(enemyAttributes);
                newEnemy.setPosition(posX, posY);
                world.getEnemies().add(newEnemy);       // On ajoute l'ennemi au monde
                if (enemyController != null)
                    enemyController.addEnemy(newEnemy); // et au controleur d'ennemi attribue
            }
        }
    }

}
