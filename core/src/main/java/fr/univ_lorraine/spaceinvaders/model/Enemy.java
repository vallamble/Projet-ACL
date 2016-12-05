package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.utils.Pool;

/**
 * Classe decrivant un ennemi.
 */
public class Enemy extends GameMoveableElement {

    private AbstractShooter shooter;

    public Enemy() {
        super();
    }

    public Enemy(float x, float y, float w, float h, float s) {
        super(x, y, w, h, s);
    }

    public AbstractShooter getShooter() {
        return shooter;
    }

    public void setShooter(AbstractShooter s) {
        shooter = s;
    }

    public void shoot() {
        if (shooter != null)
            shooter.shoot(this.position);
    }

    @Override
    public void handleCollision(GameElement element) {
        switch (element.getCollisionType()) {
            case PLAYER:
                this.life = 0;
                break;
            case SHOT:
                Shot s = (Shot) element;
                this.life -= s.getDamages();
                break;
        }
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        // On met a jour le shooter
        if (shooter != null)
            shooter.update(delta);
    }

    public void init(Enemy enemy) {
        super.init(enemy);
        if (enemy.getShooter() != null)
            this.setShooter(enemy.getShooter().copy());
    }

    @Override
    public void reset() {
        super.reset();
        shooter = null;
    }

    @Override
    public CollisionType getCollisionType() {
        return CollisionType.ENEMY;
    }

}
