package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * Created by alexis on 13/12/2016.
 */
public class SoundFactory {

    private static SoundFactory instance = new SoundFactory();

    private Music mainThemeMusic;

    private SoundFactory() {
        mainThemeMusic = Gdx.audio.newMusic(Gdx.files.internal("son/menu.mp3"));
    }

    public static SoundFactory getInstance() {
        return instance;
    }

    public Music getMainThemeMusic() {
        return mainThemeMusic;
    }
}
