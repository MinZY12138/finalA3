package game.grounds.spawnable;

import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import java.util.List;
import java.util.Random;

public class Cave extends Ground {

    private int turns = 0;
    private final int animalSpawnTurn = 5;
    private final List<Spawnable> spawnable;
    private final Random random = new Random();


    public Cave(Spawnable... spawnable){
        super('C', "Cave");
        this.spawnable = List.of(spawnable);

    }

    /**
     * spawn fruit in random adjacent locations
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location)
    {
        turns++;
        super.tick(location);

        if (turns % animalSpawnTurn == 0 && !location.containsAnActor())
        {
            Spawnable pickedAnimal = spawnable.get(random.nextInt(spawnable.size()));
            Animal animal = pickedAnimal.create();

            try{
                location.addActor(animal);
            } catch (GameEngineException e) {
                throw new RuntimeException(e);
            }

        }

    }

}
