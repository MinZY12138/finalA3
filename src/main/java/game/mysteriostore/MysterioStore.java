package game.mysteriostore;

import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Seller;
import game.grounds.dimensional.DimensionalStoreType;

import java.util.Objects;

/**
 * Static definition of a dimensional store interior.
 */
final class MysterioStore {

    private final DimensionalStoreType type;
    private final GameMap map;
    private final StoreExitGround exitGround;
    private final Location entrance;
    private final Seller seller;
    private final Location sellerSpot;

    private MysterioStoreSession activeSession;

    MysterioStore(DimensionalStoreType type,
                  GameMap map,
                  StoreExitGround exitGround,
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

    StoreExitGround exitGround() {
        return exitGround;
    }

    MysterioStoreSession openSession(Location portalLocation) {
        if (activeSession != null) {
            activeSession.forceClose();
        }
        MysterioStoreSession session = new MysterioStoreSession(this, portalLocation);
        activeSession = session;
        exitGround.bind(session);
        ensureSellerPresent();
        return session;
    }

    void sessionClosed(MysterioStoreSession session) {
        if (activeSession == session) {
            exitGround.unbind();
            activeSession = null;
        }
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
