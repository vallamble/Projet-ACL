package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by val on 08/12/2016.
 */

public class MenuRenderer {

    private float height = 20f, width = 15f;

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

    public MenuRenderer() {
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        camera = new OrthographicCamera();
        ppux = 95f;
        ppuy = 95f;
        viewport = new FitViewport(width * ppux, height * ppuy, camera);
        viewport.apply();
        camera.position.set(width * ppux / 2, height * ppuy / 2, 0);
        showBoundingBox = false;
    }

    /**
     * Affiche le menu d'accueil.
     * Est appelee par menuScreen.render(delta).
     */
    public void render() {
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        drawBackground();
        drawMenu();
        spriteBatch.end();

        if (showBoundingBox) {
            shapeRenderer.setProjectionMatrix(camera.combined);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            shapeRenderer.end();
        }
    }

    private void drawMenu() {
        spriteBatch.draw(TextureFactory.getInstance().getMenuRectangle(), 2 * ppux, 2 * ppuy, width * ppux / 19 * 14, height * ppuy / 8);
        spriteBatch.draw(TextureFactory.getInstance().getMenuRectangle(), 2 * ppux, 5 * ppuy, width * ppux / 19 * 14, height * ppuy / 8);
        spriteBatch.draw(TextureFactory.getInstance().getMenuRectangle(), 2 * ppux, 8 * ppuy, width * ppux / 19 * 14, height * ppuy / 8);
        spriteBatch.draw(TextureFactory.getInstance().getMenuRectangle(), 2 * ppux, 11 * ppuy, width * ppux / 19 * 14, height * ppuy / 8);
    }

    private void drawBackground() {
        spriteBatch.draw(TextureFactory.getInstance().getMenuBackground(), 0 * ppux, 0 * ppuy, width * ppux, height * ppuy);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
