package fr.univ_lorraine.spaceinvaders.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestGameMoveableElement {

    private GameMoveableElement gameMoveableElement;

    private GameMoveableElement otherElement;

    private float delta;

    @Before
    public void setUp() {
        gameMoveableElement = new GameMoveableElement(0f, 0f, 3f, 4f, 1f) { // speed = 1
            @Override
            public void handleCollision(GameElement element) {
            }

            @Override
            public CollisionType getCollisionType() {
                return null;
            }
        };
        gameMoveableElement.setIsMoving(true);
        delta = 1f; // deplacement = delta * speed = 1 * 1 = 1

        otherElement = new GameMoveableElement() {
            @Override
            public CollisionType getCollisionType() {
                return null;
            }

            @Override
            public void handleCollision(GameElement element) {

            }
        };
    }

    // Test methode update
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

    //Test methode hasCollision
    // On teste qu'un element ne detecte pas de collision avec un objet null
    @Test
    public void testHasNoCollisionWithNullObject() {
        GameMoveableElement other = null;
        assertFalse(gameMoveableElement.hasCollision(null));
    }

    // On teste qu'un element ne detecte pas de collision si non necessaire
    @Test
    public void testHasCollisionFalse() {
        otherElement.init(gameMoveableElement); // On reprend les mêmes valeurs pour les attributs
        // et on le positionne aux meme coordonnees juste au dessus du premier element
        otherElement.setPosition(gameMoveableElement.getPosition().x, gameMoveableElement.getPosition().y + gameMoveableElement.getHeight() + 1);
        assertFalse(gameMoveableElement.hasCollision(otherElement));
        assertFalse(otherElement.hasCollision(gameMoveableElement));
    }

    // On teste qu'un element detecte bien une collision si necessaire
    @Test
    public void testHasCollisionTrue() {
        otherElement.init(gameMoveableElement); // On reprend les mêmes valeurs pour les attributs
        assertTrue(gameMoveableElement.hasCollision(otherElement));
        assertTrue(otherElement.hasCollision(gameMoveableElement));
    }

    @Test
    public void testCollisionBetweenEnemyAndShot() {
        gameMoveableElement = new Enemy(10f,10f,20f,20f,5);
        otherElement = new Shot(10f,10f,20f,20f,5,1);
        otherElement.setPosition(gameMoveableElement.getPosition().x + 1, gameMoveableElement.getPosition().y + 1);
        assertTrue(gameMoveableElement.hasCollision(otherElement));
        gameMoveableElement.handleCollision(otherElement);
        assertTrue(gameMoveableElement.life <= 0);
    }

    @Test
    public void testCollisionBetweenPlayerAndShot() {
        gameMoveableElement = new Player(10f,10f,20f,20f,5);
        otherElement = new Shot(10f,10f,20f,20f,5,1);
        ((Player)gameMoveableElement).setLife(5);
        otherElement.setPosition(gameMoveableElement.getPosition().x + 1, gameMoveableElement.getPosition().y + 1);
        assertTrue(gameMoveableElement.hasCollision(otherElement));
        gameMoveableElement.handleCollision(otherElement);
        assertTrue(gameMoveableElement.life==4);
    }

    @Test
    public void testCollisionBetweenPlayerAndEnemy() {
        gameMoveableElement = new Player(10f,10f,20f,20f,5);
        otherElement = new Enemy(10f,10f,20f,20f,5);
        ((Player)gameMoveableElement).setLife(5);
        assertTrue(gameMoveableElement.hasCollision(otherElement));
        gameMoveableElement.handleCollision(otherElement);
        otherElement.handleCollision(gameMoveableElement);
        assertTrue(gameMoveableElement.life == 0);
        assertTrue(otherElement.life==0);
    }
}
