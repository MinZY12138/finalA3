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
        super(AnimalInfo.WOLF.getNAME(),
                AnimalInfo.WOLF.getDISPLAY_CHARACTER(),
                AnimalInfo.WOLF.getHIT_POINT(),
                AnimalInfo.WOLF.getWARMTH_LEVEL());
    }
}
