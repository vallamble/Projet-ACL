package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;

import fr.univ_lorraine.spaceinvaders.model.Enemy;
import fr.univ_lorraine.spaceinvaders.model.Player;
import fr.univ_lorraine.spaceinvaders.model.Shot;
import fr.univ_lorraine.spaceinvaders.model.World;

/**
 * Classe qui gere l'affichage du monde.
 */
public class WorldRenderer {

    private World world;

    private SpriteBatch spriteBatch;

    private ShapeRenderer shapeRenderer;

    private BitmapFont fontBatch;

    private OrthographicCamera camera;

    private Viewport viewport;

    private PlayerDrawer playerDrawer;

    private EnemyDrawer enemyDrawer;

    private PlayerShotDrawer playerShotDrawer;

    private EnemyShotDrawer enemyShotDrawer;

    /**
     * Pixels par unite.
     */
    private float ppux, ppuy;

    private float worldOriginX, worldOriginY;

    /**
     * Active/desactive l'affichage des boundingBox.
     */
    private boolean showBoundingBox;

    public WorldRenderer(World w) {
        world = w;
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        fontBatch = new BitmapFont(Gdx.files.internal("fonts/test.fnt"));
        fontBatch.getData().setScale(5,5);
        camera = new OrthographicCamera();
        ppux = 95f;
        ppuy = 95f;
        worldOriginX = 0f;
        worldOriginY = 1f;
        viewport = new FitViewport((world.getWidth() + worldOriginX) * ppux, (world.getHeight() + worldOriginY) * ppuy, camera);
        viewport.apply();
        camera.position.set((world.getWidth() + worldOriginX) * ppuy / 2, (world.getHeight() + worldOriginY) * ppuy / 2, 0);
        showBoundingBox = false;

        playerDrawer = new PlayerDrawer(spriteBatch, shapeRenderer, ppux, ppuy, worldOriginX, worldOriginY);
        enemyDrawer = new EnemyDrawer(spriteBatch, shapeRenderer, ppux, ppuy, worldOriginX, worldOriginY);
        playerShotDrawer = new PlayerShotDrawer(spriteBatch, shapeRenderer, ppux, ppuy, worldOriginX, worldOriginY);
        enemyShotDrawer = new EnemyShotDrawer(spriteBatch, shapeRenderer, ppux, ppuy, worldOriginX, worldOriginY);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Affiche le monde.
     * Est appelee par gameScreen.render(delta).
     */
    public void render() {
        spriteBatch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        spriteBatch.begin();

        drawBackground();

        playerDrawer.draw(world.getPlayer());
        for (Enemy enemy : world.getEnemies())
            enemyDrawer.draw(enemy);
        for (Shot shot : world.getPlayerShots())
            playerShotDrawer.draw(shot);
        for (Shot shot : world.getEnemyShots())
            enemyShotDrawer.draw(shot);
        if(world.getPause())
            spriteBatch.draw(TextureFactory.getInstance().getBoutonPause(), 0, world.getHeight() * ppuy - 3 *ppuy, 3 * ppuy, 3 * ppuy);
        spriteBatch.end();

        if (showBoundingBox) {
            shapeRenderer.setColor(Color.RED);
            shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
            playerDrawer.drawBoundingBox(world.getPlayer());
            for (Enemy enemy : world.getEnemies())
                enemyDrawer.drawBoundingBox(enemy);
            for (Shot shot : world.getPlayerShots())
                playerShotDrawer.drawBoundingBox(shot);
            shapeRenderer.end();
        }

        drawInterface();
    }

    private void drawBackground() {
        spriteBatch.draw(TextureFactory.getInstance().getBackground(), worldOriginX * ppux, worldOriginY * ppuy, (world.getWidth() + worldOriginX) * ppux, (world.getHeight() + worldOriginY) * ppuy);
    }

    /**
     * Dessine l'interface sur l'ecran principal.
     */
    private void drawInterface() {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // On dessine un rectangle noir en bas du monde afin de cacher les elements sortant du monde
        shapeRenderer.rect(0 * ppux, 0 * ppuy, world.getWidth() * ppux, worldOriginY * ppuy);
        shapeRenderer.end();
        // On dessine les coeurs representants la vue du joueur
        spriteBatch.begin();
        drawPlayerLife();
        // On affiche le score
        String score = Integer.toString(world.getPlayerScore());
        fontBatch.draw(spriteBatch, score, 0 * ppux, 1.2f * ppuy);
        spriteBatch.end();
    }

    private void drawPlayerLife() {
        float heartHeight = worldOriginY*0.95f;
        float heightWidthRatio = 1.1521f;
        float heartWidth = heartHeight * heightWidthRatio;

        Player player = world.getPlayer();
        for (int i=player.getMaxLife() ; i >=1 ; i-- ) {
            float decalageHori = heartWidth * 1.05f * (player.getMaxLife() - i)*-1;
            TextureRegion toDraw;
            if (player.getLife() >= i)
                toDraw = TextureFactory.getInstance().getFullHeart();
            else
                toDraw = TextureFactory.getInstance().getEmptyHeart();

            spriteBatch.draw(toDraw, (world.getWidth() - heartWidth + decalageHori) * ppux, 0 * ppuy, heartWidth * ppuy, heartHeight * ppux);
        }
    }

    public void resize(int width, int height) {
        viewport.update(width, height);
    }

}
