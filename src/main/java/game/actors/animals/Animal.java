package game.actors.animals;

import edu.monash.fit2099.engine.actions.*;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actions.AttackAction;
import game.actors.Abilities;
import game.behaviours.*;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

/**
 * <h1>Abstract Class represent Animal</h1>
 *
 * <p>
 * Subclasses should define specific animal types.
 * Animals have hitpoints and warmth level, which affect their consciousness.
 * They can move randomly (wander) or consume items if available.
 * Implements {@link Warmable} for warmth-related behavior.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 *
 * Modify by: Shee Seng Cheng, Tay Chee Hsian
 */
public abstract class Animal extends Actor implements Warmable {

    /**
     * The map where the animal currently located.
     */
    private GameMap currentMap;

    /**
     * Instead of having Random instance in each child class we put it into parent class
     */
    protected final Random RAND = new Random();

    /**
     * The warmth level of the animal
     */
    private int warmthLevel;

    protected Map<Integer, Behaviour> behaviourMap = new TreeMap<>();

    private static final int WARMTH_LOWER_BOUND = 0;

    /**
     * Constructor for an animal.
     *
     * @param name        the animal's name
     * @param displayChar character representing the animal
     * @param hitpoints   initial hit points
     * @param warmthLevel initial warmth level
     */
    public Animal(String name, char displayChar, int hitpoints, int warmthLevel) {
        super(name, displayChar, hitpoints);
        this.warmthLevel = warmthLevel;

        resetBehaviourMap();
    }

    /**
     * Reset to default behaviourMap which was only has wander behaviour.
     */
    public void resetBehaviourMap()
    {
        behaviourMap.clear();
        int lowPriority = 10;
        addBehaviourToAnimal(new WanderBehaviour(), lowPriority);
    }

    /**
     * Method to add new behaviour into this animal.
     * @param behaviour the behaviour to be added.
     * @param priority the priority of this behaviour.
     */
    public void addBehaviourToAnimal(Behaviour behaviour, int priority)
    {
        this.behaviourMap.put(priority, behaviour);
    }

    /**
     * Decreases the warmth level by 1.
     */
    @Override
    public void decreaseWarmthLevel() {
        if (!this.hasAbility(Abilities.COLD_RESISTANT)) {
            this.warmthLevel--;
            if (isCold()) {
                this.warmthLevel = WARMTH_LOWER_BOUND;
            }
        }
    }

    /**
     * Checks if the animal has positive warmth.
     *
     * @return true if warmthLevel <= 0, false otherwise
     */
    @Override
    public boolean isCold() {
        return warmthLevel <= WARMTH_LOWER_BOUND;
    }

    /**
     * Called when the animal is first spawned on a location
     *
     * @param spawnGround the location where this animal spawns
     */
    public abstract void spawnCapability(Location spawnGround);

    /**
     * Determines the action for the current turn.
     *
     * @param actions    available actions for this turn
     * @param lastAction the previous action taken
     * @param map        current game map
     * @param display    the display object
     * @return the selected action
     */
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        this.currentMap = map;

        if (!isConscious() || isCold()) {
            if (isCold()) {
                display.println(this + " is unconscious due to low warmth level");
            }

            this.unconscious(currentMap);
            return new DoNothingAction();
        }

        decreaseWarmthLevel();

        //Loop through all the behaviour in the collections.
        for (Behaviour behaviour : this.behaviourMap.values()) {
            //Generate the action based on the behaviour.
            Action action = behaviour.generateAction(this, map);

            //If contain an actions return it else continue the loop
            if (action != null) {
                return action;
            }
        }
        //When all behaviour has been checked no action then return do nothing action.
        return new DoNothingAction();
    }

    /**
     * Returns a string representation including location, health, and warmth level.
     *
     * @return descriptive string for this animal
     */
    public String toString() {
        if (currentMap == null) {
            return super.toString() + " ( warmth level: " + this.warmthLevel + " ) ";
        }

        Location location = currentMap.locationOf(this);

        return super.toString() + " ( warmth level: " + this.warmthLevel + " ) at " + location;
    }

    /**
     * Returns a new collection of the Actions that the otherActor can do to the
     * current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return A collection of Actions.
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actionList = super.allowableActions(otherActor, direction, map);

        if (otherActor.hasAbility(Abilities.ATTACK)) {
            //Game rule actor can be attack by other actor using weapon.
            actionList.add(new AttackAction(this, direction,
                    "will hit", otherActor.getIntrinsicWeapon()));
        }

        return actionList;
    }
}
