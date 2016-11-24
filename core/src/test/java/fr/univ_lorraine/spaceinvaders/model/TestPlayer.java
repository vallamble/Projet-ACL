package fr.univ_lorraine.spaceinvaders.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestPlayer {

    private Player player;

    private float delta;

    @Before
    public void setUp() {
        player = new Player(0f, 0f, 5f, 6f, 1f);    // speed = 1
        delta = 1f; // deplacement = delta * speed = 1 * 1 = 1
    }

    /**
     * On verifie que le vaisseau ne se deplace pas
     * sans appel a turnLeft ou turnRight.
     */
    @Test
    public void testUpdateNotMoving() {
        player.setDirection(GameMoveableElement.Direction.UP);
        player.update(delta);
        assertEquals(0f, player.getPosition().x, 0f);
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
    public void testUpdateRight() {
        player.turnRight();
        player.update(delta);
        assertEquals(1f, player.getPosition().x, 0f);
        assertEquals(0f, player.getPosition().y, 0f);
    }

    @Test
    public void testUpdateLeftThenRight() {
        player.turnLeft();
        player.update(delta);
        player.turnRight();
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

    // On teste que le vaisseau du joueur est bien limite a des mouvements horizontaux (gauche et droite)
    @Test
    public void testUpdateOtherThanLeftAndRight() {
        GameMoveableElement.Direction[] forbiddenDirections = {GameMoveableElement.Direction.UP, GameMoveableElement.Direction.DOWN, GameMoveableElement.Direction.UPLEFT, GameMoveableElement.Direction.UPRIGHT, GameMoveableElement.Direction.DOWNLEFT, GameMoveableElement.Direction.DOWNRIGHT};
        for (GameMoveableElement.Direction direction : forbiddenDirections) {
            player.setDirection(direction);
            player.setIsMoving(true);
            player.update(delta);
            assertEquals(0f, player.getPosition().x, 0f);
            assertEquals(0f, player.getPosition().y, 0f);
        }
    }

    // On teste que le vaisseau ne détecte pas de collision avec un objet vide
    @Test
    public void testHasNoCollisionWithNullObject() {
        Enemy e = null;
        assertFalse(player.hasCollision(e));
    }

    // On teste que le vaisseau ne détecte pas de collision si non nécessaire
    @Test
    public void testHasNoCollisionWithEnemy() {
        Enemy e = new Enemy(player.getPosition().x, player.getPosition().y + player.getHeight()+5, player.getHeight(), player.getWidth(), 1f);
        assertFalse(player.hasCollision(e));
        assertFalse(e.hasCollision(player));
    }

    //On teste que le vaisseau entre bien en collision avec un enemmi
    @Test
    public void testHasCollisionWithEnemy() {
        Enemy e = new Enemy(player.getPosition().x, player.getPosition().y, player.getHeight(), player.getWidth(), 1f);
        assertTrue(player.hasCollision(e));
        assertTrue(e.hasCollision(player));
        player.handleCollision(e);
        assertTrue(player.isDead());
    }

}
