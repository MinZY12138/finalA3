package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.coat.Coatable;
import game.items.coat.Coating;

/**
 * <h1>Class represents CoatWeaponAction</h1>
 *
 * <p>
 * Represents an action that allows an {@link Actor} to apply a {@link Coating}
 * to weapons which can be coatable.
 * </p>
 *
 * @author Zhengyuan Min
 * @version 1.1
 */
public class CoatWeaponAction extends Action {

    /**
     * The weapon to be coated.
     */
    private final Coatable WEAPON;

    /**
     * The coating to apply on the weapon.
     */
    private final Coating COATING;

    /**
     * The consumable item that provides the coating.
     * If null, this action does not consume any item.
     */
    private final Item CONSUMED_ITEM;

    /**
     * Constructor for coating using item in bag.
     * <p>
     * Used when the coating is applied using a consumable item.
     * </p>
     *
     * @param weapon       The weapon to be coated.
     * @param coating      The coating to apply.
     * @param consumedItem The consumable item used for coating.
     */
    public CoatWeaponAction(Coatable weapon, Coating coating, Item consumedItem) {
        this.WEAPON = weapon;
        this.COATING = coating;
        this.CONSUMED_ITEM = consumedItem;
    }

    /**
     * Constructor for coating from ground
     * <p>
     * Used when the coating is applied from a ground source (e.g., Snow).
     * No item will be consumed.
     * </p>
     *
     * @param weapon  The weapon to be coated.
     * @param coating The coating to apply.
     */
    public CoatWeaponAction(Coatable weapon, Coating coating) {
        this(weapon, coating, null);
    }

    /**
     * Perform the coating action
     * remove the item from inventory if used for coating
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of the coating action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Apply or replace the coating on the weapon
        WEAPON.setCoating(COATING);

        // Remove the consumable item from inventory if provided
        if (CONSUMED_ITEM != null) {
            actor.removeItemFromInventory(CONSUMED_ITEM);
        }
        return menuDescription(actor);
    }

    /**
     * Describe what action will be performed if this Action is chosen in the menu.
     *
     * @param actor The actor performing the action.
     * @return The description to be displayed on the menu.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " coats " + WEAPON + " with " + COATING.getName();
    }
}

