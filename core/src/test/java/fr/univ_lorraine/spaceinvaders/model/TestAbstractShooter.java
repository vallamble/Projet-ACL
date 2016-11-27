package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

/**
 * Created by alexis on 27/11/2016.
 */
public class TestAbstractShooter {

    private AbstractShooter abstractShooter;

    private Vector2 position;

    @Before
    public void setUp() {
        World world = new World(1f, 1f);
        abstractShooter = new AbstractShooter(world) {
            @Override
            public void shoot(Vector2 position) {

            }

            @Override
            public void update(float delta) {

            }
        };
        position = new Vector2(0f, 0f);
    }

    // Test methode generateShots qui doit generer les tirs en fonction des shotCharacteristics et de la position passee en parametre

    @Test
    public void testGenerateShotsWithoutShotsCharacteristics() {
        Shot[] shots = abstractShooter.generateShots(position);
        assertEquals(0, shots.length);
    }

    @Test
    public void testGenerateShotsWithOneShotsCharacteristics() {
        Shot shot = new Shot(0f, 0f, 1f, 5f, 10f, 10);
        ShotCharacteristics shotCharacteristics = new ShotCharacteristics(shot, 0f, 0f);
        abstractShooter.addShotCharacteristics(shotCharacteristics);
        Shot[] shots = abstractShooter.generateShots(position);
        assertEquals(1, shots.length);
    }

    @Test
    public void testGenerateShotsWithShotsCharacteristics() {
        Shot shot = new Shot(0f, 0f, 1f, 5f, 10f, 10);
        ShotCharacteristics shotCharacteristics1 = new ShotCharacteristics(shot, 0f, 0f);
        ShotCharacteristics shotCharacteristics2 = new ShotCharacteristics(shot, 5f, 3f);
        ShotCharacteristics shotCharacteristics3 = new ShotCharacteristics(shot, -4f, 4f);
        ShotCharacteristics shotCharacteristics4 = new ShotCharacteristics(shot, -50f, -2f);
        ShotCharacteristics shotCharacteristics5 = new ShotCharacteristics(shot, 14f, 50f);

        abstractShooter.addShotCharacteristics(shotCharacteristics1);
        abstractShooter.addShotCharacteristics(shotCharacteristics2);
        abstractShooter.addShotCharacteristics(shotCharacteristics3);
        abstractShooter.addShotCharacteristics(shotCharacteristics4);
        abstractShooter.addShotCharacteristics(shotCharacteristics5);

        Shot[] shots = abstractShooter.generateShots(position);
        assertEquals(5, shots.length);
    }
}
