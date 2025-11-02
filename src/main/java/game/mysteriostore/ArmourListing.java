package game.mysteriostore;

import edu.monash.fit2099.engine.actions.Action;
import game.actions.BuyAndWearAction;
import game.actors.Seller;
import game.items.equipments.armors.Armor;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Listing for armour pieces that should be equipped immediately after purchase.
 */
public final class ArmourListing {

    private final String name;
    private final Price price;
    private final Supplier<? extends Armor> factory;

    public ArmourListing(String name, Price price, Supplier<? extends Armor> factory) {
        this.name = Objects.requireNonNull(name);
        this.price = Objects.requireNonNull(price);
        this.factory = Objects.requireNonNull(factory);
    }

    public Action createAction(Seller seller) {
        return new BuyAndWearAction(seller, name, price, factory);
    }
}