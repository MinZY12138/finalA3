package game.items.currency;

import game.items.ItemInfo;

/**
 * <h1>GreenDiamond class</h1>
 * <p>
 * The {@code GreenDiamond} represents a tier of {@link Diamond} in the game's currency system.
 * Two {@code GreenDiamond}s can be combined into a higher-tier {@link BlueDiamond}.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0
 * @since 2025-10-31
 */
public class GreenDiamond extends Diamond {

    /**
     * Static initializer to ensure the diamond type is registered.
     */
    static {
        new GreenDiamond().register();
    }

    /**
     * Constructor of the GreenDiamond class.
     */
    public GreenDiamond() {
        super(ItemInfo.GREEN_DIAMOND.getNAME(), ItemInfo.GREEN_DIAMOND.getCHAR(),
                ItemInfo.GREEN_DIAMOND.isPORTABLE());
    }

    /**
     * Registers this diamond type in the global diamond registry.
     */
    @Override
    public void register() {
        registerNewDiamond(getClass());
    }

    /**
     * Getter of the next tier diamond.
     *
     * @return a new BlueDiamond instance
     */
    @Override
    public Diamond getNextTier() {
        return new BlueDiamond();
    }

    /**
     * Getter of the diamond value.
     *
     * @return the integer value of the green diamond
     */
    @Override
    public int getValue() {
        return Currency.GREEN.getVALUE();
    }
}
