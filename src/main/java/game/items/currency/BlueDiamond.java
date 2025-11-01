package game.items.currency;

import game.items.ItemInfo;

/**
 * <h1>BlueDiamond class</h1>
 * <p>
 * The {@code BlueDiamond} represents a tier of {@link Diamond} in the game's currency system.
 * Two {@code BlueDiamond}s can be combined into a higher-tier {@link RedDiamond}.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0
 * @since 2025-10-31
 */
public class BlueDiamond extends Diamond {

    /**
     * Static initializer to ensure the diamond type is registered.
     */
    static {
        new BlueDiamond().register();
    }

    /**
     * Constructor of the BlueDiamond class.
     */
    public BlueDiamond() {
        super(ItemInfo.BLUE_DIAMOND.getNAME(), ItemInfo.BLUE_DIAMOND.getCHAR(),
                ItemInfo.BLUE_DIAMOND.isPORTABLE());
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
     * @return a new RedDiamond instance
     */
    @Override
    public Diamond getNextTier() {
        return new RedDiamond();
    }

    /**
     * Getter of the diamond value.
     *
     * @return the integer value of the blue diamond
     */
    @Override
    public int getValue() {
        return Currency.BLUE.getVALUE();
    }
}
