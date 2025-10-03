package game.actors.statuses;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Poisoning class</h1>
 * <p>
 * Represent a status of {@code Poisoning} on the {@link Actor}, inheriting from
 * the {@link ContinuousDamage} abstract class.
 * </p>
 *
 * <p>Effect: deals 4 damage per turn for a fixed number of turns.</p>
 *
 * @author Min Zhengyuan
 * @version 1.0.0
 * @since 2025-10-03
 */
public class Poisoning extends ContinuousDamage {

    private static final int DAMAGE_PER_TURN = 4;

    /**
     * The constructor of the Poisoning class.
     *
     * @param actor    the actor who is poisoned
     * @param duration total poisoning turns (e.g., 5)
     */
    public Poisoning(Actor actor, int duration) {
        super(actor, DAMAGE_PER_TURN, duration, "is poisoned");
    }
}
