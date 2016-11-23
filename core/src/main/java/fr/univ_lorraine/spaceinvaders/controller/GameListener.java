package fr.univ_lorraine.spaceinvaders.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import fr.univ_lorraine.spaceinvaders.model.World;

public class GameListener implements InputProcessor {

    private World world;

    public GameListener(World w) {
        world = w;
    }

    /**
     * On verifie si certaines touches du clavier sont maintenues.
     * Necessaire car la methode keyDown n'est appelee qu'une seule fois meme lors du maintien d'une touche.
     */
    public void checkHeldKey() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT))
            world.getPlayer().turnLeft();
        else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT))
            world.getPlayer().turnRight();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.LEFT:
                world.getPlayer().turnLeft();
                break;
            case Input.Keys.RIGHT:
                world.getPlayer().turnRight();
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
