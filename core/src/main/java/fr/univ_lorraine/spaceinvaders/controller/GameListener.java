package fr.univ_lorraine.spaceinvaders.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import fr.univ_lorraine.spaceinvaders.model.Player;

public class GameListener implements InputProcessor {

    private Player player;

    public GameListener(Player s) {
        player = s;
    }

    /**
     * On verifie si certaines touches du clavier sont maintenues.
     * Necessaire car la methode keyDown n'est appele qu'une seule fois meme lors du maintien d'une touche.
     */
    public void checkHeldKey() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            player.turnLeft();
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            player.turnRight();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                player.turnLeft();
                break;
            case Input.Keys.RIGHT:
                player.turnRight();
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
