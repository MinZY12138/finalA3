package game.actors.statuses;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Warmable;

/**
 * Represents a continuous status that reduces the warmth level of an actor
 * for a fixed duration. Mirrors ContinuousDamage but targets warmth instead of HP.
 */
public abstract class ContinuousWarmthLoss implements Status {

    /** Number of turns this status will remain active. */
    private int duration;

    /** The Warmable target affected by this status. */
    private final Warmable TARGET;

    private static final int END = 0;

    /** Message verb for logging (e.g., "is frostbitten"). */
    private final String VERB;

    /**
     * @param target    the Warmable target affected
     * @param duration  number of turns this status lasts
     * @param verb      message verb to describe the status
     */
    public ContinuousWarmthLoss(Warmable target, int duration, String verb) {
        this.TARGET = target;
        this.duration = duration;
        this.VERB = verb;
    }

    /**
     * Called once per tick to update the status.
     * Reduces warmth and decrements duration.
     */
    @Override
    public void tickStatus(GameEntity currEntity, Location location) {
        TARGET.decreaseWarmthLevel();  // always decrease by 1
        duration--;
    }

    @Override
    public boolean isStatusActive() {
        return duration != END;
    }

    @Override
    public String toString() {
        return TARGET + " " + VERB + ".";
    }
}
