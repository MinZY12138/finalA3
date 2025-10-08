package game.actors.statuses;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.actors.Actor;
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

 * Extend {@link ContinuousEffect}
 *
 * @author Min Zhengyuan
 * @version 2.0
 * <p>
 * Modified by: Shee Seng Cheng, Tay Chee Hsian, Ng Jun Jie
 */
public abstract class ContinuousWarmthLoss extends ContinuousEffect {

    /**
     * Constructor for ContinuousWarmthLoss.
     *
     * @param target   The Warmable target affected.
     * @param duration The number of turns this status lasts.
     * @param verb     The descriptive verb to show when status is active.
     */
    public ContinuousWarmthLoss(Warmable target, int duration, String verb) {
        super((Actor) target, duration, verb);
    }

    /**
     * Perform warmth reduction each turn and decrease the remaining duration.
     *
     * @param currEntity The entity currently holding this status.
     * @param location   The location of the entity.
     */
    @Override
    public void tickStatus(GameEntity currEntity, Location location) {
        super.tickStatus(currEntity, location);
        Warmable warmable = (Warmable) this.ACTOR;
        warmable.decreaseWarmthLevel();
    }
}

