package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureFactory {

    private static TextureFactory instance = new TextureFactory();

    private TextureRegion background;

    private TextureRegion menuBackground;

    private TextureRegion menuRectangle;

    private TextureRegion player;

    private TextureRegion enemyShip;

    private TextureRegion laserGreen;

    private TextureRegion laserRed;

    private TextureRegion fullHeart;

    private TextureRegion midHeart;

    private TextureRegion emptyHeart;

    private TextureFactory() {
        background = new TextureRegion(new Texture(Gdx.files.internal("images/starBackground.png")));
        menuBackground = new TextureRegion(new Texture(Gdx.files.internal("images/menuBackground.png")));
        menuRectangle = new TextureRegion(new Texture(Gdx.files.internal("images/rectangleMenu.png")));
        player = new TextureRegion(new Texture(Gdx.files.internal("images/player.png")));
        enemyShip = new TextureRegion(new Texture(Gdx.files.internal("images/enemyShip.png")));
        enemyShip.flip(false, true);
        laserGreen = new TextureRegion(new Texture(Gdx.files.internal("images/laserGreen.png")));
        laserRed = new TextureRegion(new Texture(Gdx.files.internal("images/laserRed.png")));
        fullHeart = new TextureRegion(new Texture(Gdx.files.internal("images/fullHeart.png")));
        midHeart = new TextureRegion(new Texture(Gdx.files.internal("images/midHeart.png")));
        emptyHeart = new TextureRegion(new Texture(Gdx.files.internal("images/emptyHeart.png")));
    }

    public static TextureFactory getInstance() {
        return instance;
    }

    public TextureRegion getBackground() {
        return background;
    }

    public TextureRegion getMenuBackground() { return menuBackground; }

    public TextureRegion getMenuRectangle() { return menuRectangle; }

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

    public TextureRegion getFullHeart() {
        return fullHeart;
    }

    public TextureRegion getMidHeart() {
        return midHeart;
    }

    public TextureRegion getEmptyHeart() {
        return emptyHeart;
    }
}
