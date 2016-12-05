package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite permettant de generer des tirs.
 * Possede une liste de caracteristiques de tir permettant de placer  le ou les tirs
 * comme on le souhaite par rapport au tireur.
 */
public abstract class AbstractShooter {

    protected World world;

    protected List<ShotCharacteristics> shotsCharacteristics;

    public AbstractShooter(World w) {
        world = w;
        shotsCharacteristics = new ArrayList<ShotCharacteristics>();
    }

    public void addShotCharacteristics(ShotCharacteristics sc) {
        shotsCharacteristics.add(sc);
    }

    /**
     * Genere les tirs a partir de la position du tireur et des caracteristiques des tirs.
     * @param position La position du tireur.
     * @return Les tirs generes.
     */
    protected Shot[] generateShots(Vector2 position) {
        Shot[] shots = new Shot[shotsCharacteristics.size()];
        // Pour chaque tir qui doit etre genere par ce shooter
        for (int i=0 ; i < shotsCharacteristics.size() ; i++) {
            ShotCharacteristics shotChar = shotsCharacteristics.get(i);
            Shot shot = world.obtainShotFromPool();             // On recupere un tir depuis le pool,
            shotChar.applyShotCharacteristics(shot);            // on lui applique les caracteristiques voulus,
            shot.getPosition().add(position);                   // on le place par rapport a son tireur
            shot.updateBoundingBox();
            shot.setIsMoving(true);
            shots[i] = shot;
        }
        return shots;
    }

    /**
     * Methode permettant de tirer.
     * @param position La position du tireur.
     */
    public abstract void shoot(Vector2 position);

    /**
     * Methode mettant a jour le generateur de tir (le shooter).
     * Peut etre utile pour gerer certains comportements.
     * @param delta Le temps ecoule depuis le dernier update.
     */
    public abstract void update(float delta);

    /**
     * Renvoie un nouvel objet (une copie) avec les memes caracteristiques.
     * @return Une copie de l'objet.
     */
    public abstract AbstractShooter copy();

}
