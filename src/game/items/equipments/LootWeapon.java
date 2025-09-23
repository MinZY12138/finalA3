package game.items.equipments;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;

import java.util.Random;

public abstract class LootWeapon extends Item implements Weapon {

    protected int damage;

    protected int hitRate;

    protected String verb;

    public LootWeapon(String name, char displayChar, boolean portable, int damage, int hitRate,
                      String verb) {
        super(name, displayChar, portable);
        this.damage = damage;
        this.hitRate = hitRate;
        this.verb = verb;
    }

    @Override
    public String attack(Actor attacker, Actor target, GameMap map) {
        Random rand = new Random();
        if (!(rand.nextInt(100) <= this.hitRate)) {
            return attacker + " misses " + target + ".";
        }
        target.hurt(damage);
        return String.format("%s %s %s for %d damage", attacker, verb, target, damage);
    }
}
