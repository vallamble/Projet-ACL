package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import fr.univ_lorraine.spaceinvaders.model.Bonus;
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

    private GlyphLayout glyphLayout;

    private PlayerDrawer playerDrawer;

    private EnemyDrawer enemyDrawer;

    private PlayerShotDrawer playerShotDrawer;

    private EnemyShotDrawer enemyShotDrawer;

    private BonusDrawer bonusDrawer;

    /**
     * Pixels par unite.
     */
    private float ppux, ppuy;

    /**
     * Décalages entre le monde concret et le reste de l'écran.
     * worldBottomPadding correspond à l'origine X du monde concret
     * et worldLeftPadding à l'origine Y du monde concret
     */
    private float worldTopPadding, worldBottomPadding, worldLeftPadding, worldRightPadding;

    /**
     * Active/desactive l'affichage des boundingBox.
     */
    private boolean showBoundingBox;

    private String scoreText;

    public WorldRenderer(World w, SpriteBatch sb, ShapeRenderer sr, BitmapFont font, float ppux, float ppuy, float worldTopPadding, float worldBottomPadding, float worldLeftPadding, float worldRightPadding) {
        this.world = w;
        this.spriteBatch = sb;
        this.shapeRenderer = sr;
        this.fontBatch = font;
        glyphLayout = new GlyphLayout();

        this.ppux = ppux;
        this.ppuy = ppuy;

        this.worldTopPadding = worldTopPadding;
        this.worldBottomPadding = worldBottomPadding;
        this.worldLeftPadding = worldLeftPadding;
        this.worldRightPadding = worldRightPadding;

        showBoundingBox = false;

        scoreText = "SCORE : ";

        playerDrawer = new PlayerDrawer(spriteBatch, shapeRenderer, ppux, ppuy, worldLeftPadding, worldBottomPadding);
        enemyDrawer = new EnemyDrawer(spriteBatch, shapeRenderer, ppux, ppuy, worldLeftPadding, worldBottomPadding);
        playerShotDrawer = new PlayerShotDrawer(spriteBatch, shapeRenderer, ppux, ppuy, worldLeftPadding, worldBottomPadding);
        enemyShotDrawer = new EnemyShotDrawer(spriteBatch, shapeRenderer, ppux, ppuy, worldLeftPadding, worldBottomPadding);
        bonusDrawer = new BonusDrawer(spriteBatch, shapeRenderer, ppux, ppuy, worldLeftPadding, worldBottomPadding);
    }

    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Affiche le monde.
     * Est appelee par gameScreen.render(delta).
     */
    public void render() {
        spriteBatch.begin();

        drawBackground();

        playerDrawer.draw(world.getPlayer());
        for (Enemy enemy : world.getEnemies())
            enemyDrawer.draw(enemy);
        for (Shot shot : world.getPlayerShots())
            playerShotDrawer.draw(shot);
        for (Shot shot : world.getEnemyShots())
            enemyShotDrawer.draw(shot);
        for(Bonus bonus : world.getBonuses())
            bonusDrawer.draw(bonus);

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
        spriteBatch.draw(TextureFactory.getInstance().getBackground(), worldLeftPadding * ppux, worldBottomPadding * ppuy, world.getWidth() * ppux, world.getHeight() * ppuy);
    }

    /**
     * Dessine l'interface sur l'ecran principal.
     */
    private void drawInterface() {
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // On dessine un rectangle noir dans les coins du monde afin de cacher les elements sortant du monde
        // haut
        shapeRenderer.rect(0 * ppux, (world.getHeight() + worldTopPadding) * ppuy, world.getWidth() * ppux, worldTopPadding * ppuy);
        // bas
        shapeRenderer.rect(0 * ppux, 0 * ppuy, world.getWidth() * ppux, worldBottomPadding * ppuy);
        // gauche
        shapeRenderer.rect(0 * ppux, 0 * ppuy, worldLeftPadding * ppux, (world.getHeight() + worldTopPadding + worldBottomPadding) * ppuy);
        // droite
        shapeRenderer.rect((worldLeftPadding + world.getWidth()) * ppux, 0 * ppuy, worldRightPadding * ppux, (world.getHeight() + worldTopPadding + worldBottomPadding) * ppuy);

        shapeRenderer.end();
        // On dessine les coeurs representants la vie du joueur
        spriteBatch.begin();
        drawPlayerLife();
        // On affiche le score
        String score = Integer.toString(world.getPlayerScore());
        fontBatch.getData().setScale(worldBottomPadding * 2f);
        glyphLayout.setText(fontBatch, scoreText + score);
        fontBatch.draw(spriteBatch, scoreText + score, 0 * ppux, (worldBottomPadding + world.getHeight()) * ppuy + glyphLayout.height * 1.1f);
        spriteBatch.end();
    }

    private void drawPlayerLife() {
        float heartHeight = worldBottomPadding * 0.95f;
        float heightWidthRatio = 1.1521f;
        float heartWidth = heartHeight * heightWidthRatio;

        Player player = world.getPlayer();
        for (int i=1 ; i <= player.getMaxLife() ; i++) {
            float decalageHori = heartWidth * 1.05f;
            TextureRegion toDraw;
            if (player.getLife() >= i)
                toDraw = TextureFactory.getInstance().getFullHeart();
            else
                toDraw = TextureFactory.getInstance().getEmptyHeart();

            spriteBatch.draw(toDraw, (0 + decalageHori * (i-1)) * ppux, 0 * ppuy, heartWidth * ppuy, heartHeight * ppux);
        }
    }

}
