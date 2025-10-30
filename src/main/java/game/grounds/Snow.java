package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class representing snow on the ground.
 * @author Adrian Kristanto
 *
 * Modified by: Min Zhengyuan, Shee Seng Cheng
 */
public class Snow extends Ground {

    /**
     * The constructor of the Snow class.
     */
    public Snow() {
        super(GroundInfo.SNOW.getDISPLAY_CHAR(),
                GroundInfo.SNOW.getNAME());
    }
}
