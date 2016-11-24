package fr.univ_lorraine.spaceinvaders.model;

/**
 * Classe decrivant le vaisseau du joueur.
 */
public class Player extends GameMoveableElement {

    private AbstractShooter shooter;

    public Player() {
        super();
        isMoving = false;
        collision = CollisionType.PLAYER;
    }

	public Player(float x, float y, float w, float h, float s) {
		super(x, y, w, h, s);
        isMoving = false;
        collision = CollisionType.PLAYER;
	}

    public void setShooter(AbstractShooter s) {
        shooter = s;
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

    /**
     * Empeche les deplacements verticaux
     * et stoppe le mouvement du vaisseau apres l'avoir deplace.
     * @param delta Le temps ecoule depuis le dernier update.
     */
    @Override
    public void update(float delta) {
        if (isMoving && direction != null) {
            float deplacement = delta * speed;
            switch (direction) {
                case LEFT:
                    position.add(-deplacement, 0);
                    break;
                case RIGHT:
                    position.add(deplacement, 0);
                    break;
            }
            updateBoundingBox();
        }
        isMoving = false;   // On stoppe le mouvement du vaisseau apres l'avoir deplace

        // On met a jour le shooter
        if (shooter != null)
            shooter.update(delta);
    }

    public void shoot() {
        if (shooter != null)
            shooter.shoot(this.position);
    }

    public boolean handleCollision(GameElement element)
    {
        switch (element.collision) {
            case ENEMY:
                return false;
            case SHOT:
                Shot s = (Shot) element;
                this.life -= s.getDamages();
                if (this.life <= 0)
                    return false;
        }
        return true;
    }
}
