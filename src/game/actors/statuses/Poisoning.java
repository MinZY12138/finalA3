package game.actors.statuses;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Class represents Poisoning</h1>
 *
 * <p>
 * Represents a continuous status effect that deals fixed damage to an {@link Actor}
 * each turn for a limited duration. This effect models a poisoning condition.
 * </p>
 *
 * <p>
 * Extends {@link ContinuousEffect}.
 * </p>
 *
 * <p>
 * Effect: deals 4 damage per turn for a fixed number of turns.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.1
 */
public class Poisoning extends ContinuousDamage {

    /**
     * Constructor for Poisoning.
     *
     * @param actor    The actor who is poisoned.
     * @param duration The number of turns the poisoning lasts (e.g., 5).
     */
    public Poisoning(Actor actor, int damage, int duration) {
        super(actor, damage, duration, "is poisoning");
    }
}
