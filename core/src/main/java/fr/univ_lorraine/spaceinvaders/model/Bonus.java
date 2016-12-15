package fr.univ_lorraine.spaceinvaders.model;

/**
 * Classe abstraite representant un bonus.
 */
public abstract class Bonus extends GameMoveableElement {

    public Bonus() {

    }

    /**
     * Enum permettant de distinguer les differents types de bonus pour l'affichage.
     */
    public enum BonusType {HEAL}

    public Bonus(float x, float y, float w, float h, float s) {
        super(x, y, w, h, s);
    }

    public abstract BonusType getBonusType();

    public abstract Bonus copy();
}
