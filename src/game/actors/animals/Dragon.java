package game.actors.animals;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.abilities.Abilities;
import game.actors.statuses.dragon.DragonState;
import game.actors.statuses.dragon.DirtState;
import game.weapons.FireBlow;

public class Dragon extends Animal {

    private DragonState currentState;
    private int remainingTurns;


    public Dragon(){
        super("Dragon", 'Q', 500, 10);
        this.currentState = new DirtState();
        this.remainingTurns = currentState.setStateDuration();
        this.setIntrinsicWeapon(new FireBlow());
        this.enableAbility(Abilities.ATTACK);

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        remainingTurns --;

        currentState.setDragonAction(this, map);

        if (remainingTurns <= 0){
            DragonState nextState = currentState.getNextState();
            if (nextState != currentState)
            {
                display.println(this + " switch state from " + currentState.getStateName() +
                        " to " + nextState.getStateName());
            }
            currentState = nextState;
            remainingTurns = currentState.setStateDuration();



        }

        return super.playTurn(actions, lastAction, map, display);
    }

    @Override
    public String toString(){
        return super.toString() + " is on " + currentState.getStateName();
    }
}
