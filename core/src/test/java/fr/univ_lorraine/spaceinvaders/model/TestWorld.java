package fr.univ_lorraine.spaceinvaders.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestWorld {

    private World world;

    private Ship ship;

    private float worldWidth;

    private float worldHeight;

    private float delta;

    @Before
    public void setUp() {
        worldWidth = 50f;
        worldHeight = 50f;
        world = new World(worldWidth, worldHeight);
        ship = world.getShip();
        delta = 1f;
    }

    /**
     * Test sur le positionnement du vaisseau dans le monde.
     * Le vaisseau est positionne tout en bas, au milieu du monde.
     * ATTENTION : Si le positionnement du vaisseau venait a etre change, ce test devrait etre change/supprime.
     */
    @Test
    public void testConstructorShipPositionning() {
        float posX = world.getWidth()/2 - ship.getWidth()/2;
        assertEquals(posX, ship.getPosition().x, 0f);
        assertEquals(0, ship.getPosition().y, 0f);
    }

    /**
     * Test verifiant que le vaisseau ne peut pas sortir du monde par le cote gauche
     * et que le monde le repositionne bien a la limite.
     */
    @Test
    public void testUpdateShipOutsideOfWorldLeft() {
        ship.setPosition(0f, 0f);
        ship.turnLeft();
        world.update(delta);
        assertEquals(0f, ship.getPosition().x, 0f);
        assertEquals(0f, ship.getPosition().y, 0f);
    }

    /**
     * Test verifiant que le vaisseau ne peut pas sortir du monde par le cote droit
     * et que le monde le repositionne bien a la limite.
     */
    @Test
    public void testUpdateShipOutsideOfWorldRight() {
        float posX = world.getWidth() - ship.getWidth();
        ship.setPosition(posX, 0f);
        ship.turnRight();
        world.update(delta);
        assertEquals(posX, ship.getPosition().x, 0f);
        assertEquals(0f, ship.getPosition().y, 0f);
    }
}
