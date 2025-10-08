package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.AttackAction;

public class SplashBehaviour implements Behaviour {

    /**
     * Implements generateAction method.
     * This method will generate an AttackAction to
     * other actor if and only if its surrounding contain actor.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return an {@code AttackAction || null} to its surrounding actor
     * only can attack 1 actor at a time.
     */
    @Override
    public Action generateAction(Actor actor, GameMap map)
    {
        //Loop through its surrounding
        for (Exit exit: map.locationOf(actor).getExits())
        {
            //If the location has an actor then attack that actor.
            if (exit.getDestination().containsAnActor())
            {
                return new AttackAction(exit.getDestination().getActor(), exit.getName(),
                        " splash fire by ", actor.getIntrinsicWeapon());
            }
        }
        return null;
    }


}
