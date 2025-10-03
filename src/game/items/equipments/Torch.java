package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.statuses.Burning;
import game.grounds.Fire;
import game.items.equipments.coat.Coating;

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
 * @version 1.0.0
 * @since 2025-09-24
 */
public class Torch extends LootWeapon {

    /**
     * Weapon damage.
     */
    private static final int HIT_DMG = 10;

    /**
     * Rate of hitting target actors.
     */
    private static final int HIT_RATE = 50;

    /**
     * Burning damage.
     */
    private static final int BURNING_DMG = 3;

    /**
     * Total turns of the continuous damage.
     */
    private static final int DURATION = 7;

    /**
     * The constructor of the Torch class.
     */
    public Torch() {
        super("torch", 'y', true, HIT_DMG, HIT_RATE, "strikes");
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
        int chance = 50;

        if (RAND.nextInt(100) <= chance) {
            target.addStatus(new Burning(target, BURNING_DMG, DURATION));
            burnSurrounding(map.locationOf(target));
        }
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

    @Override
    public void setCoating(Coating coating) {
        // Torch cannot be coated, do nothing
    }

    @Override
    public Coating getCoating() {
        return null; // Torch never has coating
    }

}
