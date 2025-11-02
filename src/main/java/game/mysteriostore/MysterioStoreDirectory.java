package game.mysteriostore;

import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Seller;
import game.grounds.dimensional.DimensionalStoreType;

import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * Central directory containing the predefined dimensional stores.
 */
public final class MysterioStoreDirectory {

    private static final Map<DimensionalStoreType, MysterioStoreTemplate> TEMPLATES = new EnumMap<>(DimensionalStoreType.class);
    private static final Map<DimensionalStoreType, MysterioStore> STORES = new EnumMap<>(DimensionalStoreType.class);

    static {
        registerTemplate(new ArmouryStoreTemplate());
        registerTemplate(new AlchemyStoreTemplate());
        registerTemplate(new CuriosityStoreTemplate());
    }

    private MysterioStoreDirectory() {
    }

    public static void registerArmouryStore(GameMap map, int entranceX, int entranceY, int sellerX, int sellerY) {
        registerWithTemplate(map, entranceX, entranceY, sellerX, sellerY, DimensionalStoreType.ARMOURY);
    }

    public static void registerAlchemyStore(GameMap map, int entranceX, int entranceY, int sellerX, int sellerY) {
        registerWithTemplate(map, entranceX, entranceY, sellerX, sellerY, DimensionalStoreType.ALCHEMY);
    }

    public static void registerCuriosityStore(GameMap map, int entranceX, int entranceY, int sellerX, int sellerY) {
        registerWithTemplate(map, entranceX, entranceY, sellerX, sellerY, DimensionalStoreType.CURIOSITY);
    }

    public static MysterioStoreSession open(DimensionalStoreType type, Location portalLocation) {
        Objects.requireNonNull(type, "Store type is required");
        Objects.requireNonNull(portalLocation, "Portal location is required");

        MysterioStore store = STORES.get(type);
        if (store == null) {
            throw new IllegalStateException("No store registered for type " + type + ".");
        }
        return store.openSession(portalLocation);
    }

    private static void registerWithTemplate(GameMap map,
                                             int entranceX,
                                             int entranceY,
                                             int sellerX,
                                             int sellerY,
                                             DimensionalStoreType type) {
        Objects.requireNonNull(map, "Store map is required");
        Objects.requireNonNull(type, "Store type is required");

        if (STORES.containsKey(type)) {
            throw new IllegalStateException(type + " store has already been registered.");
        }

        MysterioStoreTemplate template = TEMPLATES.get(type);
        if (template == null) {
            throw new IllegalArgumentException("No template defined for store type " + type + ".");
        }

        StoreExitGround exitGround = new StoreExitGround();
        Location entrance = map.at(entranceX, entranceY);
        entrance.setGround(exitGround);

        Location sellerSpot = map.at(sellerX, sellerY);
        Seller seller = template.createSeller();

        if (!map.contains(seller)) {
            try {
                map.addActor(seller, sellerSpot);
            } catch (GameEngineException e) {
                throw new IllegalStateException("Failed to position seller for " + type + " store.", e);
            }
        }

        MysterioStore store = new MysterioStore(type, map, exitGround, entrance, seller, sellerSpot);
        STORES.put(type, store);
    }

    private static void registerTemplate(MysterioStoreTemplate template) {
        TEMPLATES.put(template.getType(), template);
    }
}
