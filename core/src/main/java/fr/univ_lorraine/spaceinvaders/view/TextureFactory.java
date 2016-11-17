package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by alexis on 15/11/2016.
 */
public class TextureFactory {

    private static TextureFactory instance = new TextureFactory();

    private Texture background;

    private Texture ship;

    private TextureFactory() {
        background = new Texture(Gdx.files.internal("images/stars_background.jpg"));
        ship = new Texture(Gdx.files.internal("images/spaceship.png"));
    }

    public static TextureFactory getInstance() {
        return instance;
    }

    public Texture getBackground() {
        return background;
    }

    public Texture getShip() {
        return ship;
    }
}
