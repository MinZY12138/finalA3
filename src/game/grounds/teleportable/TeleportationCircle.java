package game.grounds.teleportable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Fire;

import java.util.List;
import java.util.Random;

/**
 * <h1>Class represent TeleportationCircle</h1>
 *
 * <p>
 *     Represent teleportation circle in the system.
 *     It can use to teleport {@link Actor}to a certain
 *     places but will burn one of its surrounding.
 * </p>
 *
 * Extends {@link TeleGround}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class TeleportationCircle extends TeleGround
{
    /**
     * Use to get random number. (select a random location)
     */
    private static final Random RANDOM = new Random();

    /**
     * Constructor for TeleportationCircle
     *
     * @param destination A list of {@link Location} indicate
     *                    this can teleport to where.
     */
    public TeleportationCircle(List<Location> destination)
    {
        //Pass its parameter to its parent's constructor.
        super(destination, 'O', "Teleportation Circle");
    }

    /**
     * Use to burn one of its surrounding
     * @param destination the place to burn its surrounding
     */
    @Override
    protected void burnSurrounding(Location destination, Fire fire)
    {
        //Get its surrounding
        List<Location> surrounding = destination.getNearbyLocations(1);

        //Randomly choose one of its surrounding
        Location placeToBurn = surrounding.get(RANDOM.nextInt(0, surrounding.size()));

        //Burn it
        placeToBurn.setGround(fire);
    }

    /**
     * Define behaviour for teleport to a certain place.
     *
     * @param actor       the actor who interact with this teleportable object.
     * @param destination the location teleport to.
     */
    @Override
    public String teleportTo(Actor actor, Location destination)
    {
        //Use it parents method to burn its surrounding.
        super.teleportTo(actor, destination);
        return actor + " has teleport to " + destination;
    }
}
