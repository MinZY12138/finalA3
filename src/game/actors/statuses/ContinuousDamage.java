package game.actors.statuses;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * <h1>Class represents ContinuousDamage</h1>
 *
 * <p>
 * Represent a continuous status that cause damage to
 * {@link Actor} in the system. Any actor has this
 * status will continuously hurt for a specific amount
 * of damage for a specific duration.
 * </p>

 * Extends {@link ContinuousEffect}
 *
 * @author Min Zhengyuan
 * @version 2.0
 *
 * Modified by: Shee Seng Cheng, Tay Chee Hsian, Ng Jun Jie
 */
public abstract class ContinuousDamage extends ContinuousEffect {

    /**
     * The damage caused by this status.
     */
    private final int DAMAGE;

    private final Display DISPLAY = new Display();

    /**
     * Constructor for ContinuousDamage.
     *
     * @param target   The actor target affected.
     * @param duration The number of turns this status lasts.
     * @param verb     The descriptive verb to show when status is active.
     */
    public ContinuousDamage(Actor target, int damage, int duration, String verb) {
        super(target, duration, verb);
        this.DAMAGE = damage;
    }

    /**
     * Perform damage each turn and decrease the remaining duration.
     *
     * @param currEntity The entity currently holding this status.
     * @param location   The location of the entity.
     */
    @Override
    public void tickStatus(GameEntity currEntity, Location location) {
        super.tickStatus(currEntity, location);
        this.ACTOR.hurt(DAMAGE);
        DISPLAY.println(this.toString());
    }

    @Override
    public String toString() {
        return String.format("%s for %d damage", super.toString(), this.DAMAGE);
    }
}

