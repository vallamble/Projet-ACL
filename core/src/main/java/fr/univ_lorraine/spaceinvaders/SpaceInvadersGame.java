package fr.univ_lorraine.spaceinvaders;

import com.badlogic.gdx.Game;
import fr.univ_lorraine.spaceinvaders.view.GameScreen;

/**
 * Classe principale du jeu gerant les differents ecrans.
 */
public class SpaceInvadersGame extends Game {

	GameScreen gameScreen;
	
	@Override
	public void create () {
		gameScreen = new GameScreen();
	}

	@Override
	public void render () {
        setScreen(gameScreen);
        super.render();
	}
	
	@Override
	public void dispose () {
		gameScreen = null;
	}
}
