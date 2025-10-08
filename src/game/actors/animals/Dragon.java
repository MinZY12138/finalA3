package game.actors.animals;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.statuses.dragon.DragonState;
import game.actors.statuses.dragon.FireState;

public class Dragon extends Animal {

    private DragonState currentState;


    public Dragon(){
        super("Dragon", 'Q', 500, 10);
        this.currentState = new FireState();

    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        DragonState nextState = currentState.getNextState();
        if (nextState != currentState){
            display.println(this + " switch state from " + currentState.getStateName() +
                    " to " + nextState.getStateName());
        }
        currentState = nextState;

        return super.playTurn(actions, lastAction, map, display);
    }

    public String toString(){
        return super.toString() + " is on " + currentState.getStateName();
    }
}
