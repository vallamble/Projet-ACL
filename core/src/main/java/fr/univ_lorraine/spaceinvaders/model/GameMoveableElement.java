package fr.univ_lorraine.spaceinvaders.model;

/**
 * Classe abstraite definissant un element capable de se deplacer dans le monde.
 */
public abstract class GameMoveableElement extends GameElement {

	public enum Direction {UP, DOWN, LEFT, RIGHT, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT};
	
	protected Direction direction;
	
	protected float speed;
	
	protected boolean isMoving;

    /**
     * Valeur approchee de racine carre de 2.
     * Utile pour calculer les coordonnees lors des deplacements en diagonal.
     */
	private final static float sqrt2 = 1.41421356237f;

	public GameMoveableElement() {
		super();
    }

    public GameMoveableElement(float x, float y, float w, float h) {
        super(x, y, w, h);
    }

    public GameMoveableElement(float x, float y, float w, float h, float s) {
        super(x, y, w, h);
        speed = s;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setIsMoving(boolean isMoving) {
        this.isMoving = isMoving;
    }

    /**
	 * Met a jour la position de l'element en fonction du temps et de sa direction.
	 * Est appelee dans world.update() sur les elements du monde.
	 * @param delta Le temps ecoule depuis le dernier update.
	 */
	public void update(float delta) {
		if (isMoving && direction != null) {
			float deplacement;
			switch(direction) {
				case UP:
                    deplacement = delta*speed;
                    position.add(0, deplacement);
                    break;
				case DOWN:
                    deplacement = delta*speed;
                    position.add(0, -deplacement);
					break;
				case LEFT:
                    deplacement = delta*speed;
                    position.add(-deplacement, 0);
					break;
				case RIGHT:
                    deplacement = delta*speed;
                    position.add(deplacement, 0);
					break;

                case UPLEFT:
                    deplacement = delta*speed / sqrt2;
                    position.add(-deplacement, deplacement);
                    break;
                case UPRIGHT:
                    deplacement = delta*speed / sqrt2;
                    position.add(deplacement, deplacement);
                    break;
				case DOWNLEFT:
					deplacement = delta*speed / sqrt2;
					position.add(-deplacement, -deplacement);
					break;
                case DOWNRIGHT:
                    deplacement = delta*speed / sqrt2;
                    position.add(deplacement, -deplacement);
                    break;
			}
			updateBoundingBox();
		}
	}

    /**
     * Permet d'initialiser les attributs d'un element a l'aide d'un autre element.
     * Cette methode est utile pour l'utilisation de Pool de l'objet concerne.
     * @param gameMoveableElement L'autre element.
     */
	public void init(GameMoveableElement gameMoveableElement) {
		super.init(gameMoveableElement);
        this.speed = gameMoveableElement.speed;
		this.isMoving = gameMoveableElement.isMoving;
		this.direction = gameMoveableElement.direction;
	}

    /**
     * Methode de l'interface Poolable qui reinitialise les attributs de l'objet.
     */
    @Override
    public void reset() {
        super.reset();
        this.speed = 0f;
        this.isMoving = false;
        this.direction = null;
    }

}
