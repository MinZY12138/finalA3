package game.items.currency;

import game.items.ItemInfo;

public class GreenDiamond extends Diamond {

    static {
        new GreenDiamond().register();
    }

    public GreenDiamond() {
        super(ItemInfo.GREEN_DIAMOND.getNAME(), ItemInfo.GREEN_DIAMOND.getCHAR(),
                ItemInfo.GREEN_DIAMOND.isPORTABLE());
    }

    @Override
    public Diamond getNextTier() {
        return new BlueDiamond();
    }

    @Override
    public int getValue() {
        return Currency.GREEN.getVALUE();
    }

    @Override
    public void register() {
        registerNewDiamond(getClass());
    }
}
