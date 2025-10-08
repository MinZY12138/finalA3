package game.actors.statuses;

import game.actors.animals.Warmable;

/**
 * <h1>Class represents Frostbiting</h1>
 *
 * <p>
 * Represents the frostbite status effect that decreases the warmth level
 * of a {@link Warmable} actor by 1 each turn for a fixed duration.
 * This effect is part of REQ4: Coating.
 * </p>
 *
 * <p>
 * Extends {@link ContinuousWarmthLoss}.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.1
 */
public class Frostbiting extends ContinuousWarmthLoss {

    /**
     * Constructor for Frostbiting.
     *
     * @param target   The Warmable target affected by the frostbite.
     * @param duration The number of turns the frostbite lasts (REQ4: 3).
     */
    public Frostbiting(Warmable target, int duration) {
        super(target, duration, "is frostbiting");
    }
}
