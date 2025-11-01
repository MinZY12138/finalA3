package game.items.currency;

/**
 * <h1>Currency Enumeration</h1>
 * <p>
 *     The {@code Currency} enum represents the value of each diamond tier.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0
 * @since 2025-10-31
 */
public enum Currency {

    GREEN(5),
    BLUE(10),
    RED(20);

    /**
     * The value of diamonds.
     */
    private final int VALUE;

    /**
     * Constructor of the Currency class.
     *
     * @param value the value of diamonds
     */
    Currency(int value) {
        this.VALUE = value;
    }

    /**
     * Getter of the value of diamonds.
     *
     * @return diamond value
     */
    public int getVALUE() {
        return VALUE;
    }
}
