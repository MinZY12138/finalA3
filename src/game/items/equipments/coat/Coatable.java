package game.items.equipments.coat;

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
 * @version 1.0
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
     * Remove the current coating from this item.
     */
    void clearCoating();

    /**
     * Get the display name of this item including coating information.
     *
     * @return A string representing the coated item's name.
     */
    String coatedName();
}
