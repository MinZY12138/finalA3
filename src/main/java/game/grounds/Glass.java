package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.actors.Actor;

/**
 * <h1>Glass Ground</h1>
 * <p>
 * The {@code Glass} ground represents the transparent walls that outline
 * store displays. It prevents actors from stepping onto the tile while still
 * letting items be showcased on top of it.
 * </p>
 */
public class Glass extends Ground {

    /**
     * Constructor for {@link Glass}.
     */
    public Glass()
    {
        super(GroundInfo.GLASS.getDISPLAY_CHAR(), GroundInfo.GLASS.getNAME());
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }
}