package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.equipments.armors.Wearable;
import game.items.equipments.armors.Wearing;

public class WearAction extends Action {

    private final Wearable ARMOR;
    private final Wearing ARMOR_HOLDER;
    private final Actor TARGET;

    public WearAction(Wearable armor, Wearing ARMOR_HOLDER, Actor target) {
        this.ARMOR = armor;
        this.ARMOR_HOLDER = ARMOR_HOLDER;
        this.TARGET = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        map.locationOf(actor).removeItem((Item) ARMOR);
        String returnString = ARMOR.wornBy(TARGET, ARMOR_HOLDER);
        return returnString;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " wears " + ARMOR.toString();
    }
}
