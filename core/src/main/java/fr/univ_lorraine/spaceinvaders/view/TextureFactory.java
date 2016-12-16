package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureFactory {

    private static TextureFactory instance = new TextureFactory();

    private TextureRegion background;

    private TextureRegion menuBackground;

    private TextureRegion menuBoutonLancer;

    private TextureRegion menuBoutonLancerSelect;

    private TextureRegion menuBoutonQuitter;

    private TextureRegion menuBoutonQuitterSelect;

    private TextureRegion menuBoutonScores;

    private TextureRegion menuBoutonScoresSelect;

    private TextureRegion boutonPause;

    private TextureRegion player;

    private TextureRegion simpleEnemyShip;

    private TextureRegion smallEnemyShip;

    private TextureRegion laserGreen;

    private TextureRegion laserRed;

    private TextureRegion fullHeart;

    private TextureRegion midHeart;

    private TextureRegion emptyHeart;

    private TextureRegion backgroundGameOver;

    private TextureRegion gameOver;

    private TextureRegion meilleurScore;

    private TextureRegion gameoverBoutonMenu;


    public TextureRegion getBackgroundGameOver() {
        return backgroundGameOver;
    }



    private TextureFactory() {
        meilleurScore = new TextureRegion(new Texture(Gdx.files.internal("images/meilleurScore.png")));
        backgroundGameOver = new TextureRegion(new Texture(Gdx.files.internal("images/endBackground.jpg")));
        background = new TextureRegion(new Texture(Gdx.files.internal("images/starBackground.png")));
        menuBackground = new TextureRegion(new Texture(Gdx.files.internal("images/menuBackground.png")));
        menuBoutonLancer = new TextureRegion(new Texture(Gdx.files.internal("images/lancer.png")));
        menuBoutonLancerSelect = new TextureRegion(new Texture(Gdx.files.internal("images/lancerSelect.png")));
        menuBoutonQuitter = new TextureRegion(new Texture(Gdx.files.internal("images/quitter.png")));
        menuBoutonQuitterSelect = new TextureRegion(new Texture(Gdx.files.internal("images/quitterSelect.png")));
        menuBoutonScores = new TextureRegion(new Texture(Gdx.files.internal("images/tableauScore.png")));
        menuBoutonScoresSelect = new TextureRegion(new Texture(Gdx.files.internal("images/tableauScoreSelect.png")));
        boutonPause = new TextureRegion(new Texture(Gdx.files.internal("images/boutonPause.png")));
        player = new TextureRegion(new Texture(Gdx.files.internal("images/player.png")));
        simpleEnemyShip = new TextureRegion(new Texture(Gdx.files.internal("images/simpleEnemyShip.png")));
        simpleEnemyShip.flip(false, true);
        smallEnemyShip = new TextureRegion(new Texture(Gdx.files.internal("images/smallEnemyBlue.png")));
        laserGreen = new TextureRegion(new Texture(Gdx.files.internal("images/laserGreen.png")));
        laserRed = new TextureRegion(new Texture(Gdx.files.internal("images/laserRed.png")));
        fullHeart = new TextureRegion(new Texture(Gdx.files.internal("images/fullHeart.png")));
        midHeart = new TextureRegion(new Texture(Gdx.files.internal("images/midHeart.png")));
        emptyHeart = new TextureRegion(new Texture(Gdx.files.internal("images/emptyHeart.png")));
        gameOver = new TextureRegion(new Texture(Gdx.files.internal("images/gameOver.png")));
        gameoverBoutonMenu = new TextureRegion(new Texture(Gdx.files.internal("images/menuSelect.png")));
    }

    public static TextureFactory getInstance() {
        return instance;
    }

    public TextureRegion getBackground() {
        return background;
    }

    public TextureRegion getMenuBackground() { return menuBackground; }

    public TextureRegion getMenuBoutonLancer() { return menuBoutonLancer; }

    public TextureRegion getMenuBoutonLancerSelect() { return menuBoutonLancerSelect; }

    public TextureRegion getMenuBoutonQuitter() { return menuBoutonQuitter; }

    public TextureRegion getMenuBoutonQuitterSelect() { return menuBoutonQuitterSelect; }

    public TextureRegion getMenuBoutonScores() { return menuBoutonScores; }

    public TextureRegion getMenuBoutonScoresSelect() { return menuBoutonScoresSelect; }

    public TextureRegion getBoutonPause() { return boutonPause; }

    public TextureRegion getPlayer() {
        return player;
    }

    public TextureRegion getSimpleEnemyShip() {
        return simpleEnemyShip;
    }

    public TextureRegion getSmallEnemyShip() {
        return smallEnemyShip;
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

    public TextureRegion getGameOver() {
        return gameOver;
    }

    public TextureRegion getMeilleurScore() {
        return meilleurScore;
    }

    public TextureRegion getGameoverBoutonMenu() {
        return gameoverBoutonMenu;
    }
}

