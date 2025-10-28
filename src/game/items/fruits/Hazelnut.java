package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperation;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import game.items.ItemInfo;

/**
 * <h1>Class represent Hazelnut</h1>
 *
 * <p>
 * Represent a Hazelnut in this system.
 * </p>
 * <p>
 * Extends from {@link Fruit}
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Hazelnut extends Fruit {

    /**
     * Constructor for hazelnut
     */
    public Hazelnut() {
        super(ItemInfo.HAZELNUT.getNAME(), ItemInfo.HAZELNUT.getCHAR(), ItemInfo.HAZELNUT.isPORTABLE());
    }

    /**
     * define the effect of consuming of hazelnut
     *
     * @param consumer the actor that consume the fruit
     * @return description of the effect
     */
    @Override
    public String consume(Actor consumer) {

        consumer.modifyStatsMaximum(BaseAttributes.HEALTH, ActorAttributeOperation.INCREASE, 1);

        return ", increase the max hit points by 1";
    }
}
