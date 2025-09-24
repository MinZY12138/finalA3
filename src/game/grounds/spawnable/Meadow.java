package game.grounds.spawnable;

import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import java.util.List;
import java.util.Random;

public class Meadow extends Ground {

    private int turns = 0;
    private final int animalSpawnTurn = 7;
    private final List<Spawnable> spawnable;
    private final Random random = new Random();

    public Meadow(Spawnable... spawnable){
        super('w', "Meadow");
        this.spawnable = List.of(spawnable);
    }


    @Override
    public void tick(Location location)
    {
        turns++;
        super.tick(location);

        if (random.nextInt(100) < 50 && turns % animalSpawnTurn == 0 && !location.containsAnActor())
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
