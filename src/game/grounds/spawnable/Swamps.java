package game.grounds.spawnable;

import game.actors.animals.Animal;
import game.actors.animals.Spawnable;
import game.actors.statuses.Poisoning;
import game.grounds.GroundInfo;

import java.util.List;

public class Swamps extends SpawnGround {
    public Swamps(List<Spawnable> spawnable){
        super(GroundInfo.SWAMPS.getDISPLAY_CHAR(), GroundInfo.SWAMPS.getNAME(), spawnable);
        setSpawnChance(SpawnGroundInfo.SWAMPS.getSPAWN_CHANCE());
        setSpawnTurn(SpawnGroundInfo.SWAMPS.getSPAWN_TURN());
    }

    @Override
    protected boolean detectActor(){
        return true;

    }

    @Override
    protected void setAnimalAction(Animal animal) {
        // Apply poison effect: 5 damage per turn for 10 turns
        animal.addStatus(new Poisoning(animal, 5, 10));

    }


}
