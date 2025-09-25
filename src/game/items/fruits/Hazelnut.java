package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperation;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A hazelnut class
 * @author Ng Jun Jie
 * @version 1.0
 */
public class Hazelnut extends Fruit implements Consumable
{

    /**
     * Constructor for hazelnut
     */
    public Hazelnut()
    {
        super("hazelnut", 'n', true);

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
