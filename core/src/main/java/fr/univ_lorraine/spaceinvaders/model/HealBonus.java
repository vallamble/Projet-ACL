package fr.univ_lorraine.spaceinvaders.model;

/**
 * Bonus soignant le joueur.
 */
public class HealBonus extends Bonus {

    /**
     * Points de vie recuperes.
     */
    private int heal;

    public HealBonus(float x, float y, float w, float h, float s, int heal) {
        super(x, y, w, h, s);
        this.heal = heal;
        this.isMoving = true;
    }

    public HealBonus() {
        super();
    }

    public int getHeal() {
        return heal;
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.HEAL_BONUS;
    }

    @Override
    public void handleCollision(GameElement element) {
        if (element.getCollisionType() == CollisionType.PLAYER)
            life = 0;
    }

    @Override
    public BonusType getBonusType() {
        return BonusType.HEAL;
    }

    @Override
    public Bonus copy() {
        HealBonus copy = new HealBonus();
        copy.init(this);
        return copy;
    }

    public void init(HealBonus healBonus) {
        super.init(healBonus);
        this.heal = healBonus.heal;
    }

}
