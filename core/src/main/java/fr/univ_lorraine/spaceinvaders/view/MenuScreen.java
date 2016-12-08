package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;

import fr.univ_lorraine.spaceinvaders.SpaceInvadersGame;

/**
 * Ecran du menu du jeu.
 */
public class MenuScreen extends AbstractScreen {

    private MenuRenderer menuRenderer;

    private MenuChoice choice = MenuChoice.LANCER_PARTIE;

    public enum MenuChoice { LANCER_PARTIE, MEILLEURES_SCORES, QUITTER };

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

        menuRenderer = new MenuRenderer(this);
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

    public MenuChoice getChoice() { return choice; }

    public void selectChoice() {
        if(choice == MenuChoice.LANCER_PARTIE)
            game.changeScreen(SpaceInvadersGame.ScreenEnum.GAME_SCREEN);
        if(choice == MenuChoice.QUITTER)
            Gdx.app.exit();
    }

    @Override
    public void resize(int width, int height) {
        menuRenderer.resize(width, height);
    }

}
