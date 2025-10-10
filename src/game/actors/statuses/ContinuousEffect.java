package game.actors.statuses;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

/**
 * <h1>Class represent ContinuousEffect</h1>
 *
 * <p>
 * Represent ContinuousEffect in the system
 * that has specific duration of lifetime.
 * </p>
 *
 * Extends {@link Status}
 *
 * @author Shee Seng Cheng
 * @version 2.0
 */
public abstract class ContinuousEffect implements Status {

    /**
     * Indicate this status will run for how many turns
     */
    protected int duration;

    /**
     * The {@link Actor} which has this status.
     */
    protected final Actor ACTOR;

    /**
     * The status verb, e.g. is burning...
     */
    protected final String VERB;

    /**
     * The display object to print message.
     */
    protected final Display DISPLAY = new Display();

    /**
     * Constructor for ContinuousDamage
     *
     * @param actor    The {@link Actor} which has this status.
     * @param duration Status will run for how many turns
     * @param verb     Message that show the status.
     */
    public ContinuousEffect(Actor actor, int duration, String verb) {
        this.ACTOR = actor;
        this.duration = duration;
        this.VERB = verb;
    }

    /**
     * Called once per tick to update the status of the current ACTOR.
     *
     * @param currEntity Not use here but it was equal to ACTOR
     * @param location   Not use here.
     */
    @Override
    public void tickStatus(GameEntity currEntity, Location location) {
        this.duration--;
    }

    /**
     * Indicates whether this status is still active.
     *
     * @return true if active, false otherwise
     */
    @Override
    public boolean isStatusActive() {
        int endStatus = 0;
        return duration != endStatus;
    }

    /**
     * String represent this status.
     *
     * @return {@code String} details of this status.
     */
    @Override
    public String toString() {
        return this.ACTOR + " " + this.VERB;
    }
}
