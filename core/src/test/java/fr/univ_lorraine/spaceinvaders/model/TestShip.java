package fr.univ_lorraine.spaceinvaders.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestShip {

    private Player player;

    private float delta;

    @Before
    public void setUp() {
        player = new Player(0f, 0f);
        player.setSpeed(1f);
        delta = 1f; // deplacement = delta * speed = 1 * 1 = 1
    }

    @Test
    public void testUpdateNotMoving() {
        player.update(delta);
        assertEquals(0f, player.getPosition().x, 0f);
        assertEquals(0f, player.getPosition().y, 0f);
    }

    @Test
     public void testUpdateRight() {
        player.turnRight();
        player.update(delta);
        assertEquals(1f, player.getPosition().x, 0f);
        assertEquals(0f, player.getPosition().y, 0f);
    }

    @Test
    public void testUpdateLeft() {
        player.turnLeft();
        player.update(delta);
        assertEquals(-1f, player.getPosition().x, 0f);
        assertEquals(0f, player.getPosition().y, 0f);
    }

    @Test
    public void testUpdateLeftThenRight() {
        player.turnRight();
        player.update(delta);
        player.turnLeft();
        player.update(delta);
        assertEquals(0f, player.getPosition().x, 0f);
        assertEquals(0f, player.getPosition().y, 0f);
    }

    @Test
    public void testUpdateNotMovingAfterMoving() {
        player.turnRight();
        player.update(delta); // Seul update qui doit faire se deplacer le vaisseau
        player.update(delta);
        player.update(delta);
        assertEquals(1f, player.getPosition().x, 0f);
        assertEquals(0f, player.getPosition().y, 0f);
    }

}
