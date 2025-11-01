package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.equipments.armors.Wearable;
import game.items.equipments.armors.Wearing;

/**
 * <h1>WearAction class</h1>
 * <p>
 * The {@code WearAction} represents an {@link Action} that allows {@link Actor}
 * to wear a wearable item.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public class WearAction extends Action
{
    /**
     * The armor item to be worn.
     */
    private final Wearable ARMOR;

    /**
     * The armor holder responsible for managing the worn armor state.
     */
    private final Wearing ARMOR_HOLDER;

    /**
     * The actor who wear the armor.
     */
    private final Actor TARGET;

    /**
     * Constructor of the WearAction class.
     *
     * @param armor        the armor item
     * @param ARMOR_HOLDER the object managing equipped armors
     * @param target       the actor who wear the armor
     */
    public WearAction(Wearable armor, Wearing ARMOR_HOLDER, Actor target)
    {
        this.ARMOR = armor;
        this.ARMOR_HOLDER = ARMOR_HOLDER;
        this.TARGET = target;
    }

    /**
     * Executes the wear action by removing the armor from the map and
     * equipping it onto the actor.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a string message describing the outcome of the action
     */
    @Override
    public String execute(Actor actor, GameMap map)
    {
        map.locationOf(actor).removeItem((Item) ARMOR);
        return ARMOR.wornBy(TARGET, ARMOR_HOLDER);
    }

    /**
     * Describe a human-readable message of the action to display in the menu.
     *
     * @param actor The actor performing the action.
     * @return a string description of the action in the menu
     */
    @Override
    public String menuDescription(Actor actor)
    {
        return actor + " wears " + ARMOR.toString();
    }
}
