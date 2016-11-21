package fr.univ_lorraine.spaceinvaders.model;

/**
 * Classe decrivant le vaisseau du joueur.
 */
public class Player extends GameMoveableElement {

	public Player(float x, float y) {
		super(x, y);
		isMoving = false;
		speed = 5f;
        // Ratio largeur/hauteur en fonction de l'image utilise.
        width = 1f;
        height = 1.3368f;
	}

    /**
     * Change la direction du vaisseau à gauche et le met en mouvement.
     */
    public void turnLeft() {
        direction = Direction.LEFT;
        isMoving = true;
    }

    /**
     * Change la direction du vaisseau à droite et le met en mouvement.
     */
    public void turnRight() {
        direction = Direction.RIGHT;
        isMoving = true;
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        isMoving = false;   // On stoppe le mouvement du vaisseau apres l'avoir deplace.
    }
}
