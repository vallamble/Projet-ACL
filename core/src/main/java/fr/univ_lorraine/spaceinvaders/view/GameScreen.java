package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

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

    private SpriteBatch spriteBatch;

    private ShapeRenderer shapeRenderer;

    private BitmapFont fontBatch;

    private GlyphLayout glyphLayout;

    private String goToMainMenuMessage;

    private OrthographicCamera camera;

    private Viewport viewport;

    private World world;

    private WorldRenderer worldRenderer;

    /**
     * Pixels par unite.
     */
    private float ppux, ppuy;

    /**
     * Décalages entre le monde concret et le reste de l'écran.
     */
    private float worldTopPadding, worldBottomPadding, worldLeftPadding, worldRightPadding;

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

    /**
     * Booléen indiquant si le jeu est en pause.
     */
    private boolean pause;

    public GameScreen(SpaceInvadersGame g) {
        super(g);

        // Définition du monde
        world = new World(15f, 20f);

        Enemy enemyAttributes = new Enemy(0f, 0f, 1f, 50f/98f, 7f);
        enemyAttributes.setIsMoving(true);
        enemyAttributes.setDirection(GameMoveableElement.Direction.DOWN);

        EnemyShooterWithCooldown enemyShooter = new EnemyShooterWithCooldown(world, 1f);
        Shot enemyShot = new Shot(0f, 0f, 0.1f, 0.1f*33f/9f, 10f, 1);
        enemyShot.setDirection(GameMoveableElement.Direction.DOWN);
        enemyShooter.addShotCharacteristics(new ShotCharacteristics(enemyShot, enemyAttributes.getWidth() / 2 - enemyShot.getWidth() / 2, -enemyAttributes.getHeight()));

        enemyAttributes.setShooter(enemyShooter);

        SimpleEnemyController enemyController = new SimpleEnemyController();
        world.addEnemyController(enemyController);

        PeriodicEnemyGenerator enemyGenerator = new PeriodicEnemyGenerator(enemyAttributes, 1f);
        enemyGenerator.setEnemyController(enemyController);
        world.addEnemyGenerator(enemyGenerator);

        // Affichage
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        fontBatch = new BitmapFont(Gdx.files.internal("fonts/test.fnt"));
        glyphLayout = new GlyphLayout();
        camera = new OrthographicCamera();
        ppux = 95f;
        ppuy = 95f;

        worldTopPadding = 1f;
        worldBottomPadding = 1f;
        worldLeftPadding = 0f;
        worldRightPadding = 0f;

        viewport = new FitViewport((world.getWidth() + worldLeftPadding + worldRightPadding) * ppux, (world.getHeight() + worldTopPadding + worldBottomPadding) * ppuy, camera);
        viewport.apply();
        camera.position.set((world.getWidth() + worldLeftPadding + worldRightPadding) * ppuy / 2, (world.getHeight() + worldTopPadding + worldBottomPadding) * ppuy / 2, 0);

        worldRenderer = new WorldRenderer(world, spriteBatch, shapeRenderer, fontBatch, ppux, ppuy, worldTopPadding, worldBottomPadding, worldLeftPadding, worldRightPadding);

        fpsLogger = new FPSLogger();
        showFPS = false;

        mainThemeMusic = SoundFactory.getInstance().getMainThemeMusic();

        pause = false;

        goToMainMenuMessage = "Press Escape to return to main menu";
    }

    public void resetGame() {
        world = new World(15f, 20f);
        pause = false;
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

        pause = false;
    }

    @Override
    public void render(float delta) {
        // Couleur de fond de l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!pause) {               // Si le jeu n'est pas en pause,
            // on verifie si une touche est maintenue
            game.getGameListener().checkHeldKey();
            world.update(delta);    // et on met a jour le monde
            mainThemeMusic.play();
        }

        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);
        worldRenderer.render(); // et on l'affiche

        if (pause) {            // Si le jeu est en pause
            spriteBatch.begin();// on affiche l'icone de pause
            float boutonPauseWidth = 3 * ppux;
            float boutonPauseHeight = 3 * ppuy;
            mainThemeMusic.pause();
            spriteBatch.draw(TextureFactory.getInstance().getBoutonPause(), viewport.getWorldWidth()/2 - boutonPauseWidth/2, viewport.getWorldHeight()/2 - boutonPauseHeight/2, boutonPauseWidth, boutonPauseHeight);
            fontBatch.getData().setScale(1f);
            glyphLayout.setText(fontBatch, goToMainMenuMessage);
            fontBatch.draw(spriteBatch, goToMainMenuMessage, viewport.getWorldWidth()/2 - glyphLayout.width/2, viewport.getWorldHeight()/2 - glyphLayout.height/2 - boutonPauseHeight/2);
            spriteBatch.end();
        }

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
        viewport.update(width, height);
    }

    @Override
    public void dispose() {
        spriteBatch = null;
        shapeRenderer = null;
        fontBatch = null;
        fpsLogger = null;
        camera = null;
        viewport = null;
        world = null;
        worldRenderer = null;
        fpsLogger = null;
    }

    public World getWorld() { return world; }

    public void goToMainMenu() {
        game.changeScreen(SpaceInvadersGame.ScreenEnum.MENU_SCREEN);
    }

    /*public void goToMainMenu() {
        game.changeScreen(SpaceInvadersGame.ScreenEnum.MENU_SCREEN);
    }*/

    public boolean isGamePaused() {
        return pause;
    }

    public void pauseGame() {
        pause = true;
        mainThemeMusic.play();
    }

    public void resumeGame() {
        pause = false;
        mainThemeMusic.pause();
    }
}
