package game.grounds.spawnable;


import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperation;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import java.util.Random;

/**
 * <h1>Class represent Tundra </h1>
 *
 * <p>
 *     A type of {@link Ground} that represents a cold Tundra.
 *     The Tundra can occasionally spawn a specific {@link Animal} with extra resilience
 *     against warm conditions. Spawn chance is fixed at 5% per tick if the tile
 *     is unoccupied.
 * </p>
 */
public class Tundra extends Ground {


    private final Spawnable spawnable;
    private final Random random = new Random();


    /**
     * Constructs a Tundra ground tile with a single {@link Spawnable} animal type.
     *
     * @param spawnable the {@link Spawnable} that can appear in this Tundra
     */
    public Tundra(Spawnable spawnable){
        super('_', "Tundra");
        this.spawnable = spawnable;

    }

    /**
     * Called once per turn to update the state of this Tundra.
     *
     * @param location the location of this Tundra on the map
     */
    @Override
    public void tick(Location location)
    {

        super.tick(location);

        if (random.nextInt(100) < 5 && !location.containsAnActor()) {
            Animal animal = spawnable.create();

            animal.modifyStatsMaximum(BaseAttributes.HEALTH, ActorAttributeOperation.INCREASE, 10);

            animal.resistanceToWarm = true;
            try {
                location.addActor(animal);
            } catch (GameEngineException e) {
                throw new RuntimeException(e);
            }

        }





    }

}
