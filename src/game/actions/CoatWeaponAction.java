package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.equipments.coat.Coatable;
import game.items.equipments.coat.Coating;

/**
 * Action to coat a weapon with a specific coating.
 * <p>
 * Design:
 * - The caller supplies the exact {@link Coating} to apply.
 * - If a consumable item is provided, it will be removed from the actor's inventory.
 * - This avoids any instanceof checks and keeps mapping "item -> coating" outside this class.
 */
public class CoatWeaponAction extends Action {

    private final Coatable weapon;     // weapon to be coated (e.g., Axe, Bow)
    private final Coating coating;     // the coating to apply (e.g., YewberryCoating, SnowCoating)
    private final Item consumedItem;   // optional: item to consume (e.g., Yewberry). null means no consumption.

    /**
     * Create an action to coat a weapon using a consumable item (e.g., Yewberry).
     * The item will be removed from the actor's inventory if the action succeeds.
     *
     * @param weapon       the weapon to coat
     * @param coating      the coating to apply
     * @param consumedItem the item to consume (removed from inventory)
     */
    public CoatWeaponAction(Coatable weapon, Coating coating, Item consumedItem) {
        this.weapon = weapon;
        this.coating = coating;
        this.consumedItem = consumedItem;
    }

    /**
     * Create an action to coat a weapon from a ground source (e.g., Snow).
     * No item will be consumed.
     *
     * @param weapon  the weapon to coat
     * @param coating the coating to apply
     */
    public CoatWeaponAction(Coatable weapon, Coating coating) {
        this(weapon, coating, null);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // Apply/replace coating
        weapon.setCoating(coating);

        // Consume inventory item if provided
        if (consumedItem != null) {
            actor.removeItemFromInventory(consumedItem);
        }

        // Build a readable message.
        // All your coatable weapons extend Item (LootWeapon), so this cast is valid in your project.
        String weaponName = ((Item) weapon).toString();
        return actor + " coats " + weaponName + " with " + coating.name();
    }

    @Override
    public String menuDescription(Actor actor) {
        String weaponName = ((Item) weapon).toString();
        return "Coat " + weaponName + " with " + coating.name();
    }
}
