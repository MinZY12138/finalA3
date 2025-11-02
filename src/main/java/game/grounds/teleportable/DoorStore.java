package game.grounds.teleportable;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.TeleportAction;
import game.actions.Teleportable;
import game.capabilities.SummonDirt;
import game.grounds.GroundInfo;


public class DoorStore extends Ground implements Teleportable, SummonDirt
{
    private int activeTurn = 3;
    private final Location DESTINATION;
    private static final int END = 0;
    /**
     * Constructor for DoorStore
     *
     * @param destination      A list of {@link Location} indicate this can teleport to where.
     */
    public DoorStore(Location destination)
    {
        super(GroundInfo.DOOR_STORE.getDISPLAY_CHAR(), GroundInfo.DOOR_STORE.getNAME());
        this.DESTINATION = destination;
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
        return actor + " has enter " + destination.map();
    }

    /**
     * Get a simple representation name of the current object.
     *
     * @return {@code String} name representation of this object.
     */
    @Override
    public String getSimpleName()
    {
        return this.toString();
    }

    /**
     * Ground can also experience the joy of time.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location)
    {
        if(activeTurn == END){
            location.setGround(this.summonDirt());
        }
        activeTurn--;

    }

    /**
     * Get TeleportAction for its {@code DESTINATION}.
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return {@link TeleportAction} for its {@code DESTINATION}
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location,
                                       String direction) {
        ActionList actionList = super.allowableActions(actor, location, direction);

        //Check if the destination doesn't have actor and can enter by actor.
        if ((!DESTINATION.containsAnActor()) && DESTINATION.canActorEnter(actor)) {
            //Indicate the successful rate that it can function properly.
            int SUCCESSFUL_RATE = 100;

            actionList.add(new TeleportAction(this, actor,
                    DESTINATION, SUCCESSFUL_RATE));
        }

        return actionList;
    }

    public Location getDESTINATION()
    {
        return DESTINATION;
    }
}
