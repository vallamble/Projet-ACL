package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.ScreenAdapter;

import fr.univ_lorraine.spaceinvaders.SpaceInvadersGame;

/**
 * Classe abstraite dont tous les ecrans du jeu heritent.
 */
public abstract class AbstractScreen extends ScreenAdapter {

    /**
     * La classe principale du jeu.
     * Permet de changer d'ecran en appelant changeScreen de SpaceInvadersGame.
     */
    protected SpaceInvadersGame game;
    protected int score;

    public AbstractScreen(SpaceInvadersGame g) {
        game = g;
    }

    public AbstractScreen(SpaceInvadersGame g, int s) {
        game = g;
        score = s;
    }
}
