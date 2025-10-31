package game.items.currency;

import game.items.ItemInfo;

public class BlueDiamond extends Diamond {

    static {
        new BlueDiamond().register();
    }

    public BlueDiamond() {
        super(ItemInfo.BLUE_DIAMOND.getNAME(), ItemInfo.BLUE_DIAMOND.getCHAR(),
                ItemInfo.BLUE_DIAMOND.isPORTABLE());
    }

    @Override
    public void register(){
        registerNewDiamond(getClass());
    }

    @Override
    public Diamond getNextTier() {
        return new RedDiamond();
    }

    @Override
    public int getValue() {
        return Currency.BLUE.getVALUE();
    }
}
