package game.grounds.spawnable;

import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import java.util.List;
import java.util.Random;

/**
 * <h1>Class represent Meadow</h1>
 *
 * <p>
 *     A type of {@link Ground} that represents a Meadow.
 *     Meadows are capable of spawning different {@link Animal}s at random intervals.
 *     Every {@code animalSpawnTurn} ticks, there is a chance (50%) that
 *     a random animal from the {@code spawnable} list will be spawned in this location,
 *     provided the location does not already contain an actor.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public class Meadow extends Ground {

    private int turns = 0;
    final int animalSpawnTurn = 7;
    private final List<Spawnable> spawnable;
    private final Random random = new Random();

    /**
     * Constructs a Meadow ground tile with a list of spawnable animals.
     *
     * @param spawnable a list of {@link Spawnable} animals that can be spawned
     */
    public Meadow(List<Spawnable> spawnable){
        super('w', "Meadow");
        this.spawnable = spawnable;
    }

    /**
     * Called once per turn to update the state of this Meadow.
     *
     * @param location the location of this Meadow on the map
     */
    @Override
    public void tick(Location location)
    {
        turns++;
        super.tick(location);

        if (random.nextInt(100) < 50 && turns % animalSpawnTurn == 0 && !location.containsAnActor())
        {
            Spawnable pickedAnimal = spawnable.get(random.nextInt(spawnable.size()));
            Animal animal = pickedAnimal.create();

            try
            {
                location.addActor(animal);
            }

            catch (GameEngineException e)
            {
                throw new RuntimeException(e);
            }

        }

    }
}
