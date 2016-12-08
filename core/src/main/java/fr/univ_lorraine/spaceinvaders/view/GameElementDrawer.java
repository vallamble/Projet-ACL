package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import fr.univ_lorraine.spaceinvaders.model.GameElement;

/**
 * Created by alexis on 27/11/2016.
 */
public abstract class GameElementDrawer {

    private SpriteBatch spriteBatch;

    private ShapeRenderer shapeRenderer;

    private float worldOriginX, worldOriginY;

    private float ppux, ppuy;

    public GameElementDrawer(SpriteBatch sn, ShapeRenderer sr, float ppux, float ppuy, float worldOriginX, float worldOriginY) {
        this.spriteBatch = sn;
        this.shapeRenderer = sr;
        this.ppux = ppux;
        this.ppuy = ppuy;
        this.worldOriginX = worldOriginX;
        this.worldOriginY = worldOriginY;
    }

    protected abstract TextureRegion getTexture(GameElement gameElement);

    public void draw(GameElement gameElement) {
        spriteBatch.draw(getTexture(gameElement), (gameElement.getPosition().x + worldOriginX) * ppux, (gameElement.getPosition().y + worldOriginY) * ppuy, gameElement.getWidth() * ppux, gameElement.getHeight() * ppuy);
    }

    public void drawBoundingBox(GameElement gameElement) {
        shapeRenderer.rect((gameElement.getBoundingBox().x + worldOriginX) * ppux, (gameElement.getBoundingBox().y + worldOriginX) * ppuy, gameElement.getBoundingBox().getWidth() * ppux, gameElement.getBoundingBox().getHeight() * ppuy);
    }

}
