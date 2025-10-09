package game.actors.animals;

/**
 * A concrete Dear type
 * <p>
 * Initialized with fixed hitpoints and warmth level.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */
public class Deer extends Animal{

    public Deer(){
        super(AnimalInfo.DEER.getNAME(),
                AnimalInfo.DEER.getDISPLAY_CHARACTER(),
                AnimalInfo.DEER.getHIT_POINT(),
                AnimalInfo.DEER.getWARMTH_LEVEL());
    }

}
