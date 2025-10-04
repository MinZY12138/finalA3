package game.actors.animals;

/**
 * Interface for objects that can spawn animals.
 */
public interface Spawnable {
    /**
     * Creates a new animal instance.
     *
     * @return a new {@link Animal}
     */
    Animal create();
}
