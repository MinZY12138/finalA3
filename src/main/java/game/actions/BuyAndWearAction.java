package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Seller;
import game.items.currency.WalletFunction;
import game.items.equipments.armors.Armor;
import game.items.equipments.armors.Wearing;
import game.mysteriostore.Price;

import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * Action that purchases an armour piece and equips it immediately.
 */
public final class BuyAndWearAction extends Action {

    private final Seller seller;
    private final String itemName;
    private final Price price;
    private final Supplier<? extends Armor> armorFactory;

    public BuyAndWearAction(Seller seller, String itemName, Price price, Supplier<? extends Armor> armorFactory) {
        this.seller = Objects.requireNonNull(seller);
        this.itemName = Objects.requireNonNull(itemName);
        this.price = Objects.requireNonNull(price);
        this.armorFactory = Objects.requireNonNull(armorFactory);
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        List<WalletFunction> wallets = actor.getItemInventoryAs(WalletFunction.class);
        if (wallets.isEmpty()) {
            return actor + " needs a wallet to buy " + itemName + ".";
        }

        WalletFunction wallet = wallets.get(0);
        if (!price.isAffordable(wallet)) {
            return actor + " cannot afford " + itemName + ". Cost: " + price.describe();
        }

        List<Wearing> armorHolders = actor.getItemInventoryAs(Wearing.class);
        if (armorHolders.isEmpty()) {
            return actor + " has no means to equip " + itemName + ".";
        }

        Armor armor = armorFactory.get();
        String paymentDescription = price.charge(actor, wallet);
        Wearing holder = armorHolders.get(0);
        String equipDescription = armor.wornBy(actor, holder);

        return actor + " buys " + armor + " from " + seller + " for " + price.describe() + ".\n"
                + paymentDescription + "\n" + equipDescription;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Buy and equip " + itemName + " for " + price.describe();
    }
}
