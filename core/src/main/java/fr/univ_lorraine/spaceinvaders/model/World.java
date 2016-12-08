package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.utils.Pool;

import java.security.PrivateKey;
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

    private List<Shot> enemyShots;

    private List<IEnemyGenerator> enemyGenerators;

    private List<AbstractEnemyController> enemyControllers;

    private boolean pause = false;

    public Music music;

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

        player = new Player(0f, 0f, 1.2f, 1.2f*75f/99f, 5f);
        // On positionne le vaisseau du joueur en bas au milieu du monde
        player.setPosition(this.width/2 - player.getWidth()/2, 0);
        PlayerShooterWithCooldown playerShooter = new PlayerShooterWithCooldown(this, 0.3f);
        Shot playerShot = new Shot(0f, 0f, 0.1f, 0.1f*33f/9f, 10f, 1);
        playerShot.setDirection(GameMoveableElement.Direction.UP);

        // tir cote gauche
        playerShooter.addShotCharacteristics(new ShotCharacteristics(playerShot, player.getWidth() * 8.5f/95f - playerShot.getWidth() / 2, player.getHeight() * 80f/127f));
        // tir cote droit
        playerShooter.addShotCharacteristics(new ShotCharacteristics(playerShot, player.getWidth() * 86.5f/95f - playerShot.getWidth() / 2, player.getHeight() * 80f/127f));
        // tir milieu
        //playerShooter.addShotCharacteristics(new ShotCharacteristics(playerShot, player.getWidth() / 2 - playerShot.getWidth() / 2, player.getHeight()));

        player.setShooter(playerShooter);
        player.setLife(4);

        playerShots = new ArrayList<Shot>();

        enemyShots = new ArrayList<Shot>();

        shotPool = new Pool<Shot>() {
            @Override
            protected Shot newObject() {
                return new Shot();
            }
        };

        enemies = new ArrayList<Enemy>();
        enemyPool = new Pool<Enemy>() {
            @Override
            protected Enemy newObject() {
                return new Enemy();
            }
        };

        enemyGenerators = new ArrayList<IEnemyGenerator>();

        enemyControllers = new ArrayList<AbstractEnemyController>();
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

    public List<Shot> getEnemyShots() {
        return enemyShots;
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

    /**
     * Permet d'obtenir un tir depuis le Pool.
     * @return Un nouveau tir provenant du Pool.
     */
    public Shot obtainShotFromPool() {
        return shotPool.obtain();
    }

    public void addEnemyGenerator(IEnemyGenerator enemyGenerator) {
        enemyGenerators.add(enemyGenerator);
    }

    public void addEnemyController(AbstractEnemyController enemyController) {
        enemyControllers.add(enemyController);
    }

    /**
     * On met a jour les differents elements du monde.
     * @param delta Le temps ecoule depuis le dernier update.
     */
    public void update(float delta) {

        if (!pause) {


            // Gestion des ennemis
            for (AbstractEnemyController enemyController : enemyControllers)
                enemyController.control();

            for (Iterator<Enemy> iterator = enemies.iterator(); iterator.hasNext(); ) {
                Enemy enemy = iterator.next();
                enemy.update(delta);

                if (outOfWorld(enemy)) {    // Si l'ennemi sort du monde,
                    enemyPool.free(enemy);  // on le remet dans le pool
                    iterator.remove();      // et on l'enleve de la liste d'ennemis actifs
                }
            }

            // Gestion du joueur
            player.update(delta);
            // On verifie que le vaisseau ne sort pas du monde, si c'est le cas, on le repositionne
            if (player.getPosition().x < 0f)
                player.setPosition(0f, player.getPosition().y);
            else if (player.getPosition().x > width - player.getWidth())
                player.setPosition(width - player.getWidth(), player.getPosition().y);

            // Gestion des tirs du joueur
            for (Iterator<Shot> iterator = playerShots.iterator(); iterator.hasNext(); ) {
                Shot shot = iterator.next();
                shot.update(delta);
                if (outOfWorld(shot)) {    // Si le tir sort du monde,
                    shotPool.free(shot);  // on le remet dans le pool
                    iterator.remove();    // et on l'enleve de la liste d'ennemis actifs
                }
            }

            // Gestion des tirs des ennemis
            for (Iterator<Shot> iterator = enemyShots.iterator(); iterator.hasNext(); ) {
                Shot shot = iterator.next();
                shot.update(delta);
                if (outOfWorld(shot)) {    // Si le tir sort du monde,
                    shotPool.free(shot);  // on le remet dans le pool
                    iterator.remove();    // et on l'enleve de la liste d'ennemis actifs
                }
            }

            // Eventuellement, on genere des ennemis
            for (IEnemyGenerator enemyGenerator : enemyGenerators)
                enemyGenerator.generateEnemy(this, delta);

            // On verifie les collisions
            checkCollisions();
        }

    }

    private void checkCollisions() {
        // On parcourt les ennemis pour verifier leurs collisions
        for (Iterator<Enemy> enemyIterator = enemies.iterator(); enemyIterator.hasNext();) {
            Enemy enemy = enemyIterator.next();
            // On verifie les collisions avec le joueur
            if (enemy.hasCollision(player)) {
                enemy.handleCollision(player);
                player.handleCollision(enemy);
                if (player.isDead()) {  // Si le joueur est mort
                    endGame = true;
                    return;             // On arrete le jeu
                }
            }

            // On verifie les collisions avec les tirs du joueurs
            for (Iterator<Shot> playerShotIterator = playerShots.iterator(); playerShotIterator.hasNext();) {
                Shot playerShot = playerShotIterator.next();
                // On ne verifie pas la collision si l'ennemi est deja mort
                if (!enemy.isDead() && enemy.hasCollision(playerShot)) {
                    enemy.handleCollision(playerShot);
                    playerShot.handleCollision(enemy);
                    if (playerShot.isDead()) {          // Si le tir est detruit
                        shotPool.free(playerShot);      // on le remet dans le pool
                        playerShotIterator.remove();    // et on l'enleve de la liste des tirs actifs
                    }
                }
            }

            // Finalement, on verifie l'etat de l'ennemi
            if (enemy.isDead()) {       // Si l'ennemi est mort
                enemyPool.free(enemy);  // on le remet dans le pool
                enemyIterator.remove(); // et on l'enleve de la liste d'ennemis actifs
            }
        }

        // On verifie les collisions entre le joueur et les tis ennemis
        for (Iterator<Shot> enemyShotIterator = enemyShots.iterator(); enemyShotIterator.hasNext();) {
            Shot enemyShot = enemyShotIterator.next();
            if (enemyShot.hasCollision(player)) {
                enemyShot.handleCollision(player);
                player.handleCollision(enemyShot);
                if (player.isDead()) {  // Si le joueur est mort
                    endGame = true;
                    return;             // On arrete le jeu
                }
                if (enemyShot.isDead()) {          // Si le tir est detruit
                    shotPool.free(enemyShot);      // on le remet dans le pool
                    enemyShotIterator.remove();    // et on l'enleve de la liste des tirs actifs
                }
            }
        }


    }


    /**
     * Inverse le boolén pause
     */
    public void pause() {
        this.pause = !pause;
    }


}
