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
 * to a {@link Coatable} weapon. The coating can come from either a consumable item
 * (e.g., Yewberry) or an environmental source (e.g., Snow).
 * </p>
 *
 * <p>
 * Extends {@link Action}
 * </p>
 *
 * @author Zhengyuan Min
 * @version 1.0
 */
public class CoatWeaponAction extends Action {

    /**
     * The weapon to be coated.
     */
    private final Coatable weapon;

    /**
     * The coating to be applied on the weapon.
     */
    private final Coating coating;

    /**
     * The consumable item that provides the coating.
     * If null, this action does not consume any item.
     */
    private final Item consumedItem;

    /**
     * Constructor for CoatWeaponAction.
     * <p>
     * Used when the coating is applied using a consumable item.
     * </p>
     *
     * @param weapon       The weapon to be coated.
     * @param coating      The coating to apply.
     * @param consumedItem The consumable item used for coating.
     */
    public CoatWeaponAction(Coatable weapon, Coating coating, Item consumedItem) {
        this.weapon = weapon;
        this.coating = coating;
        this.consumedItem = consumedItem;
    }

    /**
     * Constructor for CoatWeaponAction.
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
     * Perform the coating action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of the coating action.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Apply or replace the coating on the weapon
        weapon.setCoating(coating);

        // Remove the consumable item from inventory if provided
        if (consumedItem != null) {
            actor.removeItemFromInventory(consumedItem);
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
        return actor + " coats " + weapon + " with " + coating.getName();
    }
}

