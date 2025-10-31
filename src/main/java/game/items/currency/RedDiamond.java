package game.items.currency;

import game.items.ItemInfo;

public class RedDiamond extends Diamond {

    static {
        new RedDiamond().register();
    }

    public RedDiamond() {
        super(ItemInfo.RED_DIAMOND.getNAME(), ItemInfo.RED_DIAMOND.getCHAR(),
                ItemInfo.RED_DIAMOND.isPORTABLE());
    }

    @Override
    public Diamond getNextTier() {
        return null;
    }

    @Override
    public int getValue() {
        return Currency.RED.getVALUE();
    }

    @Override
    public void register() {
        registerNewDiamond(getClass());
    }
}
