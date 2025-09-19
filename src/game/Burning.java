package game;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.positions.Location;

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
 * @version 1.0
 */
public class Burning implements Status
{
    /**
     * Indicate this status will run for how many turns
     */
    private int duration = 5;

    /**
     * The object which being burned.
     */
    private final Flammable OBJECT;

    /**
     * The damage cause by burning
     */
    private final int DAMAGE;

    /**
     * Constructor for Burning
     * @param object the object which being burned
     * @param damage the damage cause by burning
     */
    public Burning(Flammable object, int damage)
    {
        this.OBJECT = object;
        this.DAMAGE = damage;
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
        return duration != 0;
    }

    @Override
    public String toString()
    {
        return this.OBJECT + " is being burn";
    }
}
