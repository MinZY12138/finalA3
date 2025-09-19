package game.grounds.teleportable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Fire;

import java.util.List;

/**
 * <h1>Class represent TeleDoor</h1>
 *
 * <p>
 *     Represent a teleport door name TeleDoor in the system.
 *     It can teleport to certain places but will burn all its surrounding.
 * </p>
 *
 * Extends {@link TeleGround}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class TeleDoor extends TeleGround
{
    /**
     * Constructor for TeleDoor
     * @param destination A list of {@link Location} indicate where it can
     *                    teleport to.
     */
    public TeleDoor(List<Location> destination)
    {
        //Pass its parameter to its parent's constructor
        super(destination, '#', "Tele-door");
    }

    /**
     * Use to burn its surrounding
     * @param destination the place to burn its surrounding
     */
    @Override
    protected void burnSurrounding(Location destination)
    {
        List<Exit> surrounding = destination.getExits();
        for (Exit location : surrounding)
        {
            location.getDestination().setGround(new Fire());
        }
    }

    /**
     * Define behaviour for teleport to a certain place.
     * @param actor the actor who interact with this teleportable door.
     * @param destination the location teleport to.
     */
    @Override
    public String teleportTo(Actor actor, Location destination)
    {
        //Burn its surrounding
        burnSurrounding(destination);
        return actor + " has teleport to " + destination;
    }
}
