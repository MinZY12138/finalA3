package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;
import game.items.ItemInfo;

/**
 * /**
 * <h1>Class represent Apple</h1>
 *
 * <p>
 * Represent an Apple in this system.
 * </p>
 * <p>
 * Extends from {@link Fruit}
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Apple extends Fruit {

    /**
     * Constructor for apple
     */
    public Apple() {
        super(ItemInfo.APPLE.getNAME(), ItemInfo.APPLE.getCHAR(), ItemInfo.APPLE.isPORTABLE());
    }

    /**
     * state the effect of the apple.
     *
     * @param consumer the actor consuming the apple
     * @return a string describing the effect
     */
    @Override
    public String consume(Actor consumer) {
        consumer.heal(3);
        return " and heal with 3 hit points ";
    }
}
