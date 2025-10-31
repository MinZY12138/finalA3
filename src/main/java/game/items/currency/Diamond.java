package game.items.currency;

import edu.monash.fit2099.engine.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Diamond extends Item implements SelfRegisterDiamond {

    private static final List<Class<? extends Diamond>> DIAMOND_TYPES = new ArrayList<>();

    private static final Random RAND = new Random();

    public Diamond(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    public abstract Diamond getNextTier();

    public abstract int getValue();

    @Override
    public abstract void register();

    protected static void registerNewDiamond(Class<? extends Diamond> diamondClass) {
        if (!DIAMOND_TYPES.contains(diamondClass)) {
            DIAMOND_TYPES.add(diamondClass);
        }
    }

    public static Diamond getRandomDiamond() {
        if (DIAMOND_TYPES.isEmpty()) {
            SelfRegisterDiamond.ensureRegistered();
        }
        Class<? extends Diamond> chosenClass = DIAMOND_TYPES.get(
                RAND.nextInt(DIAMOND_TYPES.size())
        );

        Diamond chosenDiamond = null;
        try {
            chosenDiamond = chosenClass.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return chosenDiamond;
    }

    public boolean canCombine() {
        return this.getNextTier() != null;
    }
}
