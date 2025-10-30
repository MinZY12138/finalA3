package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

/**
 * <h1>AttackBehaviour Class</h1>
 * <p>
 *      The {@code AttackBehaviour} is a {@link Behaviour} that allows an {@link Actor}
 *      to automatically attack another {@link Actor} located in surrounding of specific radius
 *      If a nearby location contains another actor, it generates an {@link AttackAction}
 *      using the attacking actor’s intrinsic weapon.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public class AttackBehaviour implements Behaviour {

    /**
     * Generates an {@link Action} for the actor to attack any nearby target.
     *
     * @param actor the actor performing the behaviour
     * @param map   the game map where the actor is located
     * @return an {@link AttackAction} if an actor is found, otherwise {@code null}
     */
    public Action generateAction(Actor actor, GameMap map)
    {
        //Loop through its surrounding
        for (Exit exit: map.locationOf(actor).getExits())
        {
            //If the location has an actor then attack that actor.
            if (exit.getDestination().containsAnActor())
            {
                return new AttackAction(exit.getDestination().getActor(), exit.getName(), "bites", actor.getIntrinsicWeapon());
            }
        }
        return null;
    }
}
