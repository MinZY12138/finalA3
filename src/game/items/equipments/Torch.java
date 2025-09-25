package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.statuses.Burning;
import game.grounds.Fire;

import java.util.List;

public class Torch extends LootWeapon {

    private static final int HIT_DMG = 10;

    private static final int HIT_RATE = 50;

    private static final int BURNING_DMG = 3;

    private static final int DURATION = 7;

    public Torch() {
        super("torch", 'y', true, HIT_DMG, HIT_RATE, "strikes");
    }

    @Override
    public void hit(Actor attacker, Actor target, GameMap map) {
        int chance = 50;

        if (RAND.nextInt(100) <= chance) {
            target.addStatus(new Burning(target, BURNING_DMG, DURATION));
            burnSurrounding(map.locationOf(target));
        }
    }

    private void burnSurrounding(Location location) {
        List<Exit> surrounding = location.getExits();

        for (Exit place : surrounding) {
            place.getDestination().setGround(new Fire());
        }
    }
}
