package fr.univ_lorraine.spaceinvaders.model;

/**
 * Contient les parametres de generation d'un type d'ennemi en particulier.
 */
public class EnemyPeriodicGeneration {

    private Enemy enemyAttributes;

    private AbstractEnemyController enemyController;

    private float timeBetweenGenerations;

    private float timeSinceLastEnemyGeneration;

    public EnemyPeriodicGeneration(Enemy enemyAttributes, float timeBetweenGenerations) {
        this.enemyAttributes = enemyAttributes;
        this.timeBetweenGenerations = timeBetweenGenerations;
        this.timeSinceLastEnemyGeneration = 0;
    }

    public EnemyPeriodicGeneration(Enemy enemyAttributes, float timeBetweenGenerations, AbstractEnemyController enemyController) {
        this.enemyAttributes = enemyAttributes;
        this.timeBetweenGenerations = timeBetweenGenerations;
        this.enemyController = enemyController;
        this.timeSinceLastEnemyGeneration = 0;
    }

    public Enemy getEnemyAttributes() {
        return enemyAttributes;
    }

    public AbstractEnemyController getEnemyController() {
        return enemyController;
    }

    /**
     * Modifie la frequence de generation de l'ennemi.
     * @param multiplier Le multiplicateur.
     */
    public void alterTimeBetweenGeneration(float multiplier) {
        this.timeBetweenGenerations *= multiplier;
    }

    /**
     * Donne le nombre d'ennemi a generer.
     * @param delta Le temps ecoule.
     * @return Le nombre d'ennemi a generer.
     */
    public int calculateNumberOfEnemyToGenerate(float delta) {
        timeSinceLastEnemyGeneration += delta;
        int numberOfEnemiesToGenerate = (int)(timeSinceLastEnemyGeneration / timeBetweenGenerations);
        timeSinceLastEnemyGeneration = timeSinceLastEnemyGeneration % timeBetweenGenerations;
        return numberOfEnemiesToGenerate;
    }

}
