package game.actors.animals;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.statuses.ContinuousDamage;
import game.actors.statuses.Poisoning;
import game.behaviours.AttackBehaviour;
import game.actors.statuses.StatusType;
import game.behaviours.AttackBehaviourInjector;
import game.weapons.Teeth;
import game.weapons.TeethInjector;

/**
 * <h1>Crocodile Class</h1>
 *
 * <p>
 * {@code Crocodile} extend Animal is equipped with a {@link Teeth} weapon
 * and assigned an {@link AttackBehaviour}, allowing it to engage in combat.
 * When the crocodile spawns in a location, it applies a
 * {@link Poisoning} status effect to nearby actors within 1 radius,
 * simulating poison
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */

public class Crocodile extends Animal {

    private static final int DETECT_RADIUS = 1;
    private static final int PRIORITY = 1;

    /**
     * Constructor for {@code Crocodile}.
     * Initializes base stats and adds core behaviours.
     */
    public Crocodile() {
        super(AnimalInfo.CROCODILE.getNAME(),
                AnimalInfo.CROCODILE.getDISPLAY_CHARACTER(),
                AnimalInfo.CROCODILE.getHIT_POINT(),
                AnimalInfo.CROCODILE.getWARMTH_LEVEL());
        addBehaviourToAnimal(AttackBehaviourInjector.getAttackBehaviour(), PRIORITY);
        this.setIntrinsicWeapon(TeethInjector.getNewTeeth());

    }

    /**
     * Defines the special capability or environmental effect applied
     * when the crocodile spawns in a specific ground location.
     *
     * <p>
     * When spawned, the crocodile will poison any nearby actors within
     * a radius of one tile using the {@link Poisoning} status effect.
     * </p>
     *
     * @param spawnGround the {@link Location} where the crocodile spawns
     */
    @Override
    public void spawnCapability(Location spawnGround) {

        for (Location nearby : spawnGround.getNearbyLocations(DETECT_RADIUS)) {
            if (nearby.containsAnActor()) {
                ContinuousDamage status = StatusType.POISONING.createStatus(nearby.getActor());
                nearby.getActor().addStatus(status);
            }
        }
    }
}
