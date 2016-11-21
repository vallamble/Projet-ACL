package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fr.univ_lorraine.spaceinvaders.model.Player;
import fr.univ_lorraine.spaceinvaders.model.World;

/**
 * Classe qui gere l'affichage du monde.
 */
public class WorldRenderer {

    private World world;

    private SpriteBatch spriteBatch;

    private OrthographicCamera camera;

    private Viewport viewport;

    // Pixels par unite
    private float ppux, ppuy;

    public WorldRenderer(World w) {
        world = w;
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        ppux = 95f;
        ppuy = 95f;
        viewport = new FitViewport(world.getWidth() * ppux, world.getHeight() * ppuy, camera);
        viewport.apply();
        camera.position.set(world.getWidth() * ppux / 2, world.getHeight() * ppuy / 2, 0);
    }

    /**
     * Affiche le monde.
     * Est appelee par gameScreen.render(delta).
     */
    public void render(float delta) {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        drawBackground();
        drawShip();
        spriteBatch.end();
    }

    private void drawBackground() {
        spriteBatch.draw(TextureFactory.getInstance().getBackground(), 0 * ppux, 0 * ppuy, world.getWidth() * ppux, world.getHeight() * ppuy);
    }

    private void drawShip() {
        Player player = world.getPlayer();
        spriteBatch.draw(TextureFactory.getInstance().getShip(), player.getPosition().x * ppux, player.getPosition().y * ppuy, player.getWidth() * ppux, player.getHeight() * ppuy);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }
}
