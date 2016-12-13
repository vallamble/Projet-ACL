package fr.univ_lorraine.spaceinvaders.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import fr.univ_lorraine.spaceinvaders.view.GameScreen;

import fr.univ_lorraine.spaceinvaders.model.World;

public class GameListener implements InputProcessor {

    private World world;
    private GameScreen gameScreen;

    public GameListener(GameScreen g) {
        gameScreen = g;
        world = gameScreen.getWorld();
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * On verifie si certaines touches du clavier sont maintenues.
     * Necessaire car la methode keyDown n'est appelee qu'une seule fois meme lors du maintien d'une touche.
     */
    public void checkHeldKey() {
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE))
            world.getPlayer().shoot();
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
            case Input.Keys.SPACE:
                world.getPlayer().shoot();
                break;
            case Input.Keys.P:
                world.pause();
                if(world.getPause())
                    gameScreen.pauseMusic();
                else
                    gameScreen.playMusic();
                break;
            case Input.Keys.ESCAPE:
                if(world.getPause()) {
                    gameScreen.escape();
                    world.pause();
                }
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
