package game.actors.statuses;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Bleeding class</h1>
 * <p>
 * Represent a status of {@code Bleeding} on the {@link Actor} and inherit from the
 * {@link ContinuousDamage} abstract class.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-09-23
 */
public class Bleeding extends ContinuousDamage {

    /**
     * The constructor of the Bleeding class.
     *
     * @param actor    represent an actor bleeding
     * @param damage   bleeding damage per turn
     * @param duration total bleeding turns
     */
    public Bleeding(Actor actor, int damage, int duration) {
        super(actor, damage, duration, "is bleeding");
    }
}
