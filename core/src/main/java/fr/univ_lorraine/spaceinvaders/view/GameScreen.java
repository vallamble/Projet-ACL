package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

import fr.univ_lorraine.spaceinvaders.SpaceInvadersGame;
import fr.univ_lorraine.spaceinvaders.model.Enemy;
import fr.univ_lorraine.spaceinvaders.model.EnemyShooterWithCooldown;
import fr.univ_lorraine.spaceinvaders.model.GameMoveableElement;
import fr.univ_lorraine.spaceinvaders.model.PeriodicEnemyGenerator;
import fr.univ_lorraine.spaceinvaders.model.Shot;
import fr.univ_lorraine.spaceinvaders.model.ShotCharacteristics;
import fr.univ_lorraine.spaceinvaders.model.SimpleEnemyController;
import fr.univ_lorraine.spaceinvaders.model.World;

/**
 * Ecran principale du jeu.
 */
public class GameScreen extends AbstractScreen {

    private World world;

    private WorldRenderer worldRenderer;

    /**
     * Permet d'afficher les fps.
     */
    private FPSLogger fpsLogger;

    /**
     * Active/desactive le fpsLogger.
     */
    private boolean showFPS;

    /**
     * Musique principale présente pendant le jeu.
     */
    private Music mainThemeMusic;

    public GameScreen(SpaceInvadersGame g) {
        super(g);

        world = new World(15f, 20f);

        Enemy enemyAttributes = new Enemy(0f, 0f, 1f, 50f/98f, 7f);
        enemyAttributes.setIsMoving(true);
        enemyAttributes.setDirection(GameMoveableElement.Direction.DOWN);

        EnemyShooterWithCooldown enemyShooter = new EnemyShooterWithCooldown(world, 1f);
        Shot enemyShot = new Shot(0f, 0f, 0.1f, 0.1f*33f/9f, 10f, 1);
        enemyShot.setDirection(GameMoveableElement.Direction.DOWN);
        enemyShooter.addShotCharacteristics(new ShotCharacteristics(enemyShot, enemyAttributes.getWidth() / 2 - enemyShot.getWidth() / 2, - enemyAttributes.getHeight()));

        enemyAttributes.setShooter(enemyShooter);

        SimpleEnemyController enemyController = new SimpleEnemyController();
        world.addEnemyController(enemyController);

        PeriodicEnemyGenerator enemyGenerator = new PeriodicEnemyGenerator(enemyAttributes, 1f);
        enemyGenerator.setEnemyController(enemyController);
        world.addEnemyGenerator(enemyGenerator);

        worldRenderer = new WorldRenderer(world);

        fpsLogger = new FPSLogger();
        showFPS = false;

        mainThemeMusic = SoundFactory.getInstance().getMainThemeMusic();
    }

    public void resetGame() {
        world = new World(15f, 20f);

        mainThemeMusic.dispose();
        mainThemeMusic.play();
        mainThemeMusic.setLooping(true);

        Enemy enemyAttributes = new Enemy(0f, 0f, 1f, 50f/98f, 7f);
        enemyAttributes.setIsMoving(true);
        enemyAttributes.setDirection(GameMoveableElement.Direction.DOWN);

        EnemyShooterWithCooldown enemyShooter = new EnemyShooterWithCooldown(world, 1f);
        Shot enemyShot = new Shot(0f, 0f, 0.1f, 0.5f, 10f, 1);
        enemyShot.setDirection(GameMoveableElement.Direction.DOWN);
        enemyShooter.addShotCharacteristics(new ShotCharacteristics(enemyShot, enemyAttributes.getWidth() / 2 - enemyShot.getWidth() / 2, -enemyAttributes.getHeight()));

        enemyAttributes.setShooter(enemyShooter);

        SimpleEnemyController enemyController = new SimpleEnemyController();
        world.addEnemyController(enemyController);

        PeriodicEnemyGenerator enemyGenerator = new PeriodicEnemyGenerator(enemyAttributes, 1f);
        enemyGenerator.setEnemyController(enemyController);
        world.addEnemyGenerator(enemyGenerator);

        worldRenderer.setWorld(world);
        game.getGameListener().setWorld(world);
    }

    @Override
    public void render(float delta) {
        // Couleur de fond de l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // On verifie si une touche est maintenue
        game.getGameListener().checkHeldKey();

        world.update(delta);    // On met a jour le monde

        worldRenderer.render(); // et on l'affiche

        Gdx.gl.glDisable(GL20.GL_BLEND);

        // Si le jeu est termine, on le reinitialise
        if (world.getEndGame())
            resetGame();

        // On affiche les fps si necessaire
        if (showFPS)
            fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {
        worldRenderer.resize(width, height);
    }

    @Override
    public void dispose() {
        worldRenderer = null;
        world = null;
        fpsLogger = null;
    }

    public World getWorld() { return world; }

    public void escape() {
        game.changeScreen(SpaceInvadersGame.ScreenEnum.MENU_SCREEN);
    }

    public void pauseMusic() {
        mainThemeMusic.pause();
    }

    public void playMusic() {
        mainThemeMusic.play();
    }
}
