package game;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * <h1>Class represent Dirt</h1>
 *
 * <p>
 *     Represent dirt in the system.
 *     Dirt consider as a ground type.
 * </p>
 *
 * Extends {@link Ground}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class Dirt extends Ground
{
    /**
     * Constructor for Dirt.
     */
    public Dirt()
    {
        //Pass its parameter to its parent's constructor.
        super('+', "Dirt");
    }
}
