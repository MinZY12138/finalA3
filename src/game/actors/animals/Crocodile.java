package game.actors.animals;

import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.actors.statuses.Poisoning;
import game.behaviours.AttackBehaviour;
import game.weapons.Teeth;


/**
 * A concrete Bear type
 * <p>
 * Initialized with fixed hitpoints and warmth level.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 2.0
 */

public class Crocodile extends Animal {
    public Crocodile(){
        super(AnimalInfo.CROCODILE.getNAME(),
                AnimalInfo.CROCODILE.getDISPLAY_CHARACTER(),
                AnimalInfo.CROCODILE.getHIT_POINT(),
                AnimalInfo.CROCODILE.getWARMTH_LEVEL());



        addBehaviourToAnimal(new AttackBehaviour(), 1);

    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new Teeth();
    }

    @Override
    public void spawnCapability(Location spawnGround) {

        for (Location nearby : spawnGround.getNearbyLocations(1)) {
            if (nearby.containsAnActor()) {

                nearby.getActor().addStatus(new Poisoning(nearby.getActor(), 10,3));
            }
        }
    }
}
