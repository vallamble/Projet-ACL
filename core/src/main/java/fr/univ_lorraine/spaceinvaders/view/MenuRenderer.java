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

    private MenuScreen menu;

    /**
     * Pixels par unite.
     */
    private float ppux, ppuy;

    /**
     * Active/desactive l'affichage des boundingBox.
     */
    private boolean showBoundingBox;

    public MenuRenderer(MenuScreen m) {
        menu = m;
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
        MenuScreen.MenuChoice choice = menu.getChoice();
        if(choice == MenuScreen.MenuChoice.QUITTER)
            spriteBatch.draw(TextureFactory.getInstance().getMenuBoutonQuitterSelect(), 2 * ppux, 3 * ppuy, width * ppux / 19 * 14, height * ppuy / 10);
        else
            spriteBatch.draw(TextureFactory.getInstance().getMenuBoutonQuitter(), 2 * ppux, 3 * ppuy, width * ppux / 19 * 14, height * ppuy / 10);
        if(choice == MenuScreen.MenuChoice.MEILLEURES_SCORES)
            spriteBatch.draw(TextureFactory.getInstance().getMenuBoutonScoresSelect(), 2 * ppux, 7 * ppuy, width * ppux / 19 * 14, height * ppuy / 10);
        else
            spriteBatch.draw(TextureFactory.getInstance().getMenuBoutonScores(), 2 * ppux, 7 * ppuy, width * ppux / 19 * 14, height * ppuy / 10);
        if(choice == MenuScreen.MenuChoice.LANCER_PARTIE)
            spriteBatch.draw(TextureFactory.getInstance().getMenuBoutonLancerSelect(), 2 * ppux, 11 * ppuy, width * ppux / 19 * 14, height * ppuy / 10);
        else
            spriteBatch.draw(TextureFactory.getInstance().getMenuBoutonLancer(), 2 * ppux, 11 * ppuy, width * ppux / 19 * 14, height * ppuy / 10);
    }

    private void drawBackground() {
        spriteBatch.draw(TextureFactory.getInstance().getMenuBackground(), 0 * ppux, 0 * ppuy, width * ppux, height * ppuy);
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
