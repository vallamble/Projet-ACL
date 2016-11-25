package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

/**
 * Classe contenant les caracteristiques d'un tir, c'est-a-dire
 * les attributs d'un tir ainsi que son placement (via un decalage).
 */
public class ShotCharacteristics {

    private Shot shotAttributes;

    /**
     * Le decalage (changement de position) du tir.
     */
    private Vector2 positionShift;

    public ShotCharacteristics(Shot s, float shiftX, float shiftY) {
        shotAttributes = s;
        positionShift = new Vector2(shiftX, shiftY);
    }

    public void applyShotCharacteristics(Shot shot) {
        shot.init(shotAttributes);
        shot.getPosition().add(positionShift);
    }
}
