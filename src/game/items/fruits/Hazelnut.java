package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperation;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * <h1>Class represent Hazelnut</h1>
 *
 * <p>
 *     Represent a Hazelnut in this system.
 * </p>
 *
 * Extends from {@link Fruit}
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Hazelnut extends Fruit
{

    /**
     * Constructor for hazelnut
     */
    public Hazelnut()
    {
        super("Hazelnut", 'n', true);

    }

    /**
     * define the effect of consuming of hazelnut
     * @param consumer the actor that consume the fruit
     * @param map the place where the actor at
     * @return description of the effect
     */
    @Override
    public String consume (Actor consumer, GameMap map)
    {

        consumer.modifyStatsMaximum(BaseAttributes.HEALTH, ActorAttributeOperation.INCREASE, 1);

        return ", increase the max hit points by 1";
    }





}
