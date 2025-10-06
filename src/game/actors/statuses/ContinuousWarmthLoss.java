package game.actors.statuses;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Warmable;

/**
 * <h1>Class represents ContinuousWarmthLoss</h1>
 *
 * <p>
 * Represents a continuous status effect that decreases the warmth level
 * of a {@link Warmable} actor each turn for a fixed duration.
 * This is similar to continuous damage but affects warmth instead of HP.
 * </p>
 *
 * <p>
 * Implements {@link Status}.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 1.0
 */
public abstract class ContinuousWarmthLoss implements Status {

    /**
     * Number of turns this status remains active.
     */
    private int duration;

    /**
     * The Warmable target affected by this status.
     */
    private final Warmable TARGET;

    /**
     * Indicates when the effect ends.
     */
    private static final int END = 0;

    /**
     * Message verb used for displaying log messages (e.g., "is frostbitten").
     */
    private final String VERB;

    /**
     * Constructor for ContinuousWarmthLoss.
     *
     * @param target   The Warmable target affected.
     * @param duration The number of turns this status lasts.
     * @param verb     The descriptive verb to show when status is active.
     */
    public ContinuousWarmthLoss(Warmable target, int duration, String verb) {
        this.TARGET = target;
        this.duration = duration;
        this.VERB = verb;
    }

    /**
     * Perform warmth reduction each turn and decrease the remaining duration.
     *
     * @param currEntity The entity currently holding this status.
     * @param location   The location of the entity.
     */
    @Override
    public void tickStatus(GameEntity currEntity, Location location) {
        TARGET.decreaseWarmthLevel();
        duration--;
    }

    /**
     * Check whether the status effect is still active.
     *
     * @return True if the duration is greater than 0, false otherwise.
     */
    @Override
    public boolean isStatusActive() {
        return duration != END;
    }

    /**
     * Return a readable representation of the status.
     *
     * @return A string describing the current status effect.
     */
    @Override
    public String toString() {
        return TARGET + " " + VERB + ".";
    }
}

