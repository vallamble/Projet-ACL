package model;

public abstract class GameElement {

	protected int height, weight;
	
	protected Vector2 position;
	
	public GameElement(Vector2 pos) {
		position = pos;
	}

	/**
	 * @return the position
	 */
	public Vector2 getPosition() {
		return position;
	}
	
}
