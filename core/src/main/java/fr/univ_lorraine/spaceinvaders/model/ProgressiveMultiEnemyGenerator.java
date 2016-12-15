package fr.univ_lorraine.spaceinvaders.model;

/**
 * Generateur d'ennemi qui genere plusieurs types d'ennemi
 * de maniere progressive (periodique avec augmentation de la frequence de generation au fur et a mesure)
 * tout en haut du monde et positionner aleatoirement horizontalement.
 */
public class ProgressiveMultiEnemyGenerator extends PeriodicMultiEnemyGenerator {

    private float frequencyMultiplier;

    private float timeBetweenFrequencyAlteration;

    private float timeSinceLastAlteration;

    public ProgressiveMultiEnemyGenerator(float frequencyMultiplier, float timeBetweenFrequencyAlteration) {
        super();
        this.frequencyMultiplier = frequencyMultiplier;
        this.timeBetweenFrequencyAlteration = timeBetweenFrequencyAlteration;
        this.timeSinceLastAlteration = 0;
    }

    @Override
    public void generateEnemy(World world, float delta) {
        // On altere le temps entre les frequences de generations des ennemis si necessaire
        timeSinceLastAlteration += delta;
        int numberOfAlterationsToDo = (int)(timeSinceLastAlteration / timeBetweenFrequencyAlteration);
        timeSinceLastAlteration = timeSinceLastAlteration % timeBetweenFrequencyAlteration;
        // Si il est necessaire de changer la frequence de generation
        if (numberOfAlterationsToDo > 0)
            for (EnemyPeriodicGeneration enemyPeriodicGeneration : enemyPeriodicGenerations)    // On le fait pour chaque type d'ennemi
                enemyPeriodicGeneration.alterTimeBetweenGeneration(1/(float)Math.pow(frequencyMultiplier, numberOfAlterationsToDo));
        super.generateEnemy(world, delta);
    }

}
