package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.statuses.Bleeding;

public class Axe extends LootWeapon {

    private static final int HIT_DMG = 15;

    private static final int HIT_RATE = 75;

    private static final int BLEED_DMG = 10;

    private static final int DURATION = 2;

    public Axe() {
        super("axe", 'p', true, HIT_DMG, HIT_RATE, "hacks");
    }

    @Override
    public void hit(Actor attacker, Actor target, GameMap map) {
        int chance = 50;

        if (RAND.nextInt(100) <= chance) {
            target.addStatus(new Bleeding(target, BLEED_DMG, DURATION));
        }
    }
}