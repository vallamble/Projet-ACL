package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureFactory {

    private static TextureFactory instance = new TextureFactory();

    private TextureRegion background;

    private TextureRegion ship;

    private TextureRegion smallEnemyShip;

    private TextureFactory() {
        background = new TextureRegion(new Texture(Gdx.files.internal("images/stars_background.jpg")));
        ship = new TextureRegion(new Texture(Gdx.files.internal("images/spaceship.png")));
        smallEnemyShip = new TextureRegion(new Texture(Gdx.files.internal("images/small_enemy_ship.png")));
        smallEnemyShip.flip(false, true);
    }

    public static TextureFactory getInstance() {
        return instance;
    }

    public TextureRegion getBackground() {
        return background;
    }

    public TextureRegion getPlayer() {
        return ship;
    }

    public TextureRegion getSmallEnemyShip() {
        return smallEnemyShip;
    }
}
