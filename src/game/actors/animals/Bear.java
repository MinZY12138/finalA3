package game.actors.animals;

/**
 * A concrete Bear type
 * <p>
 * Initialized with fixed hitpoints and warmth level.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Bear extends Animal {

    public Bear() {
        super(AnimalInfo.BEAR.getNAME(),
                AnimalInfo.BEAR.getDISPLAY_CHARACTER(),
                AnimalInfo.BEAR.getHIT_POINT(),
                AnimalInfo.BEAR.getWARMTH_LEVEL());
    }
}
