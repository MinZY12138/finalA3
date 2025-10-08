package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.animals.Animal;
import game.behaviours.SplashBehaviour;

public class FireState extends StateOfDragon{
    public void setDragonAction(Animal dragon, GameMap map) {
        dragon.resistanceToWarm = false;
        int HIGHEST_PRIORITY = 0;
        //Game rule attack has the most priority in the system.
        dragon.addBehaviourToAnimal(new SplashBehaviour(), HIGHEST_PRIORITY);

    }
}
