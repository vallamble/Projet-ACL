package fr.univ_lorraine.spaceinvaders.model;

/**
 * Classe heritant de la classe Rectangle de Libgdx
 * ajoutant simplement une methode intersects qui verifie si 2 rectangles ont une intersection.
 */
public class Rectangle extends com.badlogic.gdx.math.Rectangle {

    public Rectangle() {
        super();
    }

    public Rectangle(float x, float y, float width, float height) {
        super(x, y, width, height);
    }

    public Rectangle(Rectangle rect) {
        super(rect);
    }

    /**
     * Verifie si 2 rectangles ont au moins un point d'intersection.
     * @param r L'autre rectangle.
     * @return vrai si les deux rectangles ont au moins un point d'intersection.
     */
    public boolean intersects(Rectangle r) {
        return x <= r.x + r.width && x + width >= r.x && y <= r.y + r.height && y + height >= r.y;
    }
}
