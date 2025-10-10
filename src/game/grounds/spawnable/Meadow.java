package game.grounds.spawnable;

import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import game.behaviours.ConsumeBehaviour;
import game.grounds.GroundInfo;

import java.util.List;

/**
 * <h1>Class represent Meadow</h1>
 *
 * <p>
 *     A type of {@link SpawnGround} that represents a Meadow.
 *     Meadows are capable of spawning different {@link Animal}s at random intervals.
 *     Every {@code animalSpawnTurn} ticks, there is a chance (50%) that
 *     a random animal from the {@code spawnable} list will be spawned in this location,
 *     provided the location does not already contain an actor.
 * </p>
 *
 * @author Ng Jun Jie
 * @version 1.0
 */
public class Meadow extends SpawnGround {
    /**
     * Constructs a Meadow ground tile with a list of spawnable animals.
     *
     * @param spawnable a list of {@link Spawnable} animals that can be spawned
     */
    public Meadow(List<Spawnable> spawnable){
        super(GroundInfo.MEADOW.getDISPLAY_CHAR(), GroundInfo.MEADOW.getNAME(), spawnable);
        setSpawnChance(SpawnGroundInfo.MEADOW.getSPAWN_CHANCE());
        setSpawnTurn(SpawnGroundInfo.MEADOW.getSPAWN_TURN());
    }

    @Override
    protected void addBehaviour(Animal animal) {
        //Game rule consume behaviour are always has the middle priority.
        int priority = 5;
        animal.addBehaviourToAnimal(new ConsumeBehaviour(), priority);
    }
}
