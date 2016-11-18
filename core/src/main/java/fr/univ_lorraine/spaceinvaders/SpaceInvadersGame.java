package fr.univ_lorraine.spaceinvaders;

import com.badlogic.gdx.Game;
import fr.univ_lorraine.spaceinvaders.view.GameScreen;

/**
 * Classe principale du jeu gerant les differents ecrans.
 */
public class SpaceInvadersGame extends Game {

	public enum ScreenEnum { GAME_SCREEN }

	GameScreen gameScreen;

	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		setScreen(gameScreen);
	}

    /**
     * Permet de changer d'ecran.
     * @param screen L'ecran a afficher.
     */
    public void changeScreen(ScreenEnum screen) {
        switch(screen) {
            case GAME_SCREEN :
                setScreen(gameScreen);
                break;
        }
    }

	@Override
	public void dispose () {
		gameScreen = null;
	}
}
