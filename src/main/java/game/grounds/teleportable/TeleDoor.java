package game.grounds.teleportable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.GroundInfo;

import java.util.List;

/**
 * <h1>Class represent TeleDoor</h1>
 *
 * <p>
 * Represent a teleport door name TeleDoor in the system.
 * It can use by {@link Actor} teleport to certain places
 * but will burn all its surrounding.
 * </p>
 * <p>
 * Extends {@link TeleGround}
 *
 * @author Shee Seng Cheng
 * @version 2.0
 * <p>
 * Modified by: Tay Chee Hsian
 */
public class TeleDoor extends TeleGround {
    /**
     * Constructor for TeleDoor
     *
     * @param destination A list of {@link Location} indicate where it can
     *                    teleport to.
     */
    public TeleDoor(List<Location> destination, Location source) {
        //Pass its parameter to its parent's constructor
        super(destination, GroundInfo.TELE_DOOR.getDISPLAY_CHAR(),
                GroundInfo.TELE_DOOR.getNAME(), source);
    }

    /**
     * Use to burn its surrounding
     *
     * @param destination the place to burn its surrounding
     */
    @Override
    protected void burnSurrounding(Location destination) {
        //Burn all its surrounding
        List<Exit> surrounding = destination.getExits();

        for (Exit location : surrounding) {
            burnLocation(location.getDestination(), getDURATION_OF_BURNING());
        }
    }
}
