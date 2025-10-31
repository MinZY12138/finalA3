package game.items.currency;

import game.items.ItemInfo;

/**
 * <h1>RedDiamond class</h1>
 * <p>
 * The {@code RedDiamond} represents a tier of {@link Diamond} in the game's currency system.
 * The {@code RedDiamond} is the highest tier of diamond, this tier of diamond cannot be
 * combined anymore.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0
 * @since 2025-10-31
 */
public class RedDiamond extends Diamond {

    /**
     * Static initializer to ensure the diamond type is registered.
     */
    static {
        new RedDiamond().register();
    }

    /**
     * Constructor of the GreenDiamond class.
     */
    public RedDiamond() {
        super(ItemInfo.RED_DIAMOND.getNAME(), ItemInfo.RED_DIAMOND.getCHAR(),
                ItemInfo.RED_DIAMOND.isPORTABLE());
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
     * @return null
     */
    @Override
    public Diamond getNextTier() {
        return null;
    }

    /**
     * Getter of the diamond value.
     *
     * @return the integer value of the red diamond
     */
    @Override
    public int getValue() {
        return Currency.RED.getVALUE();
    }
}
