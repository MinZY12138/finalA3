package game.items.equipments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;

import java.util.Random;

public abstract class LootWeapon extends Item implements Weapon {

    protected final int DAMAGE;

    protected final int HIT_RATE;

    protected final String VERB;

    public static final Random RAND = new Random();

    public LootWeapon(String name, char displayChar, boolean portable, int damage, int hitRate,
                      String verb) {
        super(name, displayChar, portable);
        this.DAMAGE = damage;
        this.HIT_RATE = hitRate;
        this.VERB = verb;
    }

    public void hit(Actor attacker, Actor target, GameMap map) {}

    @Override
    public final String attack(Actor attacker, Actor target, GameMap map) {
        if (!(RAND.nextInt(100) <= this.HIT_RATE)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(this.DAMAGE);
        this.hit(attacker, target, map);
        return String.format("%s %s %s for %d damage", attacker, this.VERB, target, this.DAMAGE);
    }

    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        actions.add(new AttackAction(otherActor, location.toString(), this.VERB, this));
        return actions;
    }
}
