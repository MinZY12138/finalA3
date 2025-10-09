package game.actors.animals;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Abilities;
import game.actors.statuses.dragon.DragonState;
import game.weapons.FireBlow;

import java.util.Random;

/**
 * <h1>Class represent Dragon</h1>
 *
 * <p>
 * Represent a type of species (Dragon) in the system.
 * Dragon has 3 different state each state has it own specific
 * action or effect can be done by the dragon.
 * </p>
 * <p>
 * Extends: {@link Animal}
 *
 * @author Ng Jun Jie
 * @version 1.0
 * <p>
 * Modify by: Shee Seng Cheng
 */
public class Dragon extends Animal {
    /**
     * Use to get random number.
     */
    private static final Random RAND = new Random();

    /**
     * Store the state of the dragon.
     */
    private DragonState currentState;

    /**
     * Indicate the turn remaining for the dragon to transform to another state.
     */
    private int remainingTurns;

    /**
     * Indicate the time to transform to a different state.
     */
    private static final int TIME_TO_TRANSFORM = 0;


    /**
     * Constructor for Dragon
     */
    public Dragon() {
        super(AnimalInfo.DRAGON.getNAME(),
                AnimalInfo.DRAGON.getDISPLAY_CHARACTER(),
                AnimalInfo.DRAGON.getHIT_POINT(),
                AnimalInfo.DRAGON.getWARMTH_LEVEL());
        this.setIntrinsicWeapon(new FireBlow());
        this.enableAbility(Abilities.ATTACK);
    }

    /**
     * Setter to set the current state and reset the remaining turn.
     *
     * @param state the next dragon state to transform this dragon to.
     */
    public void setCurrentState(DragonState state) {
        this.currentState = state;
        this.remainingTurns = state.getStateDuration();
    }

    /**
     * Determines the action for the current turn.
     *
     * @param actions    available actions for this turn
     * @param lastAction the previous action taken
     * @param map        current game map
     * @param display    the display object
     * @return the selected action
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        //Reduce the remaining turn for this state.
        remainingTurns--;
        currentState.setDragonAction(this, map);

        if (remainingTurns == TIME_TO_TRANSFORM) {
            DragonState nextState = currentState.getNextState(RAND);

            if (nextState == null || nextState.getStateName().equals(currentState.getStateName())) {
                display.println(this + " stay in current state, " + currentState.getStateName());
                remainingTurns = currentState.getStateDuration();
            } else {
                display.println(this + " switch state from " + currentState.getStateName() +
                        " to " + nextState.getStateName());
                this.setCurrentState(nextState);
            }
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String toString() {
        return super.toString() + " is on " + currentState.getStateName();
    }
}
