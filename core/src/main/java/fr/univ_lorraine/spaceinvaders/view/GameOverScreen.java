package fr.univ_lorraine.spaceinvaders.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import fr.univ_lorraine.spaceinvaders.SpaceInvadersGame;
import fr.univ_lorraine.spaceinvaders.controller.GameOverListener;

/**
 * Ecran du menu du jeu.
 */
public class GameOverScreen extends AbstractScreen {

    public enum GameOverChoice {MENU}

    private float height1 = 20f, width1 = 15f,height2 = height1/3, width2 = width1/2,height3 = height1/4, width3 = width1/2;

    private SpriteBatch spriteBatch;

    private OrthographicCamera camera;

    private Viewport viewport;

    private float ppux, ppuy;


    private int score;

      private GameOverScreen.GameOverChoice choice = GameOverScreen.GameOverChoice.MENU;


    /**
     * Active/desactive le fpsLogger.
     */


    public GameOverScreen(SpaceInvadersGame g) {

        super(g);
        spriteBatch = new SpriteBatch();
        camera = new OrthographicCamera();
        ppux = 95f;
        ppuy = 95f;
        viewport = new FitViewport(width1 * ppux, height1 * ppuy, camera);
        viewport.apply();
        camera.position.set(width1 * ppux / 2, height1 * ppuy / 2, 0);
        score=-1;
    }

    @Override
    public void render(float delta) {
        // Couleur de fond de l'écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glDisable(GL20.GL_BLEND);

        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.begin();
        drawBackground();
        spriteBatch.end();
        //drawScore();




    }


    private void drawBackground() {
        spriteBatch.draw(TextureFactory.getInstance().getBackgroundGameOver(), 0 * ppux, 0 * ppuy, width1 * ppux, height1 * ppuy);
        spriteBatch.draw(TextureFactory.getInstance().getGameoverBoutonMenu(), 6 * ppux, 1 * ppuy, width1 * ppux/4, height1 * ppuy/10);
        if(this.score==-1){
            spriteBatch.draw(TextureFactory.getInstance().getMeilleurScore(), 4 * ppux, 15 * ppuy, width3 * ppux, height3 * ppuy);
        }
        else{
            spriteBatch.draw(TextureFactory.getInstance().getGameOver(), 4 * ppux, 13 * ppuy, width2 * ppux, height2 * ppuy);
            drawScore();
        }
    }

    private void drawScore() {
        BitmapFont font=new BitmapFont(Gdx.files.internal("fonts/test.ttf"), false);

        //http://stackoverflow.com/questions/17127201/libgdx-add-scores-display-it-at-top-left-corner-of-the-screen
        //http://tuto-libgdx.blogspot.fr/2013/08/6-les-fonts.html
        spriteBatch.begin();
        font.draw(spriteBatch, Integer.toString(score), 50,80);
        spriteBatch.end();
    }

    public void setChoice(int num) {
        if(choice == GameOverScreen.GameOverChoice.MENU) {
            if (num == 1)
                choice = GameOverScreen.GameOverChoice.MENU;
        }
    }


    public void selectChoice() {
        if(choice == GameOverScreen.GameOverChoice.MENU) {
            game.changeScreen(SpaceInvadersGame.ScreenEnum.MENU_SCREEN);
        }

    }


    public void resize(int width1, int height1) { viewport.update(width1, height1);
    }

    public void setScore(int score) {
        this.score = score;
    }

}
