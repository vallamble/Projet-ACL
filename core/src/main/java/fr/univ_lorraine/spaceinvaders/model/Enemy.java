package fr.univ_lorraine.spaceinvaders.model;

import com.badlogic.gdx.utils.Pool;

/**
 * Classe decrivant un ennemi.
 */
public class Enemy extends GameMoveableElement {

    private AbstractShooter shooter;

    private AbstractBonusGenerator bonusGenerator;

    public Enemy() {
        super();
        score = 100;
    }

    public Enemy(float x, float y, float w, float h, float s) {
        super(x, y, w, h, s);
        score = 100;
    }

    public AbstractShooter getShooter() {
        return shooter;
    }

    private int score;

    public int getScore() {
        return score;
    }

    public void setShooter(AbstractShooter s) {
        shooter = s;
    }

    public AbstractBonusGenerator getBonusGenerator() { return bonusGenerator; }

    public void setBonusGenerator(AbstractBonusGenerator generator) {
        bonusGenerator = generator;
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
        if (life <= 0)
            onDie();
    }

    @Override
    public void update(float delta) {
        super.update(delta);
        // On met a jour le shooter
        if (shooter != null)
            shooter.update(delta);
        // On met à jour le generateur de bonus
        if (bonusGenerator != null)
            bonusGenerator.update(delta);
    }

    public void onDie() {
        if (bonusGenerator != null)
            bonusGenerator.generateBonus(position);
    }

    public void init(Enemy enemy) {
        super.init(enemy);
        if (enemy.getShooter() != null)
            this.setShooter(enemy.getShooter().copy());
        if (enemy.getBonusGenerator() != null)
            this.setBonusGenerator(enemy.getBonusGenerator().copy());
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
