package game.items.fruits;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actions.CoatWeaponAction;
import game.items.equipments.coat.Coatable;
import game.items.equipments.coat.YewberryCoating;


/**
 * A yew berry class
 * @author Ng Jun Jie
 * @version 1.0
 */
public class YewBerry extends Fruit implements Consumable
{

    /**
     * Constructor for yew berry
     */
    public YewBerry()
    {
        super("yewBerry", 'x', true);

    }

    /**
     * define the effect of consuming of yew berry
     * @param actor the actor that consume the fruit
     * @param map the place where the actor at
     * @return description of the effect
     */
    @Override
    public String consume (Actor actor, GameMap map)
    {

        actor.hurt(actor.getAttribute(BaseAttributes.HEALTH));

        return ", died ";
    }

    /**
     * Return all allowable actions for this item.
     * <p>
     * Adds a {@link CoatWeaponAction} for each coatable weapon
     * in the owner's inventory using {@link YewberryCoating}.
     * </p>
     */
    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = super.allowableActions(owner, map);

        for (Item it : owner.getItemInventory()) {
            it.asCapability(Coatable.class).ifPresent(weapon -> {
                actions.add(new CoatWeaponAction(weapon, new YewberryCoating(), this));
            });
        }
        return actions;
    }




}
