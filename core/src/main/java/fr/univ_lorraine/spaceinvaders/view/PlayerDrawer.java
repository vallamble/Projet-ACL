package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by alexis on 27/11/2016.
 */
public class PlayerDrawer extends GameElementDrawer {

    public PlayerDrawer(SpriteBatch sn, ShapeRenderer sr, float ppux, float ppuy) {
        super(sn, sr, ppux, ppuy);
    }

    @Override
    protected TextureRegion getTexture() {
        return TextureFactory.getInstance().getPlayer();
    }
}
