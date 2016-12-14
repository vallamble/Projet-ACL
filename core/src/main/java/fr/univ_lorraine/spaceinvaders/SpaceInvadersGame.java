package fr.univ_lorraine.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import fr.univ_lorraine.spaceinvaders.view.GameScreen;
import fr.univ_lorraine.spaceinvaders.controller.GameListener;
import fr.univ_lorraine.spaceinvaders.view.MenuScreen;
import fr.univ_lorraine.spaceinvaders.controller.MenuListener;

/**
 * Classe principale du jeu gerant les differents ecrans.
 */
public class SpaceInvadersGame extends Game {

	public enum ScreenEnum { GAME_SCREEN, MENU_SCREEN }

	private GameScreen gameScreen;

	private GameListener gameListener;

	private MenuScreen menuScreen;

	private MenuListener menuListener;


	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		gameListener = new GameListener(gameScreen);
		menuScreen = new MenuScreen(this);
		menuListener = new MenuListener(menuScreen);
		changeScreen(ScreenEnum.MENU_SCREEN);
	}

    /**
     * Permet de changer d'ecran.
     * @param screen L'ecran a afficher.
     */
    public void changeScreen(ScreenEnum screen) {
        switch(screen) {
            case GAME_SCREEN :
				Gdx.input.setInputProcessor(gameListener);
				setScreen(gameScreen);
				gameScreen.resetGame();
				break;
			case MENU_SCREEN :
				Gdx.input.setInputProcessor(menuListener);
				setScreen(menuScreen);
				break;
			default:
				setScreen(menuScreen);
        }
    }

	public GameListener getGameListener() { return gameListener; }

	@Override
	public void dispose () {
		gameScreen = null;
	}
}
