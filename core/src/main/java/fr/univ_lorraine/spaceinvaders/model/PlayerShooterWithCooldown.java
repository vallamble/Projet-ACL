package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

import java.util.Arrays;
import java.util.List;

/**
 * Classe generant des tirs pour le joueur avec un temps de recharge.
 */
public class PlayerShooterWithCooldown extends AbstractShooterWithCooldown {

    public PlayerShooterWithCooldown(World w, float cd) {
        super(w, cd);
    }

    @Override
    public void shoot(Vector2 position) {
        // Si le cooldown est termine, on peut tirer
        if (canShoot()) {
            cooldown = cooldownTime;
            List<Shot> shots = Arrays.asList(generateShoots(position));
            world.getPlayerShots().addAll(shots); // On ajoute les tirs generes aux tirs actifs du joueur
        }
    }

}
