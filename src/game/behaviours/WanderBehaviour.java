package game.behaviours;

import edu.monash.fit2099.engine.actors.*;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.positions.*;
import java.util.Random;
import java.util.ArrayList;



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
     * Returns a MoveAction to wander to a random location, if possible.
     * If no movement is possible, returns null.
     *
     * @param actor the Actor enacting the behaviour
     * @param map the map that actor is currently on
     * @return an Action, or null if no MoveAction is possible
     */
    @Override
    public Action generateAction(Actor actor, GameMap map) {
        ArrayList<Action> actions = new ArrayList<>();

        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                actions.add(exit.getDestination().getMoveAction(actor, "around", exit.getHotKey()));
            }
        }

        if (!actions.isEmpty()) {
            return actions.get(random.nextInt(actions.size()));
        }
        else {
            return null;
        }
    }
}
