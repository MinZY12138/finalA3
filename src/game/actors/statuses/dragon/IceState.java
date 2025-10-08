package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;

public class IceState extends StateOfDragon{
    public void setDragonAction(Animal dragon, GameMap map) {
        dragon.resistanceToWarm = true;
    }
}
