package game.items.fruits;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.CoatWeaponAction;
import game.items.coat.Coatable;
import game.items.coat.YewBerryCoating;

/**
 * <h1>Class represent YewBerry</h1>
 *
 * <p>
 * Represent a YewBerry in this system.
 * </p>
 * <p>
 * Extends from {@link Fruit}
 *
 * @author Ng Jun Jie
 * @version 2.0
 *
 * Modified by: Shee Seng Cheng, Zhengyuan Min
 */
public class YewBerry extends Fruit  {

    /**
     * Constructor for yew berry
     */
    public YewBerry() {
        super("Yew Berry", 'x', true);
    }

    /**
     * define the effect of consuming of yew berry
     *
     * @param actor the actor that consume the fruit
     * @return description of the effect
     */
    @Override
    public String consume(Actor actor) {
        actor.hurt(actor.getAttribute(BaseAttributes.HEALTH));
        return ", died ";
    }

    /**
     * Get all actions that the owner can perform with this item.
     * Add {@link CoatWeaponAction} if the owner has a {@link Coatable} weapon that can be coated.
     *
     * @param owner the actor holding this item
     * @param map   the map where the actor is located
     * @return a list of actions that can be performed
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);

        for (Coatable coatable : owner.getItemInventoryAs(Coatable.class)) {
            if (coatable.isCoatable()) {
                if (coatable.getCoating() == null ||
                ! (coatable.getCoating().getClass() == YewBerryCoating.class)) {
                    actions.add(new CoatWeaponAction(coatable, new YewBerryCoating(), this));
                }
            }
        }

        return actions;
    }
}
