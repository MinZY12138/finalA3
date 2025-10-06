package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CoatWeaponAction;
import game.items.coat.Coatable;
import game.items.coat.SnowCoating;

/**
 * A class representing snow on the ground.
 * @author Adrian Kristanto
 */
public class Snow extends Ground {
    public Snow() {
        super('.', "Snow");
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);

        // Add a coat action for each coatable weapon in the inventory
        for (Item it : actor.getItemInventory()) {
            it.asCapability(Coatable.class).ifPresent(weapon -> {
                actions.add(new CoatWeaponAction(weapon, new SnowCoating()));
            });
        }
        return actions;
    }
}
