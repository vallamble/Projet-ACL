package fr.univ_lorraine.spaceinvaders.model;

/**
 * Contient un bonusAttributes et sa probabilité de drop.
 */
public class BonusDropProbability {

    /**
     * Probabilite de drop du bonus.
     * Entre 0 et 1.
     */
    private float dropProbability;

    private Bonus bonusAttributes;

    public BonusDropProbability(Bonus bonusAttributes, float dropProbability) {
        this.bonusAttributes = bonusAttributes;
        this.dropProbability = dropProbability;
    }

    public Bonus getBonusAttributes() {
        return bonusAttributes;
    }

    /**
     * Verifie si le bonus drop selon le tirage passe en parametre.
     * @param random Nombre genere aleatoirement entre 0 et 1.
     * @return vrai si le bonus drop.
     */
    public boolean drop(float random) {
        if (random <= dropProbability)
            return true;
        else
            return false;
    }
}
