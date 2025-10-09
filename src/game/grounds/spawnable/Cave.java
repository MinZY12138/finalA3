package game.grounds.spawnable;

import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import java.util.List;

/**
 * <h1>Class represent Cave</h1>
 *
 * <p>
 *     A type of {@link SpawnGround} that represents a Cave.
 *     Cave are capable of spawning different {@link Animal}s at random intervals.
 *     Every {@code animalSpawnTurn} ticks, there is an equal chance that
 *     a random animal from the {@code spawnable} list will be spawned in this location,
 *     provided the location does not already contain an actor.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public class Cave extends SpawnGround {

    /**
     * Constructs a Cave ground tile with a list of spawnable animals.
     *
     * @param spawnable a list of {@link Spawnable} animals that can be spawned
     */
    public Cave(List<Spawnable> spawnable)
    {
        super('C', "Cave",spawnable);
        setSpawnChance(SpawnableInfo.CAVE_SPAWN_CHANCE.getINFO());
        setSpawnTurn(SpawnableInfo.CAVE_SPAWN_TURN.getINFO());
    }
}
