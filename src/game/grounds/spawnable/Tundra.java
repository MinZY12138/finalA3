package game.grounds.spawnable;


import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperation;
import edu.monash.fit2099.engine.actors.attributes.BaseAttributes;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Animal;
import game.actors.animals.Spawnable;

import java.util.Random;

public class Tundra extends Ground {


    private final Spawnable spawnable;
    private final Random random = new Random();


    public Tundra(Spawnable spawnable){
        super('_', "Tundra");
        this.spawnable = spawnable;

    }

    /**
     * spawn fruit in random adjacent locations
     * @param location The location of the Ground
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
