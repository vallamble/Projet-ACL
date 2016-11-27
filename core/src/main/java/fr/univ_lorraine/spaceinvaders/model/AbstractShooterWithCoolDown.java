package fr.univ_lorraine.spaceinvaders.model;

/**
 * Classe abstraite permettant de generer des tirs avec un cooldown (temps de recharge).
 */
public abstract class AbstractShooterWithCooldown extends AbstractShooter {

    protected float cooldownTime;

    protected float cooldown;

    public AbstractShooterWithCooldown(World w, float cdt) {
        super(w);
        cooldownTime = cdt;
        cooldown = 0;
    }

    /**
     * On met a jour le cooldown en fonction du temps ecoule.
     * @param delta Le temps ecoule depuis le dernier update.
     */
    @Override
    public void update(float delta) {
        if (cooldown > 0)
            cooldown -= delta;
    }

    protected boolean canShoot() {
        return cooldown <= 0;
    }

}
