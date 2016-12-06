package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureFactory {

    private static TextureFactory instance = new TextureFactory();

    private TextureRegion background;

    private TextureRegion player;

    private TextureRegion enemyShip;

    private TextureRegion laserGreen;

    private TextureRegion laserRed;

    private TextureFactory() {
        background = new TextureRegion(new Texture(Gdx.files.internal("images/starBackground.png")));
        player = new TextureRegion(new Texture(Gdx.files.internal("images/player.png")));
        enemyShip = new TextureRegion(new Texture(Gdx.files.internal("images/enemyShip.png")));
        enemyShip.flip(false, true);
        laserGreen = new TextureRegion(new Texture(Gdx.files.internal("images/laserGreen.png")));
        laserRed = new TextureRegion(new Texture(Gdx.files.internal("images/laserRed.png")));
    }

    public static TextureFactory getInstance() {
        return instance;
    }

    public TextureRegion getBackground() {
        return background;
    }

    public TextureRegion getPlayer() {
        return player;
    }

    public TextureRegion getEnemyShip() {
        return enemyShip;
    }

    public TextureRegion getLaserGreen() {
        return laserGreen;
    }

    public TextureRegion getLaserRed() {
        return laserRed;
    }
}
