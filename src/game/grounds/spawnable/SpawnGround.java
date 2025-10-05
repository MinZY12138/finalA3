package game.grounds.spawnable;

import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import java.util.List;
import java.util.Random;

/**
 * <h1>Abstract Class SpawnGround</h1>
 *
 * <p>
 * Represents a type of {@link Ground} capable of spawning {@link Animal}s.
 * Subclasses such as {@link Cave}, {@code Meadow}, or {@code Tundra} define
 * the spawn rate and conditions by implementing the abstract methods
 * {@link #getAnimalSpawnTurn()} and {@link #getAnimalSpawnChance()}.
 * </p>
 *
 * <p>
 * Every fixed number of ticks (based on {@code getAnimalSpawnTurn()}),
 * the ground attempts to spawn a random {@link Animal} from the provided
 * {@link Spawnable} list with a probability determined by
 * {@code getAnimalSpawnChance()}. If successful, the {@link Animal} is
 * created and added to the {@link Location}.
 * </p>
 *
 * <p>
 * This class provides a hook method {@link #setAnimalAction(Animal)}
 * for subclasses to customise animal attributes or behaviours before
 * being added to the map.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public abstract class SpawnGround extends Ground {

    private final List<Spawnable> spawnable;
    private int turns = 0;
    private final Random random = new Random();

    /**
     * Constructor for SpawnGround.
     *
     * @param displayChar the character used to represent this ground
     * @param name the name of this ground
     * @param spawnable a list of {@link Spawnable} animals that may be spawned
     */
    public SpawnGround(char displayChar, String name, List<Spawnable> spawnable) {
        super(displayChar, name);
        this.spawnable = spawnable;
    }

    /**
     * Defines after how many ticks animals should attempt to spawn.
     *
     * @return the number of ticks between spawn attempts
     */
    protected abstract int getAnimalSpawnTurn();

    /**
     * Defines the percentage chance (0–100) that a spawn attempt succeeds.
     *
     * @return the chance of spawning an animal
     */
    protected abstract int getAnimalSpawnChance();

    /**
     * Hook method that allows subclasses to modify animals before adding them.
     * Default implementation does nothing.
     *
     * @param animal the animal about to be spawned
     */
    protected void setAnimalAction (Animal animal){}

    /**
     * Called each game tick to possibly spawn an animal.
     *
     * @param location the {@link Location} of this ground
     */
    @Override
    public void tick(Location location)
    {
        turns++;
        super.tick(location);

        if (turns % getAnimalSpawnTurn() == 0 && !location.containsAnActor()
        && random.nextInt(100) < getAnimalSpawnChance())
        {
            Spawnable pickedAnimal = spawnable.get(random.nextInt(spawnable.size()));
            Animal animal = pickedAnimal.create();
            setAnimalAction(animal);

            try{
                location.addActor(animal);
            } catch (GameEngineException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
