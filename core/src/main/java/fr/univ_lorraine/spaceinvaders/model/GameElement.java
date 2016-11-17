package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Classe abstraite definissant un element du jeu appartenant au monde.
 */
public abstract class GameElement {

	protected float width, height;
	
	protected Vector2 position;
	
	public GameElement(float x, float y) {
		position = new Vector2(x, y);
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public Vector2 getPosition() {
		return position;
	}

    public void setPosition(float x, float y) {
        this.position = new Vector2(x, y);
    }
}
