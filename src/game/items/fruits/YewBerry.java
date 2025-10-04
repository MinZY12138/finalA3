package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.positions.GameMap;


/**
 * <h1>Class represent YewBerry</h1>
 *
 * <p>
 *     Represent a YewBerry in this system.
 * </p>
 *
 * Extends from {@link Fruit}
 *
 * @author Ng Jun Jie
 * @version 2.0
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




}
