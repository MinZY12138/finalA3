package game.items.equipments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

import java.util.List;

/**
 * <h1>Bow class</h1>
 * <p>
 * The {@code Bow} is a {@link LootWeapon}.
 * It can attack {@link Actor} from a distance.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-09-24
 */
public class Bow extends LootWeapon {

    /**
     * Weapon damage.
     */
    private static final int HIT_DMG = 5;

    /**
     * Rate of hitting target actors.
     */
    private static final int HIT_RATE = 25;

    /**
     * Attack distance.
     */
    private static final int RADIUS = 3;

    /**
     * The constructor of the Bow class.
     */
    public Bow() {
        super("bow", 'c', true, HIT_DMG, HIT_RATE, "shoots");
    }

    /**
     * Represent the bow what action is allowable.
     *
     * @param owner the actor that owns the item
     * @param map   the map where the actor is performing the action on
     * @return a list of actions
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        List<Location> locations = map.locationOf(owner).getNearbyLocations(RADIUS);

        for (Location location : locations) {
            if (location.containsAnActor()) {
                actions.add(new AttackAction(location.getActor(), location.toString(),
                        this.VERB, this));
                return actions;
            }
        }

        return actions;
    }
}
