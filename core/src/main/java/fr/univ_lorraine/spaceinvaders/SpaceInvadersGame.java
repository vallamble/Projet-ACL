package fr.univ_lorraine.spaceinvaders;

import com.badlogic.gdx.Game;
import fr.univ_lorraine.spaceinvaders.view.GameScreen;
import fr.univ_lorraine.spaceinvaders.view.MenuScreen;

/**
 * Classe principale du jeu gerant les differents ecrans.
 */
public class SpaceInvadersGame extends Game {

	public enum ScreenEnum { GAME_SCREEN,MENU_SCREEN }

	GameScreen gameScreen;
	MenuScreen menuScreen;


	@Override
	public void create () {
		menuScreen = new MenuScreen(this);
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
			case MENU_SCREEN :
				setScreen(menuScreen);
				break;
			default:
				setScreen(menuScreen);
        }
    }

	@Override
	public void dispose () {
		gameScreen = null;
	}
}
