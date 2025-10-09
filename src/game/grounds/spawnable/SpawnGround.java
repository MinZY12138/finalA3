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
 * Represents a type of {@link Ground} capable of spawning {@link Animal}.
 * Subclasses define the spawn rate and conditions by implementing the abstract methods
 * {@link #getAnimalSpawnTurn()} and {@link #getAnimalSpawnChance()}.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public abstract class SpawnGround extends Ground {

    private final List<Spawnable> SPAWNABLE;
    private int turns = 0;
    private final Random RAND = new Random();
    private static final int RANDOM_RANGE = 100;
    private static final int FACTOR_NUMBER = 0;

    /**
     * Constructor for SpawnGround.
     *
     * @param displayChar the character used to represent this ground
     * @param name the name of this ground
     * @param spawnable a list of {@link Spawnable} animals that may be spawned
     */
    public SpawnGround(char displayChar, String name, List<Spawnable> spawnable)
    {
        super(displayChar, name);
        this.SPAWNABLE = spawnable;
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

    protected void addBehaviour(Animal animal){}

    /**
     * Called each game tick to possibly spawn an animal.
     *
     * @param location the {@link Location} of this ground
     */
    @Override
    public void tick(Location location)
    {
        turns++;

        if (turns % getAnimalSpawnTurn() == FACTOR_NUMBER && !location.containsAnActor()
        && RAND.nextInt(RANDOM_RANGE) < getAnimalSpawnChance())
        {
            Spawnable pickedAnimal = SPAWNABLE.get(RAND.nextInt(SPAWNABLE.size()));
            Animal animal = pickedAnimal.create();
            this.addBehaviour(animal);
            setAnimalAction(animal);

            try{
                location.addActor(animal);
            } catch (GameEngineException e) {
                throw new RuntimeException(e);
            }

        }

    }
}
