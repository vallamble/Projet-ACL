package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Classe abstraite d'un generateur de bonus associe a un ennemi.
 */
public abstract class AbstractBonusGenerator {

    /**
     * Decalage de position a laquelle le bonus est genere.
     */
    protected Vector2 shiftDropPosition;

    protected World world;

    public AbstractBonusGenerator(World world, float shiftDropX, float shiftDropY) {
        this.world = world;
        shiftDropPosition = new Vector2(shiftDropX, shiftDropY);
    }

    /**
     * Methode permettant de generer un bonus.
     * @param position La position de l'element declencheur du drop.
     */
    public abstract void generateBonus(Vector2 position);

    /**
     * Methode mettant a jour le generateur de bonus.
     * Peut etre utile pour gerer certains comportements.
     * @param delta Le temps ecoule depuis le dernier update.
     */
    public abstract void update(float delta);

    /**
     * Renvoie un nouvel objet (une copie) avec les memes caracteristiques.
     * @return Une copie de l'objet.
     */
    public abstract AbstractBonusGenerator copy();
}
