package game.items.fruits;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.positions.GameMap;


/**
 * A yew berry class
 * @author Ng Jun Jie
 * @version 1.0
 */
public class YewBerry extends game.items.fruits.Fruit implements Consumable
{

    /**
     * Constructor for yew berry
     */
    public YewBerry()
    {
        super("yewBerry", 'x', true);
        this.enableAbility(game.Items.Abilities.POISON);
    }

    /**
     * define the effect of consuming of yew berry
     * @param actor the actor that consume the fruit
     * @param map the place where the actor at
     * @return description of the effect
     */
    public String consume (Actor actor, GameMap map)
    {

        actor.hurt(actor.getAttribute(BaseAttributes.HEALTH));

        return ", died ";
    }




}
