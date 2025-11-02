package game.mysteriostore;

import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Seller;
import game.mysteriostore.DimensionalStoreType;
import game.grounds.dimensional.ExitGround;
import java.util.Objects;

/**
 * Static definition of a dimensional store interior.
 */
final class MysterioStoreInterior {

    private final DimensionalStoreType type;
    private final GameMap map;
    private final ExitGround exitGround;
    private final Location entrance;
    private final Seller seller;
    private final Location sellerSpot;


    MysterioStoreInterior(DimensionalStoreType type,
                  GameMap map,
                  ExitGround exitGround,
                  Location entrance,
                  Seller seller,
                  Location sellerSpot) {
        this.type = Objects.requireNonNull(type);
        this.map = Objects.requireNonNull(map);
        this.exitGround = Objects.requireNonNull(exitGround);
        this.entrance = Objects.requireNonNull(entrance);
        this.seller = Objects.requireNonNull(seller);
        this.sellerSpot = Objects.requireNonNull(sellerSpot);
    }

    DimensionalStoreType getType() {
        return type;
    }

    GameMap map() {
        return map;
    }

    String name() {
        return type.getDescription() + " Mysterio Store";
    }

    Location entrance() {
        return entrance;
    }

    ExitGround exitGround() {
        return exitGround;
    }

    private void ensureSellerPresent() {
        if (!map.contains(seller)) {
            try {
                map.addActor(seller, sellerSpot);
            } catch (GameEngineException e) {
                throw new IllegalStateException("Unable to position store seller.", e);
            }
        }
    }

    Location sellerSpot() {
        return sellerSpot;
    }

    Seller seller() {
        return seller;
    }
}