package game.items.equipments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.AttackAction;

import java.util.List;

public class Bow extends LootWeapon {

    private static final int HIT_DMG = 5;

    private static final int HIT_RATE = 25;

    private static final int RADIUS = 3;

    public Bow() {
        super("bow", 'c', true, HIT_DMG, HIT_RATE, "shoots");
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);
        List<Location> locations = map.locationOf(owner).getNearbyLocations(RADIUS);

        for (Location location : locations) {
            if (location.containsAnActor()) {
                actions.add(new AttackAction(location.getActor(), location.toString(),
                        this.VERB,this));
                return actions;
            }
        }

        return actions;
    }
}
