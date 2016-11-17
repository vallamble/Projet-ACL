package fr.univ_lorraine.spaceinvaders.model;

/**
 * Classe modelisant le monde du jeu.
 * Prend en charge la mise a jour des differents elements du monde en fonction du temps.
 */
public class World {

	private float width, height;

	Ship ship;

	public World(float w, float h) {
		width = w;
		height = h;
        ship = new Ship(0, 0);
        // On positionne le vaisseau du joueur en bas au milieu du monde
        ship.setPosition(this.width/2 - ship.getWidth()/2, 0);
	}

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Ship getShip() {
        return ship;
    }

    /**
     * On met a jour les differents elements du monde.
     * @param delta Le temps ecoule depuis le dernier update.
     */
    public void update(float delta) {
        ship.update(delta);
        // On verifie que le vaisseau ne sort pas du monde, si c'est le cas, on le repositionne
        if (ship.getPosition().x < 0f)
            ship.setPosition(0f, ship.getPosition().y);
        else if (ship.getPosition().x > width - ship.getWidth())
            ship.setPosition(width - ship.getWidth(), ship.getPosition().y);
    }
	
}
