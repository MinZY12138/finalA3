package game.actors.animals;

/**
 * Interface for actors that are affected by temperature/warmth.
 */
public interface Warmable {

    /**
     * Decrease the actor's warmth level
     */
    void decreaseWarmthLevel();

    /**
     * Checks if the actor is warm
     */
    boolean isCold();

}
