package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fr.univ_lorraine.spaceinvaders.model.Enemy;
import fr.univ_lorraine.spaceinvaders.model.GameElement;
import fr.univ_lorraine.spaceinvaders.model.Shot;
import fr.univ_lorraine.spaceinvaders.model.World;

/**
 * Classe qui gere l'affichage du monde.
 */
public class WorldRenderer {

    private World world;

    private SpriteBatch spriteBatch;

    private ShapeRenderer shapeRenderer;

    private OrthographicCamera camera;

    private Viewport viewport;

    private PlayerDrawer playerDrawer;

    private EnemyDrawer enemyDrawer;

    private ShotDrawer shotDrawer;

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
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        ppux = 95f;
        ppuy = 95f;
        viewport = new FitViewport(world.getWidth() * ppux, world.getHeight() * ppuy, camera);
        viewport.apply();
        camera.position.set(world.getWidth() * ppux / 2, world.getHeight() * ppuy / 2, 0);
        showBoundingBox = false;

        playerDrawer = new PlayerDrawer(spriteBatch, shapeRenderer, ppux, ppuy);
        enemyDrawer = new EnemyDrawer(spriteBatch, shapeRenderer, ppux, ppuy);
        shotDrawer = new ShotDrawer(spriteBatch, shapeRenderer, ppux, ppuy);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Affiche le monde.
     * Est appelee par gameScreen.render(delta).
     */
    public void render() {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        drawBackground();
        playerDrawer.draw(world.getPlayer());
        for (Enemy enemy : world.getEnemies())
                enemyDrawer.draw(enemy);
        for (Shot shot : world.getPlayerShots())
                shotDrawer.draw(shot);
        spriteBatch.end();

        if (showBoundingBox) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            playerDrawer.drawBoundingBox(world.getPlayer());
            for (Enemy enemy : world.getEnemies())
                enemyDrawer.drawBoundingBox(enemy);
            for (Shot shot : world.getPlayerShots())
                shotDrawer.drawBoundingBox(shot);
            shapeRenderer.end();
        }
    }

    private void drawBackground() {
        spriteBatch.draw(TextureFactory.getInstance().getBackground(), 0 * ppux, 0 * ppuy, world.getWidth() * ppux, world.getHeight() * ppuy);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
