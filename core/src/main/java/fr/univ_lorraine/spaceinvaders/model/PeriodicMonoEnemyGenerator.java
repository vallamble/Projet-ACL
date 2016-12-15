package fr.univ_lorraine.spaceinvaders.model;

import java.util.Random;

/**
 * Generateur d'ennemi qui genere un seul type d'ennemi
 * de maniere periodique
 * tout en haut du monde et positionner aleatoirement horizontalement.
 */
public class PeriodicMonoEnemyGenerator implements IEnemyGenerator {

    private Enemy enemyAttributes;

    private AbstractEnemyController enemyController;

    private float timeBetweenGenerations;

    private float timeSinceLastEnemyGeneration;

    private Random rng;

    public PeriodicMonoEnemyGenerator(Enemy en, float timeBetweenGenerations) {
        enemyAttributes = en;
        this.timeBetweenGenerations = timeBetweenGenerations;
        timeSinceLastEnemyGeneration = 0f;
        rng = new Random();
    }

    public void setEnemyController(AbstractEnemyController enemyController) {
        this.enemyController = enemyController;
    }

    @Override
    public void generateEnemy(World world, float delta) {
        float maxX = world.getWidth() - enemyAttributes.getWidth();
        float minX = 0;
        float posY = world.getHeight();
        timeSinceLastEnemyGeneration += delta;
        int numberOfEnemiesToGenerate = (int)(timeSinceLastEnemyGeneration / timeBetweenGenerations);
        timeSinceLastEnemyGeneration = timeSinceLastEnemyGeneration % timeBetweenGenerations;
        // Si c'est le moment de generer un ennemi,
        // on genere le nombre d'ennemis requis
        for (int i = 1; i <= numberOfEnemiesToGenerate; i++) {
            float posX = rng.nextFloat() * (maxX - minX) + minX;
            Enemy newEnemy = world.obtainEnemyFromPool();
            newEnemy.init(enemyAttributes);
            newEnemy.setPosition(posX, posY);
            world.getEnemies().add(newEnemy);   // On ajoute l'ennemi au monde
            if (enemyController != null)
                enemyController.addEnemy(newEnemy); // et au controleur d'ennemi attribue
        }
    }

}
