package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;

public interface DragonState {
    String getStateName();

    DragonState getNextState();

    void setDragonAction(Animal dragon, GameMap map);

    int setStateDuration();



}
