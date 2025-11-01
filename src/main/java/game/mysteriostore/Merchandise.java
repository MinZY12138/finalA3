package game.mysteriostore;

import edu.monash.fit2099.engine.items.Item;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <h1>Merchandise</h1>
 * <p>
 * Represents a purchasable listing in a shop. Each merchandise instance knows
 * how to create a fresh copy of the underlying item and the price associated
 * with it.
 * </p>
 */
public final class Merchandise {

    private final String name;
    private final Supplier<? extends Item> itemFactory;
    private final Price price;

    /**
     * Constructs a new merchandise listing.
     *
     * @param name        display name of the merchandise
     * @param itemFactory factory creating a new instance of the item being sold
     * @param price       the selling price
     */
    public Merchandise(String name, Supplier<? extends Item> itemFactory, Price price) {
        this.name = Objects.requireNonNull(name);
        this.itemFactory = Objects.requireNonNull(itemFactory);
        this.price = Objects.requireNonNull(price);
    }

    /**
     * Provides the display name of the merchandise.
     *
     * @return the merchandise name
     */
    public String getName() {
        return name;
    }

    /**
     * Creates a new instance of the item.
     *
     * @return a fresh item instance
     */
    public Item createItem() {
        return itemFactory.get();
    }

    /**
     * Retrieves the price of the merchandise.
     *
     * @return the price
     */
    public Price getPrice() {
        return price;
    }
}