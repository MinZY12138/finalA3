package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.statuses.Burning;
import game.grounds.Fire;

import java.util.List;

/**
 * <h1>Torch class</h1>
 * <p>
 * The {@code Torch} is a {@link LootWeapon}.
 * When actors use it to attack others, it has a chance to cause target actors to
 * {@link Burning} and spawns the {@link Fire} surrounding the target actor.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 2.0.1
 * @since 2025-09-24
 */
public class Torch extends LootWeapon {

    /**
     * Defining status attributes.
     */
    private final StatusType STATUS;

    /**
     * The constructor of the Torch class.
     *
     * @param type   defining damage, hit rate, and verb
     * @param status defining burning damage and duration
     */
    public Torch(WeaponType type, StatusType status) {
        super("Torch", 'y', true, type);
        this.STATUS = status;
    }

    /**
     * Attack a target actor with the burning effect and spawn fire surrounding the target actor.
     *
     * @param attacker represent an actor attack
     * @param target   represent an actor being attacked
     * @param map      the game map
     */
    @Override
    public void hit(Actor attacker, Actor target, GameMap map) {
        target.addStatus(new Burning(target, STATUS.getDAMAGE(), STATUS.getDURATION()));
        burnSurrounding(map.locationOf(target));
    }

    /**
     * Burn the surrounding location.
     *
     * @param location location on the game map
     */
    private void burnSurrounding(Location location) {
        List<Exit> surrounding = location.getExits();

        for (Exit place : surrounding) {
            place.getDestination().setGround(new Fire());
        }
    }
}
