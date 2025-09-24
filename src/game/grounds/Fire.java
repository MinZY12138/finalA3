package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.statuses.Burning;

/**
 * <h1>Class represent Fire</h1>
 *
 * <p>
 *     Represent fire in the system. Fire is a ground type
 *     whoever ({@link Actor}) step on it will cause 5 damage
 *     to the actor for 5 turns.
 * </p>
 *
 * Extends {@link Ground}
 *
 * @author Shee Seng Cheng
 * @version 2.0
 */
public class Fire extends Ground
{
    /**
     * Indicate the fire on the ground for how many turn
     */
    private int duration = 5;

    /**
     * Indicate the amount can hurt the actor standing on it.
     */
    private static final int DAMAGE = 5;

    /**
     * Constructor for Fire.
     */
    public Fire()
    {
        //Pass parameter of itself to its parent's class
        super('^', "Fire");
    }

    /**
     * Fire can also experience the joy of time.
     *
     * <p>
     *     Use to detect has an actor step on it or not,
     *     if so burn the actor by applying burning status
     *     to the actor.
     * </p>
     * @param location The location of the Fire
     */
    @Override
    public void tick(Location location)
    {
        if (duration == 0)
        {
            location.setGround(new Dirt());
        }

        duration --;

        if (location.containsAnActor())
        {
            //Get the actor who step on it and applying burning status.
            Actor actor = location.getActor();
            actor.addStatus(new Burning(actor, DAMAGE, duration));
        }
    }
}
