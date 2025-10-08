package game.actors.statuses.dragon;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Animal;
import game.grounds.Dirt;

public class DirtState extends StateOfDragon{
    public void setDragonAction(Animal dragon, GameMap map) {
        Location here = map.locationOf(dragon);
        dragon.resistanceToWarm = false;

        for (Exit exit : here.getExits()) {
            Location surrounding = exit.getDestination();
            surrounding.setGround(new Dirt());
        }
    }
}
