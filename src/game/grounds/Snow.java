package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.CoatWeaponAction;
import game.items.coat.Coatable;
import game.items.coat.SnowCoating;

/**
 * A class representing snow on the ground.
 * @author Adrian Kristanto
 *
 * Modified by: Min Zhengyuan, Shee Seng Cheng
 */
public class Snow extends Ground {

    /**
     * The constructor of the Snow class.
     */
    public Snow() {
        super(GroundInfo.SNOW.getDISPLAY_CHAR(),
                GroundInfo.SNOW.getNAME());
    }

    /**
     * Returns an Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, collection of CoatWeaponAction
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = super.allowableActions(actor, location, direction);

        // Only allow coating if actor is standing on this tile (not adjacent ones)
        if (location.containsAnActor()) {
            // Add a coat action for each coatable weapon in the inventory
            for (Coatable coatable : actor.getItemInventoryAs(Coatable.class)) {
                if (coatable.isCoatable()) {
                    if (coatable.getCoating() == null ||
                            ! (coatable.getCoating().getClass() == SnowCoating.class)) {
                        actions.add(new CoatWeaponAction(coatable, new SnowCoating()));
                    }
                }
            }
        }

        return actions;
    }
}
