package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Generateur de bonus concret se basant sur des probabilites de drop.
 * Un seul bonus genere par generation.
 */
public class RandomBonusGenerator extends AbstractBonusGenerator {

    /**
     * Ensemble des bonus disponibles associes a leurs probabilites
     * dont l'ordre defini la priorite de drop.
     */
    private List<BonusDropProbability> bonusDropProbabilities;

    private Random RNG;

    public RandomBonusGenerator(World world, float shiftDropX, float shiftDropY) {
        super(world, shiftDropX, shiftDropY);
        bonusDropProbabilities = new ArrayList<BonusDropProbability>();
        RNG = new Random();
    }

    public void addBonusDropProbability(BonusDropProbability bonusDropProbability) {
        bonusDropProbabilities.add(bonusDropProbability);
    }

    /**
     * Genere un bonus de la liste de maniere aleatoire (selon les probabilites associes).
     * Genere un seul bonus a la fois.
     * @param position La position de l'element declencheur du drop.
     */
    @Override
    public void generateBonus(Vector2 position) {
        boolean bonusGenerated = false;
        // Tant qu'aucun bonus n'a ete genere et que le parcours des bonus n'est pas termine
        Iterator<BonusDropProbability> iterator = bonusDropProbabilities.iterator();
        while(!bonusGenerated && iterator.hasNext()) {
            BonusDropProbability bonusDropProbability = iterator.next();
            if (bonusDropProbability.drop(RNG.nextFloat())) {
                // On genere le bonus
                Bonus bonus = bonusDropProbability.getBonusAttributes().copy();
                bonus.setPosition(position.add(shiftDropPosition));
                world.addBonus(bonus);
                bonusGenerated = true;
            }
        }
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public AbstractBonusGenerator copy() {
        RandomBonusGenerator copy = new RandomBonusGenerator(world, shiftDropPosition.x, shiftDropPosition.y);
        for (BonusDropProbability bonusDropProbability : bonusDropProbabilities)
            copy.addBonusDropProbability(bonusDropProbability);
        return copy;
    }
}
