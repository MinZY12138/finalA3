package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;



/**
 * An apple class
 * @author Ng Jun Jie
 * @version 1.0
 */
public class Apple extends game.items.fruits.Fruit implements Consumable
{

    /**
     * Constructor for apple
     */
    public Apple()
    {
        super("apple", 'a', true);
        this.enableAbility(game.Items.Abilities.HEAL);
    }

    /**
     * state the effect of the apple.
     *
     * @param consumer the actor consuming the apple
     * @param map the game map the actor is on
     * @return a string describing the effect
     */
    @Override
    public String consume(Actor consumer, GameMap map) {

        consumer.heal(3);
        return " and heal with 3 points ";

    }



}
