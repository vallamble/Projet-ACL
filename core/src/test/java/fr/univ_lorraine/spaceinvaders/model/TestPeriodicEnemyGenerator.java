package fr.univ_lorraine.spaceinvaders.model;


import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPeriodicEnemyGenerator {

    private World world;

    private IEnemyGenerator periodicEnemyGenerator;

    private Enemy enemyAttributes;

    private float timeBetweenGenerations;

    private float delta;

    @Before
    public void setUp() {
        world = new World(50f, 50f);
        enemyAttributes = new Enemy(0f, 0f, 5f, 5f, 1f);
        timeBetweenGenerations = 1f;
        periodicEnemyGenerator = new PeriodicEnemyGenerator(enemyAttributes, timeBetweenGenerations);
        world.getEnemyGenerators().clear();
        world.getEnemyGenerators().add(periodicEnemyGenerator);
        delta = 1f;
    }

    @Test
    public void testDontGenerateEnemy() {
        periodicEnemyGenerator.generateEnemy(world, delta / 2);
        assertTrue(world.getEnemies().isEmpty());
    }

    @Test
    public void testGenerateOneEnemy() {
        periodicEnemyGenerator.generateEnemy(world, delta);
        assertEquals(1, world.getEnemies().size());
    }

    @Test
    public void testGenerateHundredEnemies() {
        periodicEnemyGenerator.generateEnemy(world, delta*100);
        assertEquals(100, world.getEnemies().size());
    }
}
