package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fr.univ_lorraine.spaceinvaders.SpaceInvadersGame;

/**
 * Ecran du menu du jeu.
 */
public class MenuScreen extends AbstractScreen {

    private MenuChoice choice = MenuChoice.LANCER_PARTIE;

    public enum MenuChoice { LANCER_PARTIE, MEILLEURES_SCORES, QUITTER }

    private float height = 20f, width = 15f;

    private SpriteBatch spriteBatch;

    private OrthographicCamera camera;

    private Viewport viewport;

    /**
     * Pixels par unite.
     */
    private float ppux, ppuy;

    public MenuScreen(SpaceInvadersGame g) {
        super(g);
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        ppux = 95f;
        ppuy = 95f;
        viewport = new FitViewport(width * ppux, height * ppuy, camera);
        viewport.apply();
        camera.position.set(width * ppux / 2, height * ppuy / 2, 0);
    }


    @Override
    public void render(float delta) {
        // Couleur de fond de l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Affiche le menu d'accueil
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        drawBackground();
        drawMenu();
        spriteBatch.end();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void drawMenu() {
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

    public void setChoice(int num) {
        if(choice == MenuChoice.LANCER_PARTIE) {
            if (num == 1)
                choice = MenuChoice.QUITTER;
            if (num == -1)
                choice = MenuChoice.MEILLEURES_SCORES;
        }
        else if(choice == MenuChoice.MEILLEURES_SCORES) {
            if (num == 1)
                choice = MenuChoice.LANCER_PARTIE;
            if (num == -1)
                choice = MenuChoice.QUITTER;
        }
        else if(choice == MenuChoice.QUITTER) {
            if(num == 1)
                choice = MenuChoice.MEILLEURES_SCORES;
            if(num == -1)
                choice = MenuChoice.LANCER_PARTIE;
        }
    }

    public void selectChoice() {
        if(choice == MenuChoice.LANCER_PARTIE)
            game.changeScreen(SpaceInvadersGame.ScreenEnum.GAME_SCREEN);
        if(choice == MenuChoice.QUITTER)
            Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
