package game.items.equipments.armors;

/**
 * <h1>Wearing Interface</h1>
 * <p>
 * The {@code Wearing} interface defines the contract any object that manages an {@link Armor}
 * equipped by the actor. It provides the essential methods required to equip, access, and retrieve information
 * about the currently worn armor.
 * </p>
 *
 * @author Shee Seng Cheng
 * @version 1.0.0
 * @since 2025-11-01
 */
public interface Wearing
{

    /**
     * Assign the given armor to the holder.
     *
     * @param armor the armor to wear
     * @return a message describing the result of wearing the armor
     */
    String setArmor(Armor armor);

    /**
     * Retrieves detailed information about the currently worn armor.
     *
     * @return a descriptive string for the worn armor
     */
    String getArmorInfo();

    /**
     * Returns the defensive capability of the currently worn armor.
     *
     * @return the amount of damage the worn armor can block
     */
    int getBlockArmor();

    /**
     * Returns a simplified summary of the currently worn armor,
     * typically including its name and defensive properties.
     *
     * @return a short summary of the armor’s basic information
     */
    String getSimpleArmorInfo();
}
