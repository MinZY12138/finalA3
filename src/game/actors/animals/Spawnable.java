package game.actors.animals;

/**
 * <h1>Interface Spawnable</h1>
 *
 * <p>
 *     Represents a contract for objects that can spawn {@link Animal}.
 *     Classes implementing this interface act as factories, providing a way
 *     to create new {@link Animal} objects
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public interface Spawnable
{
    /**
     * Creates a new animal instance.
     *
     * @return a new {@link Animal}
     */
    Animal create();
}
