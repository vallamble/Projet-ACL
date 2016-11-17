package fr.univ_lorraine.spaceinvaders.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestShip {

    private Ship ship;

    private float delta;

    @Before
    public void setUp() {
        ship = new Ship(0f, 0f);
        ship.setSpeed(1f);
        delta = 1f; // deplacement = delta * speed = 1 * 1 = 1
    }

    @Test
    public void testUpdateNotMoving() {
        ship.update(delta);
        assertEquals(0f, ship.getPosition().x, 0f);
        assertEquals(0f, ship.getPosition().y, 0f);
    }

    @Test
     public void testUpdateRight() {
        ship.turnRight();
        ship.update(delta);
        assertEquals(1f, ship.getPosition().x, 0f);
        assertEquals(0f, ship.getPosition().y, 0f);
    }

    @Test
    public void testUpdateLeft() {
        ship.turnLeft();
        ship.update(delta);
        assertEquals(-1f, ship.getPosition().x, 0f);
        assertEquals(0f, ship.getPosition().y, 0f);
    }

    @Test
    public void testUpdateLeftThenRight() {
        ship.turnRight();
        ship.update(delta);
        ship.turnLeft();
        ship.update(delta);
        assertEquals(0f, ship.getPosition().x, 0f);
        assertEquals(0f, ship.getPosition().y, 0f);
    }

    @Test
    public void testUpdateNotMovingAfterMoving() {
        ship.turnRight();
        ship.update(delta); // Seul update qui doit faire se deplacer le vaisseau
        ship.update(delta);
        ship.update(delta);
        assertEquals(1f, ship.getPosition().x, 0f);
        assertEquals(0f, ship.getPosition().y, 0f);
    }

}
