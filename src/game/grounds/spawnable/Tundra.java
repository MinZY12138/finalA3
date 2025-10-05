package game.grounds.spawnable;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperation;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import java.util.List;

/**
 * <h1>Class represent Tundra </h1>
 *
 * <p>
 *     A type of {@link SpawnGround} that represents a Tundra.
 *     The Tundra can occasionally spawn a specific {@link Animal} with extra resilience
 *     against warm conditions. Spawn chance is fixed at 5% per tick if the tile
 *     is unoccupied.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public class Tundra extends SpawnGround {


    /**
     * Constructs a Tundra ground tile with a list of spawnable animals.
     *
     * @param spawnable a list of {@link Spawnable} animals that can be spawned
     */
    public Tundra(List<Spawnable> spawnable){
        super('_', "Tundra", spawnable);

    }

    /**
     * Specifies the number of ticks between spawn attempts.
     *
     * @return 1 tick
     */
    @Override
    protected int getAnimalSpawnTurn() {
        return 1;
    }

    /**
     * Specifies the spawn success chance.
     *
     * @return 5
     */
    @Override
    protected int getAnimalSpawnChance() {
        return 5;
    }

    /**
     * Applies modifications to spawned animals.
     *
     * @param animal the {@link Animal} to modify
     */
    @Override
    protected void setAnimalAction (Animal animal)
    {
        animal.modifyStatsMaximum(BaseAttributes.HEALTH, ActorAttributeOperation.INCREASE, 10);

        animal.resistanceToWarm = true;
    }

}
