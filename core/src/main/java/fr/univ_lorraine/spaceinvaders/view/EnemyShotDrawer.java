package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import fr.univ_lorraine.spaceinvaders.model.GameElement;

/**
 * Created by alexis on 27/11/2016.
 */
public class EnemyShotDrawer extends GameElementDrawer {

    public EnemyShotDrawer(SpriteBatch sn, ShapeRenderer sr, float ppux, float ppuy) {
        super(sn, sr, ppux, ppuy);
    }

    @Override
    protected TextureRegion getTexture(GameElement gameElement) {
        return TextureFactory.getInstance().getLaserRed();
    }
}
