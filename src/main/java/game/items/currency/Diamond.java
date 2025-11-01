package game.items.currency;

import edu.monash.fit2099.engine.items.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * <h1>Diamond class</h1>
 * <p>
 * The {@code Diamond} represents a currency {@link Item} in the game.
 * Diamonds have multiple tiers and two same tier diamonds can be combined to get a higher tier
 * diamond.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0
 * @since 2025-10-31
 */
public abstract class Diamond extends Item implements SelfRegisterDiamond {

    /**
     * A registry of all diamond types available in the game.
     */
    private static final List<Class<? extends Diamond>> DIAMOND_TYPES = new ArrayList<>();

    /**
     * Random object for selecting a diamond at random.
     */
    private static final Random RAND = new Random();

    /**
     * Constructor of the Diamond class.
     *
     * @param name        the name of the diamond
     * @param displayChar the character representing diamond on the game map
     * @param portable    determine the diamond can be picked up
     */
    public Diamond(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * This method is used to get the next higher tier diamond.
     *
     * @return the next tier diamond, null if there is no next higher tier
     */
    public abstract Diamond getNextTier();

    /**
     * This method is used to get the value of the diamond.
     *
     * @return the value of the diamond
     */
    public abstract int getValue();

    /**
     * Register this diamond class in the diamond registry.
     * Subclasses must implement this method to ensure they can be used in random selection.
     */
    @Override
    public abstract void register();

    /**
     * Adds a new diamond type to the registry if it is not already present.
     *
     * @param diamondClass the class of the diamond to register
     */
    protected static void registerNewDiamond(Class<? extends Diamond> diamondClass) {
        if (!DIAMOND_TYPES.contains(diamondClass)) {
            DIAMOND_TYPES.add(diamondClass);
        }
    }

    /**
     * Randomly choose registered diamonds.
     *
     * @return a new instance of a randomly selected diamond
     */
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

    /**
     * Determine if this diamond can combine.
     *
     * @return true if this diamond can combine, otherwise false
     */
    public boolean canCombine() {
        return this.getNextTier() != null;
    }
}
