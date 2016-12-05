package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

/**
 * Created by alexis on 05/12/2016.
 */
public class EnemyShooterWithCooldown extends AbstractShooterWithCooldown {

    public EnemyShooterWithCooldown(World w, float cdt) {
        super(w, cdt);
    }

    @Override
    public void shoot(Vector2 position) {
        // Si le cooldown est termine, on peut tirer
        if (canShoot()) {
            cooldown = cooldownTime;
            List<Shot> shots = Arrays.asList(generateShots(position));
            world.getEnemyShots().addAll(shots); // On ajoute les tirs generes aux tirs actifs des ennemis
        }
    }

    @Override
    public AbstractShooter copy() {
        EnemyShooterWithCooldown copy = new EnemyShooterWithCooldown(world, cooldownTime);
        for (ShotCharacteristics shotCharacteristics : shotsCharacteristics)
            copy.addShotCharacteristics(shotCharacteristics);
        return copy;
    }

}
