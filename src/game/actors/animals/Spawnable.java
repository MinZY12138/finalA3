package game.actors.animals;

/**
 * <h1>Interface Spawnable</h1>
 *
 * <p>
 *     Represents a contract for objects that can spawn {@link Animal} instances.
 *     Classes implementing this interface act as factories, providing a way
 *     to create new {@link Animal} objects (e.g., for spawning animals in
 *     specific environments such as {@code Meadow} or {@code Tundra}).
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public interface Spawnable {
    /**
     * Creates a new animal instance.
     *
     * @return a new {@link Animal}
     */
    Animal create();
}
