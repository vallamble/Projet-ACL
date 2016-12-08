package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

import fr.univ_lorraine.spaceinvaders.SpaceInvadersGame;
import fr.univ_lorraine.spaceinvaders.controller.GameListener;
import fr.univ_lorraine.spaceinvaders.model.GameMoveableElement;

/**
 * Ecran du menu du jeu.
 */
public class MenuScreen extends AbstractScreen {

    private MenuRenderer menuRenderer;

    //private MenuListener menuListener;

    /**
     * Permet d'afficher les fps.
     */
    private FPSLogger fpsLogger;

    /**
     * Active/desactive le fpsLogger.
     */
    private boolean showFPS;

    public MenuScreen(SpaceInvadersGame g) {
        super(g);

        menuRenderer = new MenuRenderer();
        fpsLogger = new FPSLogger();
        showFPS = false;
    }


    @Override
    public void render(float delta) {
        // Couleur de fond de l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // On verifie si une touche est maintenue
        //menuListener.checkHeldKey();

        menuRenderer.render(); // et on l'affiche

        Gdx.gl.glDisable(GL20.GL_BLEND);


        // On affiche les fps si necessaire
        if (showFPS)
            fpsLogger.log();
    }

    @Override
    public void resize(int width, int height) {
        menuRenderer.resize(width, height);
    }

    @Override
    public void dispose() {
        menuRenderer = null;
        //menuListener = null;
        fpsLogger = null;
    }
}
