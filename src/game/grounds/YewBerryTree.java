package game.grounds;

import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.items.fruits.*;
import java.util.*;

/**
 * A hazelnut tree class
 * @author Ng Jun Jie
 * @version 1.0
 */
public class YewBerryTree extends Ground {

    private int turns = 0;
    private int yewBerrySpawnTurn = 1;

    /**
     * Constructor for yew berry tree
     */
    public YewBerryTree() {
        super('Y', "Yew Berry Tree");
    }

    /**
     * spawn fruit in random adjacent locations
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location)
    {
        turns++;


        if (turns % yewBerrySpawnTurn == 0)
        {
            List<Location> validPlaceNearTree = new ArrayList<>();
            Random random = new Random();

            for (Exit exit : location.getExits())
            {
                Location temlocation = exit.getDestination();
                validPlaceNearTree.add(temlocation);
            }

            Location spawnFruitPlace = validPlaceNearTree.get(random.nextInt(validPlaceNearTree.size()));
            spawnFruitPlace.addItem(new YewBerry());

        }
    }


}
