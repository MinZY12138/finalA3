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
    private boolean sessionOpen;


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

    synchronized MysterioStoreSession openSession(Location portalLocation) {
        Objects.requireNonNull(portalLocation, "Portal location is required");
        if (sessionOpen) {
            throw new IllegalStateException("A session for " + type + " store is already active.");
        }

        ensureSellerPresent();
        MysterioStoreSession session = new MysterioStoreSession(this, portalLocation);
        exitGround.setActiveSession(session);
        sessionOpen = true;
        return session;
    }

    synchronized void sessionClosed(MysterioStoreSession session) {
        if (sessionOpen && session != null) {
            exitGround.clearActiveSession(session);
            sessionOpen = false;
        }
    }
}