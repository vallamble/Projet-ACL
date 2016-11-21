package fr.univ_lorraine.spaceinvaders.model;

/**
 * Classe modelisant le monde du jeu.
 * Prend en charge la mise a jour des differents elements du monde en fonction du temps.
 */
public class World {

	private float width, height;

	Player player;

	public World(float w, float h) {
		width = w;
		height = h;
        player = new Player(0, 0);
        // On positionne le vaisseau du joueur en bas au milieu du monde
        player.setPosition(this.width/2 - player.getWidth()/2, 0);
	}

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public Player getPlayer() {
        return player;
    }

    /**
     * On met a jour les differents elements du monde.
     * @param delta Le temps ecoule depuis le dernier update.
     */
    public void update(float delta) {
        player.update(delta);
        // On verifie que le vaisseau ne sort pas du monde, si c'est le cas, on le repositionne
        if (player.getPosition().x < 0f)
            player.setPosition(0f, player.getPosition().y);
        else if (player.getPosition().x > width - player.getWidth())
            player.setPosition(width - player.getWidth(), player.getPosition().y);
    }
	
}
