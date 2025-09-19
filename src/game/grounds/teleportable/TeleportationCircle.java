package game.grounds.teleportable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;

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
 * @version 2.0
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
    protected void burnSurrounding(Location destination)
    {
        //Get its surrounding
        List<Location> surrounding = destination.getNearbyLocations(1);

        //Randomly choose one of its surrounding
        Location placeToBurn = surrounding.get(RANDOM.nextInt(0,
                surrounding.size()));

        //Burn it
        placeToBurn.setGround(this.summonFire());
    }
}
