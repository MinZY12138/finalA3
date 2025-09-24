package game.actors.statuses;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

/**
 * <h1>Class represent ContinuousDamage</h1>
 *
 * <p>
 *     Represent a continuous status that cause damage to
 *     {@link Actor} in the system. Any actor has this
 *     status will continuously hurt for a specific amount
 *     of damage for a specific duration.
 * </p>
 *
 * Extends {@link Status}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class ContinuousDamage implements Status
{
    /**
     * Indicate this status will run for how many turns
     */
    private int duration;

    /**
     * The {@link Actor} which has this status.
     */
    private final Actor ACTOR;

    /**
     * The damage cause by status.
     */
    private final int DAMAGE;

    /**
     * Integer indicate the end of this status.
     */
    private static final int END = 0;

    private final String VERB;

    /**
     * Constructor for ContinuousDamage
     * @param actor The {@link Actor} which has this status.
     * @param damage The damage cause by this status.
     * @param duration Status will run for how many turns
     * @param verb Message that show the status.
     */
    public ContinuousDamage(Actor actor, int damage, int duration, String verb)
    {
        this.ACTOR = actor;
        this.DAMAGE = damage;
        this.duration = duration;
        this.VERB = verb;
    }

    /**
     * Called once per tick to update the status of the current ACTOR.
     * Each turn reducing one duration and hurt the ACTOR by the damage.
     *
     * @param currEntity Not use here but it was equal to ACTOR
     * @param location Not use here.
     */
    @Override
    public void tickStatus(GameEntity currEntity, Location location)
    {
        this.ACTOR.hurt(this.DAMAGE);
        this.duration--;
    }

    /**
     * Indicates whether this status is still active.
     *
     * @return true if active, false otherwise
     */
    @Override
    public boolean isStatusActive()
    {
        return duration != END;
    }

    /**
     * String represent this status.
     * @return {@code String} details of this status.
     */
    @Override
    public String toString()
    {
        return this.ACTOR + " " + this.VERB + ".";
    }
}
