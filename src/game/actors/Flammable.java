package game.actors;

/**
 * <h1>Interface Flammable</h1>
 *
 * <p>
 *     Representing flammable object in the system.
 *     Class that implement this should define how they
 *     react with when the object is being burned.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public interface Flammable
{
    /**
     * Define a behaviour when the object is being burned.
     * @param damage the amount of damage cause by this burn.
     */
    void burn(int damage);
}
