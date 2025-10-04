package game.actors.animals;

/**
 * A concrete Wolf type
 * <p>
 * Initialized with fixed hitpoints and warmth level.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Wolf extends Animal{

    public Wolf(){
        super("Wolf", 'e', 100, 25);
    }
}
