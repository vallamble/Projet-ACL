package model;

public class GameMoveableElement extends GameElement {

	protected enum Direction {UP, DOWN, LEFT, RIGHT};
	
	protected Direction direction;
	
	protected float speed;
	
	boolean isMoving;
	
	public GameMoveableElement(Vector2 pos) {
		super(pos);
	}

	public void update(float delta) {
		if (isMoving) {
			// Déplacemement horizontal uniquement
			switch(direction) {
				case UP:
				case DOWN:
					break;
				case LEFT:
					position.x -= delta*speed;
					break;
				case RIGHT:
					position.x += delta*speed;
					break;
			}
		}
	}
}
