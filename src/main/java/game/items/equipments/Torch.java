package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.statuses.ContinuousDamage;
import game.actors.statuses.StatusType;
import game.capabilities.SummonFire;
import game.items.ItemInfo;

import java.util.List;

/**
 * <h1>Torch class</h1>
 * <p>
 * The {@code Torch} is a {@link LootWeapon}.
 * When actors use it to attack others, it has a chance to cause target actors to
 * burning and spawns the fire surrounding the target actor.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 2.0.1
 * @since 2025-09-24
 * <p>
 * Modified by: Shee Seng Cheng，Min Zhengyuan
 */
public class Torch extends LootWeapon implements SummonFire {

    private static final int BURNING_GROUND_DURATION = 5;

    /**
     * The constructor of the Torch class.
     *
     * @param type   defining damage, hit rate, and verb
     * @param effect defining burning damage and duration
     */
    public Torch(WeaponType type, StatusType effect) {
        super(ItemInfo.TORCH.getNAME(), ItemInfo.TORCH.getCHAR(), ItemInfo.TORCH.isPORTABLE(),
                type, effect);
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
        ContinuousDamage status = EFFECT.createStatus(target);

        if (status != null) {
            target.addStatus(status);
        }

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
            burnLocation(place.getDestination(), BURNING_GROUND_DURATION);
        }
    }
}
