package fr.univ_lorraine.spaceinvaders;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

import fr.univ_lorraine.spaceinvaders.controller.GameOverListener;
import fr.univ_lorraine.spaceinvaders.view.GameOverScreen;
import fr.univ_lorraine.spaceinvaders.view.GameScreen;
import fr.univ_lorraine.spaceinvaders.controller.GameListener;
import fr.univ_lorraine.spaceinvaders.view.MenuScreen;
import fr.univ_lorraine.spaceinvaders.controller.MenuListener;

/**
 * Classe principale du jeu gerant les differents ecrans.
 */
public class SpaceInvadersGame extends Game {

	public enum ScreenEnum { GAME_SCREEN, MENU_SCREEN,MEILLEURES_SCORES,GAME_OVER}

	private GameScreen gameScreen;

	private GameListener gameListener;

	private MenuScreen menuScreen;

	private MenuListener menuListener;

	private GameOverScreen gameOverScreen;

	private GameOverListener gameOverListener;


	@Override
	public void create () {
		gameScreen = new GameScreen(this);
		gameListener = new GameListener(gameScreen);
		menuScreen = new MenuScreen(this);
		menuListener = new MenuListener(menuScreen);
		gameOverScreen = new GameOverScreen(this);
		gameOverListener = new GameOverListener(gameOverScreen);
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
			case MEILLEURES_SCORES :
				Gdx.input.setInputProcessor(gameOverListener);
				setScreen(gameOverScreen);
				break;
			case GAME_OVER :
				Gdx.input.setInputProcessor(gameOverListener);
				setScreen(gameOverScreen);
				break;
			default:
				setScreen(menuScreen);
        }
    }

	public void setGameOverScore(int score) {
		gameOverScreen.setScore(score);
	}

	public GameListener getGameListener() { return gameListener; }

	@Override
	public void dispose () {
		gameScreen = null;
	}
}
