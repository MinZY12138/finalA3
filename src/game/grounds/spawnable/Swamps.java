package game.grounds.spawnable;

import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import game.actors.statuses.Poisoning;
import game.grounds.GroundInfo;

import java.util.List;

/**
 * <h1>Swamps Class</h1>
 *
 * <p>
 *      The {@code Swamps} class extends {@link SpawnGround}
 *      Animals spawned on swamp are affected by a
 *      {@link Poisoning} status effect, dealing damage over time.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public class Swamps extends SpawnGround {

    private static final int POISON_DAMAGE = 5;

    private static final int POISON_DURATION = 10;


    /**
     * Constructor for {@code Swamps}.
     *
     * @param spawnable the list of {@link Spawnable} animals that can be spawned
     */
    public Swamps(List<Spawnable> spawnable){
        super(GroundInfo.SWAMPS.getDISPLAY_CHAR(), GroundInfo.SWAMPS.getNAME(), spawnable);
        setSpawnChance(SpawnGroundInfo.SWAMPS.getSPAWN_CHANCE());
        setSpawnTurn(SpawnGroundInfo.SWAMPS.getSPAWN_TURN());
    }

    /**
     * Indicates that swamps require a nearby actor to trigger spawning.
     *
     * @return {@code true} since swamps depend on nearby actors
     */
    @Override
    protected boolean detectActor(){
        return true;

    }

    /**
     * Applies a {@link Poisoning} effect to all animals spawned in swamps.
     *
     * @param animal the animal being spawned
     */
    @Override
    protected void setAnimalAction(Animal animal) {
        // Apply poison effect: 5 damage per turn for 10 turns
        animal.addStatus(new Poisoning(animal, POISON_DAMAGE, POISON_DURATION));

    }


}
