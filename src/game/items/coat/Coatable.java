package game.items.coat;

/**
 * <h1>Interface represents Coatable</h1>
 *
 * <p>
 * Represents any weapon or item that can be coated with a {@link Coating}.
 * Classes implementing this interface can apply, remove, or query a coating
 * and return a name that reflects the coating applied.
 * </p>
 *
 * <p>
 * Implemented by weapons such as Axe or Bow in REQ4: Coating.
 * </p>
 *
 * @author Min Zhengyuan
 * @version 2.0
 *
 * Modified by: Shee Seng Cheng, Tay Chee Hsian
 */
public interface Coatable {

    /**
     * Get the current coating applied on this item.
     *
     * @return The {@link Coating} currently applied, or {@code null} if none.
     */
    Coating getCoating();

    /**
     * Apply a coating to this item, replacing any existing one.
     *
     * @param coating The {@link Coating} to be applied.
     */
    void setCoating(Coating coating);

    /**
     * Determine whether this item supports coating.
     * <p>
     * Default is {@code true}, but some classes (e.g., Torch) can override it to disable coating.
     * </p>
     *
     * @return {@code true} if the item can be coated, otherwise {@code false}.
     */
    default boolean isCoatable() {
        return true;
    }
}
