package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

import fr.univ_lorraine.spaceinvaders.model.Enemy;
import fr.univ_lorraine.spaceinvaders.model.GameElement;
import fr.univ_lorraine.spaceinvaders.model.World;

/**
 * Classe qui gere l'affichage du monde.
 */
public class WorldRenderer {

    private World world;

    private float stateTime;

    private SpriteBatch spriteBatch;

    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;

    private Viewport viewport;

    /**
     * Pixels par unite.
     */
    private float ppux, ppuy;

    /**
     * Active/desactive l'affichage des boundingBox.
     */
    private boolean showBoundingBox;

    public WorldRenderer(World w) {
        world = w;
        stateTime = 0f;
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        ppux = 95f;
        ppuy = 95f;
        viewport = new FitViewport(world.getWidth() * ppux, world.getHeight() * ppuy, camera);
        viewport.apply();
        camera.position.set(world.getWidth() * ppux / 2, world.getHeight() * ppuy / 2, 0);
        showBoundingBox = false;
    }

    /**
     * Affiche le monde.
     * Est appelee par gameScreen.render(delta).
     */
    public void render(float delta) {
        stateTime += delta;
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        drawBackground();
        drawPlayer();
        drawEnemies();
        spriteBatch.end();
        if (showBoundingBox) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            drawPlayerBoundingBox();
            drawEnemiesBoundingBox();
            shapeRenderer.end();
        }
    }

    private void drawGameElement(TextureRegion texture, GameElement gameElement) {
        spriteBatch.draw(texture, gameElement.getPosition().x * ppux, gameElement.getPosition().y * ppuy, gameElement.getWidth() * ppux, gameElement.getHeight() * ppuy);
    }

    private void drawGameElementBoundingBox(GameElement gameElement) {
        shapeRenderer.rect(gameElement.getBoundingBox().x * ppux, gameElement.getBoundingBox().y * ppuy, gameElement.getBoundingBox().getWidth() * ppux, gameElement.getBoundingBox().getHeight() * ppuy);
    }

    private void drawBackground() {
        spriteBatch.draw(TextureFactory.getInstance().getBackground(), 0 * ppux, 0 * ppuy, world.getWidth() * ppux, world.getHeight() * ppuy);
    }

    private void drawPlayer() {
        drawGameElement(TextureFactory.getInstance().getPlayer(), world.getPlayer());
    }

    private void drawEnemies() {
        List<Enemy> enemies = world.getEnemies();
        for (Enemy enemy : enemies) {
            drawGameElement(TextureFactory.getInstance().getSmallEnemyShip(), enemy);
        }
    }

    private void drawPlayerBoundingBox() {
        drawGameElementBoundingBox(world.getPlayer());
    }

    private void drawEnemiesBoundingBox() {
        for (Enemy enemy : world.getEnemies())
            drawGameElementBoundingBox(enemy);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
