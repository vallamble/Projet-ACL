package fr.univ_lorraine.spaceinvaders.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import fr.univ_lorraine.spaceinvaders.view.GameOverScreen;


/**
 * Created by denis49u on 08/12/16.
 */
public class GameOverListener implements InputProcessor {

    GameOverScreen gameOver;

    public GameOverListener(GameOverScreen gameOverScreen) {

        gameOver = gameOverScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                gameOver.setChoice(1);
                break;
            case Input.Keys.DOWN:
                gameOver.setChoice(-1);
                break;
            case Input.Keys.ENTER:
                gameOver.selectChoice();
                break;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    // Mobile
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }
    // Fin mobile

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
