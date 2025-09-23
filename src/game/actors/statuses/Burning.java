package game.actors.statuses;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.positions.Location;
import game.capabilities.Flammable;

/**
 * <h1>Class representing Burning</h1>
 *
 * <p>
 *     Represent a status of burning in the system.
 *     Once any object has this status it will brun the
 *     object for five turns.
 * </p>
 *
 * Implements {@link Status}
 *
 * @author Shee Seng Cheng
 * @version 2.0
 */
public class Burning implements Status
{
    /**
     * Indicate this status will run for how many turns
     */
    private int duration;

    /**
     * The object which being burned.
     */
    private final Flammable OBJECT;

    /**
     * The damage cause by burning
     */
    private final int DAMAGE;

    /**
     * Integer indicate the end of this status.
     */
    private static final int END = 0;

    /**
     * Constructor for Burning
     * @param object the object which being burned
     * @param damage the damage cause by burning
     * @param duration status will run for how many turns
     */
    public Burning(Flammable object, int damage, int duration)
    {
        this.OBJECT = object;
        this.DAMAGE = damage;
        this.duration = duration;
    }

    /**
     * Called once per tick to update the status of the current OBJECT.
     * Each turn reducing one duration and burn the OBJECT by the damage.
     *
     * @param currEntity Not use here but it was equal to OBJECT
     * @param location Not use here.
     */
    @Override
    public void tickStatus(GameEntity currEntity, Location location)
    {
        this.OBJECT.burn(this.DAMAGE);
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
        return this.OBJECT + " is being burn";
    }
}
