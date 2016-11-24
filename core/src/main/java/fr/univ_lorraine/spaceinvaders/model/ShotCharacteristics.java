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
    private Vector2 shiftPosition;

    public ShotCharacteristics(Shot s, float x, float y) {
        shotAttributes = s;
        shiftPosition = new Vector2(x, y);
    }

    public void applyShotCharacteristics(Shot shot) {
        shot.init(shotAttributes);
        shot.getPosition().add(shiftPosition);
    }
}
