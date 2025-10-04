package game.actors.animals;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.behaviours.*;

/**
 * <h1>Abstract Class represent Animal</h1>
 *
 * <p>
 *     Subclasses should define specific animal types.
 *     Animals have hitpoints and warmth level, which affect their consciousness.
 *     They can move randomly (wander) or consume items if available.
 *     Implements {@link Warmable} for warmth-related behavior.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public abstract class Animal extends Actor implements Warmable {

    /**
     * The map where the animal currently located.
     */
    private GameMap currentMap;

    /**
     * The warmth level of the animal
     */
    private int warmthLevel;

    /**
     * Check whether the animal resistance to warm
     */
    public boolean resistanceToWarm;


    private final WanderBehaviour wanderBehaviour = new WanderBehaviour();
    private final ConsumeBehaviour consumeBehaviour = new ConsumeBehaviour();

    /**
     * Constructor for an animal.
     *
     * @param name the animal's name
     * @param displayChar character representing the animal
     * @param hitpoints initial hit points
     * @param warmthLevel initial warmth level
     */
    public Animal(String name, char displayChar, int hitpoints, int warmthLevel)
    {
        super(name, displayChar, hitpoints);
        this.warmthLevel = warmthLevel;
        this.resistanceToWarm = false;
    }

    /**
     * Decreases the warmth level by 1.
     */
    @Override
    public void decreaseWarmthLevel()
    {
        this.warmthLevel -= 1;
    }

    /**
     * Checks if the animal has positive warmth.
     *
     * @return true if warmthLevel <= 0, false otherwise
     */
    @Override
    public boolean isCold(){
        return warmthLevel <= 0;
    }

    /**
     * Determines the action for the current turn.
     *
     * @param actions available actions for this turn
     * @param lastAction the previous action taken
     * @param map current game map
     * @param display the display object
     * @return the selected action
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display)
    {
        this.currentMap = map;

        if (!isConscious())
        {
            if (!isCold())
            {
                display.println(this + " is unconscious due to warmth level");
            }

            this.unconscious(currentMap);
            return new DoNothingAction();

        }

        if (!resistanceToWarm)
        {
            decreaseWarmthLevel();
        }

        Action consumeAction = consumeBehaviour.generateAction(this, map);
        if (consumeAction != null)
        {
            return consumeAction;
        }
        return wanderBehaviour.generateAction(this, map);

    }

    /**
     * Returns a string representation including location, health, and warmth level.
     *
     * @return descriptive string for this animal
     */
    public String toString()
    {

        Location location = currentMap.locationOf(this);

        return "At "+ location + ", " + super.toString() + " ( warmth level: " + this.warmthLevel + " ) ";


    }
}
