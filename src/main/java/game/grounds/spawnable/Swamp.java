package game.grounds.spawnable;

import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import game.actors.statuses.Poisoning;
import game.actors.statuses.StatusType;
import game.grounds.GroundInfo;

import java.util.List;

/**
 * <h1>Swamp Class</h1>
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
public class Swamp extends SpawnGround {

    /**
     * Constructor for {@code Swamps}.
     *
     * @param spawnable the list of {@link Spawnable} animals that can be spawned
     */
    public Swamp(List<Spawnable> spawnable){
        super(GroundInfo.SWAMP.getDISPLAY_CHAR(), GroundInfo.SWAMP.getNAME(), spawnable);
        setSpawnChance(SpawnGroundInfo.SWAMP.getSPAWN_CHANCE());
        setSpawnTurn(SpawnGroundInfo.SWAMP.getSPAWN_TURN());
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
        animal.addStatus(StatusType.POISONING2.createStatus(animal));


    }


}
