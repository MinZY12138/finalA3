package game.grounds.spawnable;

import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import java.util.List;

/**
 * <h1>Class represent Meadow</h1>
 *
 * <p>
 *     A type of {@link SpawnGround} that represents a Meadow.
 *     Meadows are capable of spawning different {@link Animal}s at random intervals.
 *     Every {@code animalSpawnTurn} ticks, there is a chance (50%) that
 *     a random animal from the {@code spawnable} list will be spawned in this location,
 *     provided the location does not already contain an actor.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public class Meadow extends SpawnGround {

    /**
     * Constructs a Meadow ground tile with a list of spawnable animals.
     *
     * @param spawnable a list of {@link Spawnable} animals that can be spawned
     */
    public Meadow(List<Spawnable> spawnable){
        super('w', "Meadow", spawnable);

    }

    /**
     * Specifies the number of ticks between spawn attempts.
     *
     * @return 7 ticks
     */
    @Override
    protected int getAnimalSpawnTurn()
    {
        return 7;
    }

    /**
     * Specifies the spawn success chance.
     *
     * @return 50
     */
    @Override
    protected int getAnimalSpawnChance() {
        return 50;
    }


}
