package fr.univ_lorraine.spaceinvaders.model;

/**
 * Classe abstraite definissant un element capable de se deplacer dans le monde.
 */
public abstract class GameMoveableElement extends GameElement {

	protected enum Direction {UP, DOWN, LEFT, RIGHT};
	
	protected Direction direction;
	
	protected float speed;
	
	boolean isMoving;
	
	public GameMoveableElement(float x, float y) {
		super(x, y);
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	/**
	 * Met a jour la position de l'element en fonction du temps et de sa direction.
	 * Est appelee dans world.update() sur les elements du monde.
	 * @param delta Le temps ecoule depuis le dernier update.
	 */
	public void update(float delta) {
		if (isMoving && direction != null) {
			float deplacement = delta*speed;
			switch(direction) {
				case UP:
					position.add(0, deplacement);
                    break;
				case DOWN:
					position.add(0, -deplacement);
					break;
				case LEFT:
					position.add(-deplacement, 0);
					break;
				case RIGHT:
					position.add(deplacement, 0);
					break;
			}
		}
	}
}
