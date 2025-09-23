package game.actors.statuses;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Class representing Burning</h1>
 *
 * <p>
 *     Represent a status of burning in the system.
 *     Once any object has this status it will brun the
 *     object for five turns.
 * </p>
 *
 * Extends {@link ContinuousDamage}
 *
 * @author Shee Seng Cheng
 * @version 3.0
 */
public class Burning extends ContinuousDamage
{
    /**
     * Constructor for Burning
     * @param actor the {@link Actor} which being burned
     * @param damage the damage cause by burning
     * @param duration status will run for how many turns
     */
    public Burning(Actor actor, int damage, int duration)
    {
        //Pass parameter to its parent's constructor.
        super(actor, damage, duration, "is burning");
    }
}
