package game.actors.statuses;

import game.actors.animals.Warmable;

/**
 * Frostbite status: reduces warmth by 1 per turn for a fixed duration.
 */
public class Frosting extends ContinuousWarmthLoss {

    /**
     * @param target   Warmable target affected by the frostbite
     * @param duration number of turns the frostbite lasts (REQ4: 3)
     */
    public Frosting(Warmable target, int duration) {
        super(target, duration, "is frostbitten");
    }
}
