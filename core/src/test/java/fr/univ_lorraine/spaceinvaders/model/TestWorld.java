package fr.univ_lorraine.spaceinvaders.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestWorld {

    private World world;

    private Player player;

    private float worldWidth;

    private float worldHeight;

    private float delta;

    @Before
    public void setUp() {
        worldWidth = 50f;
        worldHeight = 50f;
        world = new World(worldWidth, worldHeight);
        player = world.getPlayer();
        delta = 1f;
    }

    /**
     * Test sur le positionnement du vaisseau du joueur dans le monde.
     * Le vaisseau est positionne tout en bas, au milieu du monde.
     * ATTENTION : Si le positionnement du vaisseau venait a etre change, ce test devrait etre change/supprime.
     */
    @Test
    public void testConstructorPlayerPositionning() {
        float posX = world.getWidth()/2 - player.getWidth()/2;
        assertEquals(posX, player.getPosition().x, 0f);
        assertEquals(0, player.getPosition().y, 0f);
    }

    /**
     * Test verifiant que le vaisseau ne peut pas sortir du monde par le cote gauche
     * et que le monde le repositionne bien a la limite.
     */
    @Test
    public void testUpdatePlayerOutsideOfWorldLeft() {
        player.setPosition(0f, 0f);
        player.turnLeft();
        world.update(delta);
        assertEquals(0f, player.getPosition().x, 0f);
        assertEquals(0f, player.getPosition().y, 0f);
    }

    /**
     * Test verifiant que le vaisseau ne peut pas sortir du monde par le cote droit
     * et que le monde le repositionne bien a la limite.
     */
    @Test
    public void testUpdatePlayerOutsideOfWorldRight() {
        float posX = world.getWidth() - player.getWidth();
        player.setPosition(posX, 0f);
        player.turnRight();
        world.update(delta);
        assertEquals(posX, player.getPosition().x, 0f);
        assertEquals(0f, player.getPosition().y, 0f);
    }

    @Test
    public void testUpdateEnnemiesOutOfWorld() {
        Enemy enemyInsideWorld = new Enemy(0f, 0f, 4f, 3f, 18f);
        Enemy enemyOutsideWorld1 = new Enemy(-2f, 0f, 1f, 1f, 18f);
        Enemy enemyOutsideWorld2 = new Enemy(0f, -2f, 1f, 1f, 18f);
        Enemy enemyOutsideWorld3 = new Enemy(worldWidth+10f, 0f, 4f, 3f, 18f);
        Enemy enemyOutsideWorld4 = new Enemy(0f, worldHeight+10f, 4f, 3f, 18f);
        world.getEnemies().add(enemyOutsideWorld1);
        world.getEnemies().add(enemyOutsideWorld2);
        world.getEnemies().add(enemyInsideWorld);
        world.getEnemies().add(enemyOutsideWorld3);
        world.getEnemies().add(enemyOutsideWorld4);
        world.update(delta);
        assertEquals(1, world.getEnemies().size());
        assertSame(enemyInsideWorld, world.getEnemies().get(0));
    }

    @Test
    public void testEnnemyDestruction() {
        Enemy enemy1 = new Enemy(10f, 10f, 4f, 3f, 18f);
        Enemy enemy2 = new Enemy(5f, 5f, 4f, 3f, 18f);
        player.shoot();
        world.getEnemies().add(enemy1);
        world.getEnemies().add(enemy2);
        world.getPlayerShots().get(0).setPosition(10f,10f);
        world.checkCollisions();
        assertEquals(1, world.getEnemies().size());
        assertFalse(world.getEnemies().contains(enemy1));
    }

    @Test
    public void testEndOfTheGame() {
        Enemy enemy1 = new Enemy(10f, 10f, 4f, 3f, 18f);
        world.getEnemies().add(enemy1);

        Enemy enemyAttributes = new Enemy(0f, 0f, 1f, 50f/98f, 7f);
        enemyAttributes.setIsMoving(true);
        enemyAttributes.setDirection(GameMoveableElement.Direction.DOWN);

        EnemyShooterWithCooldown enemyShooter = new EnemyShooterWithCooldown(world, 0f);
        Shot enemyShot = new Shot(0f, 0f, 0.1f, 0.5f, 10f, 1);
        enemyShot.setDirection(GameMoveableElement.Direction.DOWN);
        enemyShooter.addShotCharacteristics(new ShotCharacteristics(enemyShot, enemyAttributes.getWidth() / 2 - enemyShot.getWidth() / 2, - enemyAttributes.getHeight()));

        enemy1.setShooter(enemyShooter);
        enemy1.shoot();
        enemy1.shoot();
        enemy1.shoot();
        enemy1.shoot();
        for (Shot s: world.getEnemyShots())
        {
            s.setPosition(player.position.x, player.position.y);
        }
        world.checkCollisions();
        assertEquals(world.getEnemyShots().size(), 0);
        assertTrue(world.getEndGame());
    }
}
