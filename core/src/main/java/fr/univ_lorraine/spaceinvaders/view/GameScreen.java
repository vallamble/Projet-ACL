package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

import java.util.ArrayList;

import fr.univ_lorraine.spaceinvaders.SpaceInvadersGame;
import fr.univ_lorraine.spaceinvaders.controller.GameListener;
import fr.univ_lorraine.spaceinvaders.model.Enemy;
import fr.univ_lorraine.spaceinvaders.model.GameMoveableElement;
import fr.univ_lorraine.spaceinvaders.model.PeriodicEnemyGenerator;
import fr.univ_lorraine.spaceinvaders.model.World;

/**
 * Ecran principale du jeu.
 */
public class GameScreen extends AbstractScreen {

    private World world;

    private WorldRenderer worldRenderer;

    private GameListener gameListener;

    /**
     * Permet d'afficher les fps.
     */
    private FPSLogger fpsLogger;

    /**
     * Active/desactive le fpsLogger.
     */
    private boolean showFPS;

    public GameScreen(SpaceInvadersGame g) {
        super(g);

        world = new World(15f, 20f);
        Enemy enemyAttributes = new Enemy(0f, 0f, 1f, 1.5f, 10f);
        enemyAttributes.setIsMoving(true);
        enemyAttributes.setDirection(GameMoveableElement.Direction.DOWN);
        world.addEnemyGenerator(new PeriodicEnemyGenerator(enemyAttributes, 1f));

        worldRenderer = new WorldRenderer(world);
        gameListener = new GameListener(world);
        Gdx.input.setInputProcessor(gameListener);

        fpsLogger = new FPSLogger();
        showFPS = false;
    }

    public void resetGame() {
        world = new World(15f, 20f);
        Enemy enemyAttributes = new Enemy(0f, 0f, 1f, 1.5f, 10f);
        enemyAttributes.setIsMoving(true);
        enemyAttributes.setDirection(GameMoveableElement.Direction.DOWN);
        world.addEnemyGenerator(new PeriodicEnemyGenerator(enemyAttributes, 1f));

        worldRenderer.setWorld(world);
        gameListener.setWorld(world);
    }

    @Override
    public void render(float delta) {
        // Couleur de fond de l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // On verifie si une touche est maintenue
        gameListener.checkHeldKey();

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
        gameListener = null;
        fpsLogger = null;
    }
}
