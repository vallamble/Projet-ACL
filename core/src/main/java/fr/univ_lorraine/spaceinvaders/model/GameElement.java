package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

/**
 * Classe abstraite definissant un element du jeu appartenant au monde.
 */
public abstract class GameElement implements Collidable, Pool.Poolable {

	protected float width, height;
	
	protected Vector2 position;

	protected int life;

    /**
     * Figure definissant la zone de collision de cet element.
     */
	protected Rectangle boundingBox;

	public GameElement() {
		position = new Vector2();
		boundingBox = new Rectangle();
		life = 1;
	}

	public GameElement(float x, float y, float w, float h) {
		position = new Vector2(x, y);
        width = w;
        height = h;
		boundingBox = new Rectangle(position.x, position.y, width, height);
		life = 1;
	}

    /**
     * Met a jour la bounding box en fonction de la position.
     */
	protected void updateBoundingBox() {
		boundingBox.setPosition(position.x, position.y);
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

	public Rectangle getBoundingBox() {
		return boundingBox;
	}

	public boolean isDead() {
		return life <= 0;
	}

	public void setPosition(float x, float y) {
        this.position.set(x, y);
        updateBoundingBox();
    }

    @Override
	public boolean hasCollision(GameElement element) {
		if (element != null && boundingBox != null && element.getBoundingBox() != null) {
			return this.getBoundingBox().overlaps(element.getBoundingBox());
		}
		return false;
	}

	/**
	 * Permet d'initialiser les attributs d'un element a l'aide d'un autre element.
	 * Cette methode est utile pour l'utilisation de Pool de l'objet concerne.
	 * @param gameElement L'autre element.
	 */
	public void init(GameElement gameElement) {
        this.position.set(gameElement.position.x, gameElement.position.y);
        this.width = gameElement.width;
        this.height = gameElement.height;
        boundingBox.set(position.x, position.y, width, height);
		this.life = 1;
	}

	/**
	 * Methode de l'interface Poolable qui reinitialise les attributs de l'objet.
	 */
	@Override
	public void reset() {
		this.position.set(0f, 0f);
		this.height = 0f;
		this.width = 0f;
		this.life = 0;
		updateBoundingBox();
	}

}
