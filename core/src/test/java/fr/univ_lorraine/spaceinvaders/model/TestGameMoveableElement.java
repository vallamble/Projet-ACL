package fr.univ_lorraine.spaceinvaders.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGameMoveableElement {

    private GameMoveableElement gameMoveableElement;

    private float delta;

    @Before
    public void setUp() {
        gameMoveableElement = new GameMoveableElement(0f, 0f, 3f, 4f, 1f) {
        };          // speed = 1
        gameMoveableElement.setIsMoving(true);
        delta = 1f; // deplacement = delta * speed = 1 * 1 = 1
    }

    @Test
    public void testUpdateNotMoving() {
        gameMoveableElement.setIsMoving(false);
        gameMoveableElement.update(delta);
        assertEquals(0f, gameMoveableElement.getPosition().x, 0f);
        assertEquals(0f, gameMoveableElement.getPosition().y, 0f);
    }
    
    @Test
    public void testUpdateUp() {
        gameMoveableElement.setDirection(GameMoveableElement.Direction.UP);
        gameMoveableElement.update(delta);
        assertEquals(0f, gameMoveableElement.getPosition().x, 0f);
        assertEquals(1f, gameMoveableElement.getPosition().y, 0f);
    }

    @Test
    public void testUpdateDown() {
        gameMoveableElement.setDirection(GameMoveableElement.Direction.DOWN);
        gameMoveableElement.update(delta);
        assertEquals(0f, gameMoveableElement.getPosition().x, 0f);
        assertEquals(-1f, gameMoveableElement.getPosition().y, 0f);
    }

    @Test
    public void testUpdateLeft() {
        gameMoveableElement.setDirection(GameMoveableElement.Direction.LEFT);
        gameMoveableElement.update(delta);
        assertEquals(-1f, gameMoveableElement.getPosition().x, 0f);
        assertEquals(0f, gameMoveableElement.getPosition().y, 0f);
    }

    @Test
    public void testUpdateRight() {
        gameMoveableElement.setDirection(GameMoveableElement.Direction.RIGHT);
        gameMoveableElement.update(delta);
        assertEquals(1f, gameMoveableElement.getPosition().x, 0f);
        assertEquals(0f, gameMoveableElement.getPosition().y, 0f);
    }

    @Test
    public void testUpdateLeftThenRight() {
        gameMoveableElement.setDirection(GameMoveableElement.Direction.LEFT);
        gameMoveableElement.update(delta);
        gameMoveableElement.setDirection(GameMoveableElement.Direction.RIGHT);
        gameMoveableElement.update(delta);
        assertEquals(0f, gameMoveableElement.getPosition().x, 0f);
        assertEquals(0f, gameMoveableElement.getPosition().y, 0f);
    }
}
