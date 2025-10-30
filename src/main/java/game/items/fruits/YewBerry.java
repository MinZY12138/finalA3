package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import game.items.ItemInfo;

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
 * <p>
 * Modified by: Shee Seng Cheng, Zhengyuan Min
 */
public class YewBerry extends Fruit {

    /**
     * Constructor for yew berry
     */
    public YewBerry() {
        super(ItemInfo.YEW_BERRY.getNAME(), ItemInfo.YEW_BERRY.getCHAR(), ItemInfo.YEW_BERRY.isPORTABLE());
    }

    /**
     * define the effect of consuming of yew berry
     *
     * @param actor the actor that consume the fruit
     * @return description of the effect
     */
    @Override
    public String consume(Actor actor) {
        actor.hurt(actor.getMaximumAttribute(BaseAttributes.HEALTH));
        return ", died ";
    }
}
