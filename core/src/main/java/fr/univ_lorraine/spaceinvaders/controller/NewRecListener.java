package fr.univ_lorraine.spaceinvaders.controller;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import fr.univ_lorraine.spaceinvaders.view.NewRecScreen;

/**
 * Created by denis49u on 08/12/16.
 */
public class NewRecListener implements InputProcessor {

    NewRecScreen newRec;

    public NewRecListener(NewRecScreen newRecScreen) {
        newRec = newRecScreen;
    }


    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.UP:
                //newRec.setChoice(1);
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
