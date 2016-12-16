package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import fr.univ_lorraine.spaceinvaders.SpaceInvadersGame;

/**
 * Ecran du menu du jeu.
 */
public class NewRecScreen extends AbstractScreen {


    private MenuChoice choice = MenuChoice.LANCER_PARTIE;

    public enum MenuChoice { LANCER_PARTIE, MEILLEURES_SCORES, QUITTER };

    /**
     * Active/desactive le fpsLogger.
     */
    private boolean showFPS;

    public NewRecScreen(SpaceInvadersGame g) {
        super(g);
        showFPS = false;
    }


    @Override
    public void render(float delta) {
        // Couleur de fond de l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // On verifie si une touche est maintenue
        //menuListener.checkHeldKey();

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

}
