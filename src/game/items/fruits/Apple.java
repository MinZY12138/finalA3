package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;

/**
 /**
 * <h1>Class represent Apple</h1>
 *
 * <p>
 *     Represent an Apple in this system.
 * </p>
 *
 * Extends from {@link Fruit}
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Apple extends Fruit
{

    /**
     * Constructor for apple
     */
    public Apple()
    {
        super("Apple", 'a', true);

    }

    /**
     * state the effect of the apple.
     *
     * @param consumer the actor consuming the apple
     * @return a string describing the effect
     */
    @Override
    public String consume(Actor consumer)
    {
        consumer.heal(3);
        return " and heal with 3 hit points ";
    }



}
