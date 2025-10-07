package game.behaviours;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.positions.*;
import java.util.*;

/**
 * <h1>Class represent WanderBehaviour</h1>
 *
 * <p>
 *     A behaviour that makes an {@link Actor} wander randomly around the map.
 *     The behaviour checks all available exits from the actor’s current location,
 *     filters out blocked destinations (either not enterable or occupied by another actor),
 *     and chooses one at random to move to.
 * </p>
 *
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class WanderBehaviour implements Behaviour
{

    /**
     * Random number generator used for selecting exits.
     */
    private final Random random = new Random();

    /**
     * Generates a random movement action for the actor.
     *
     * @param actor the actor whose turn it is
     * @param map   the map the actor is currently on
     * @return a {@link MoveActorAction} to a random valid exit, or {@code null} if none available
     */
    @Override
    public Action generateAction(Actor actor, GameMap map)
    {
        Location here = map.locationOf(actor);

        // Collect valid exits
        List<Exit> possibleExits = new ArrayList<>();
        for (Exit exit : here.getExits())
        {
            Location destination = exit.getDestination();
            // Check if the destination can be entered by the actor and has no other actor
            if (destination.canActorEnter(actor) && !destination.containsAnActor())
            {
                possibleExits.add(exit);
            }
        }


        if (possibleExits.isEmpty())
        {
            return null;
        }

        // Pick a random exit
        Exit chosenExit = possibleExits.get(random.nextInt(possibleExits.size()));

        return new MoveActorAction(chosenExit.getDestination(), chosenExit.getName());
    }
}
