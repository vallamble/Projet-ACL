package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

import fr.univ_lorraine.spaceinvaders.SpaceInvadersGame;

/**
 * Ecran du menu du jeu.
 */
public class GameOverScreen extends AbstractScreen {

    private GameOverRenderer gameOverRenderer;



    /**
     * Active/desactive le fpsLogger.
     */


    public GameOverScreen(SpaceInvadersGame g) {
        super(g);
        gameOverRenderer = new GameOverRenderer(this);
    }


    @Override
    public void render(float delta) {
        // Couleur de fond de l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // On verifie si une touche est maintenue
        //menuListener.checkHeldKey();

        gameOverRenderer.render(); // et on l'affiche

        Gdx.gl.glDisable(GL20.GL_BLEND);
    }




    @Override
    public void resize(int width, int height) {
        gameOverRenderer.resize(width, height);
    }

}
