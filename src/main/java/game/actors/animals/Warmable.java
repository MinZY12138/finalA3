package game.actors.animals;

/**
 * <h1>Interface Warmable</h1>
 *
 * <p>
 *     Represents an actor that is affected by warmth or coldness.
 *     Define how their warmth level decreases
 *     over time and provide a way to check whether they have become cold.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public interface Warmable {

    /**
     * Decrease the actor's warmth level
     */
    void decreaseWarmthLevel();

    /**
     * Determines if the actor is considered cold.
     *
     * @return {@code true} if the actor's warmth level equal or lower than 0,
     * otherwise {@code false}
     */
    boolean isCold();

}
