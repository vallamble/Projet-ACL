package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.utils.Pool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Classe modelisant le monde du jeu.
 * Prend en charge la mise a jour des differents elements du monde en fonction du temps.
 */
public class World {

	private float width, height;

	private Player player;

    private List<Enemy> enemies;

    private List<Shot> playerShots;

    private List<IEnemyGenerator> enemyGenerators;

    /**
     * Rectangle representant les limites du monde.
     */
    private Rectangle worldLimits;

    /**
     * Pool d'ennemi : permet de prendre un ennemi deja cree (new) et de le reutiliser.
     * Evite de faire un new a chaque fois que l'on a besoin d'un ennemi.
     * pool.free(objet) permet de remettre l'objet dans le pool et ainsi de le reutiliser par la suite.
     */
    private final Pool<Enemy> enemyPool;

    private final Pool<Shot> shotPool;

    private boolean endGame = false;

    public World(float w, float h) {
		width = w;
		height = h;
        worldLimits = new Rectangle(0f, 0f, width, height);

        enemies = new ArrayList<Enemy>();

        enemyGenerators = new ArrayList<IEnemyGenerator>();
        Enemy enemyAttributes = new Enemy(0f, 0f, 1f, 1.5f, 10f);
        enemyAttributes.setIsMoving(true);
        enemyAttributes.setDirection(GameMoveableElement.Direction.DOWN);
        enemyGenerators.add(new PeriodicEnemyGenerator(enemyAttributes, 1f));

        player = new Player(0f, 0f, 1f, 1.3368f, 5f);
        // On positionne le vaisseau du joueur en bas au milieu du monde
        player.setPosition(this.width/2 - player.getWidth()/2, 0);

        PlayerShooterWithCooldown playerShooter = new PlayerShooterWithCooldown(this, 0.3f);
        Shot playerShot = new Shot(0f, 0f, 0.1f, 0.5f, 10f, 1);
        playerShot.setDirection(GameMoveableElement.Direction.UP);
        playerShooter.addShotCharacteristics(new ShotCharacteristics(playerShot, player.getWidth() / 2 - playerShot.getWidth() / 2, player.getHeight()));
        player.setShooter(playerShooter);

        playerShots = new ArrayList<Shot>();
        shotPool = new Pool<Shot>() {
            @Override
            protected Shot newObject() {
                return new Shot();
            }
        };

        enemyPool = new Pool<Enemy>() {
            @Override
            protected Enemy newObject() {
                return new Enemy();
            }
        };
        worldLimits = new Rectangle(0f, 0f, width, height);
	}

    /**
     * Determine si un element est totalement sorti du monde.
     * @param gameElement L'element.
     * @return Vrai si l'element est totalement sorti du monde.
     */
    private boolean outOfWorld(GameElement gameElement) {
        return !worldLimits.intersects(gameElement.getBoundingBox());
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Shot> getPlayerShots() {
        return playerShots;
    }

    public List<IEnemyGenerator> getEnemyGenerators() {
        return enemyGenerators;
    }

    public boolean getEndGame() {return endGame;}
    /**
     * Permet d'obtenir un ennemi depuis le Pool.
     * @return Un nouvel ennemi provenant du Pool.
     */
    public Enemy obtainEnemyFromPool() {
        return enemyPool.obtain();
    }


    public void checkCollisions()
    {
        int i = 0;
        boolean playerReachable = true;
        while(i < enemies.size() && playerReachable)
        {
            Enemy e = enemies.get(i);
            if (e.getPosition().y > player.getPosition().y + player.getHeight()) {
                playerReachable = false;
            } else {
                if (player.hasCollision(e)) {
                    player.handleCollision(e);
                    e.handleCollision(player);
                    if (player.isDead())
                    {
                        endGame = true;
                    }
                }
            }
            i++;
        }
        for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
            Enemy enemy = iterator.next();
            int j = 0;
            boolean enemyReachable = true;
            while(j < playerShots.size() && enemyReachable)
            {
                Shot s = playerShots.get(j);
                if (s.getPosition().y + s.getHeight() < enemy.getPosition().y) {
                    playerReachable = false;
                } else {
                    if (enemy.hasCollision(s)) {
                        s.handleCollision(enemy);
                        enemy.handleCollision(s);
                        if (s.isDead())
                        {
                            shotPool.free(s);       // on le remet dans le pool
                            playerShots.remove(s);  // et on l'enleve de la liste des tirs actifs

                        }
                        if (enemy.isDead())
                        {
                            enemyPool.free(enemy);  // on le remet dans le pool
                            iterator.remove();      // et on l'enleve de la liste d'ennemis actifs
                        }
                    }
                }
                j++;
            }
        }
    }

    /**
     * Permet d'obtenir un tir depuis le Pool.
     * @return Un nouveau tir provenant du Pool.
     */
    public Shot obtainShotFromPool() {
        return shotPool.obtain();
    }

    /**
     * On met a jour les differents elements du monde.
     * @param delta Le temps ecoule depuis le dernier update.
     */
    public void update(float delta) {
        // Gestion du joueur
        player.update(delta);
        // On verifie que le vaisseau ne sort pas du monde, si c'est le cas, on le repositionne
        if (player.getPosition().x < 0f)
            player.setPosition(0f, player.getPosition().y);
        else if (player.getPosition().x > width - player.getWidth())
            player.setPosition(width - player.getWidth(), player.getPosition().y);

        // Gestion des ennemis
        for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext();) {
            Enemy enemy = iterator.next();
            enemy.update(delta);

            if (outOfWorld(enemy)) {    // Si l'ennemi sort du monde,
                //System.out.println("out");
                enemyPool.free(enemy);  // on le remet dans le pool
                iterator.remove();      // et on l'enleve de la liste d'ennemis actifs
            }
        }

        // Gestion des tirs du joueur
        for (Iterator<Shot> iterator = playerShots.iterator(); iterator.hasNext();) {
            Shot shot = iterator.next();
            shot.update(delta);
            if (outOfWorld(shot)) {    // Si l'ennemi sort du monde,
                //System.out.println("out");
                shotPool.free(shot);  // on le remet dans le pool
                iterator.remove();      // et on l'enleve de la liste d'ennemis actifs
            }
        }

        // Eventuellement, on genere des ennemis
        for (IEnemyGenerator enemyGenerator : enemyGenerators)
            enemyGenerator.generateEnemy(this, delta);

        checkCollisions();
    }

}
