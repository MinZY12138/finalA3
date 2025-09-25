package game.behaviours;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.positions.*;
import java.util.*;

/**
 * Wander behaviour that make actor to move around
 * @author Ng Jun Jie
 * @version 1.0
 */
public class WanderBehaviour implements Behaviour
{

    private final Random random = new Random();

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
