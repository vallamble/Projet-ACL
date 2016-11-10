package model;
public class Ship extends GameMoveableElement{

	private int x,y;

	/**
	 * @param x
	 * @param y
	 */
	public Ship(int x, int y) {
		super(new Vector2(x,y));
		isMoving = true;
		speed = 5;
	}

}
