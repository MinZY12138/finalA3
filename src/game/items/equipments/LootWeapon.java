package game.items.equipments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;

import java.util.Random;

/**
 * <h1>LootWeapon class</h1>
 * <p>
 * The {@code LootWeapon} is an {@link Item} and {@link Weapon} at the same time (i.e. equipment).
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 2.0.1
 * @since 2025-09-24
 */
public abstract class LootWeapon extends Item implements Weapon {

    /**
     * Defining weapon attributes.
     */
    protected final WeaponType TYPE;

    /**
     * A random object.
     */
    protected static final Random RAND = new Random();

    /**
     * The constructor of LoopWeapon class.
     *
     * @param name        the weapon name
     * @param displayChar the symbol represents a weapon on the game map
     * @param portable    weapon portability
     * @param type        the configuration of weapons
     */
    public LootWeapon(String name, char displayChar, boolean portable, WeaponType type) {
        super(name, displayChar, portable);
        this.TYPE = type;
    }

    /**
     * Attack a target actor with additional effects.
     *
     * @param attacker represent an actor attack
     * @param target   represent an actor being attacked
     * @param map      the game map
     */
    public void hit(Actor attacker, Actor target, GameMap map) {
    }

    /**
     * Define a weapon hit rate and show a description of the action.
     *
     * @param attacker the actor who performed the attack
     * @param target   the actor who is the target of the attack
     * @param map      the map on which the attack was executed
     * @return a string message
     */
    @Override
    public final String attack(Actor attacker, Actor target, GameMap map) {
        int maximumBound = 100;

        if (!(RAND.nextInt(maximumBound) <= this.getHitRate())) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(this.getDamage());
        this.hit(attacker, target, map);
        return String.format("%s %s %s for %d damage",
                attacker, this.getVerb(), target, this.getDamage());
    }

    /**
     * Represent a weapon what action is allowable.
     *
     * @param otherActor the other actor
     * @param location   the location of the other actor
     * @return a list of actions
     */
    @Override
    public ActionList allowableActions(Actor otherActor, Location location) {
        ActionList actions = super.allowableActions(otherActor, location);
        actions.add(new AttackAction(otherActor, location.toString(), this.getVerb(), this));
        return actions;
    }

    /**
     * The accessor of the weapon damage.
     *
     * @return this weapon damage
     */
    public int getDamage() {
        return TYPE.getDAMAGE();
    }

    /**
     * The accessor of the chance of hitting target.
     *
     * @return this weapon hit rate
     */
    public int getHitRate() {
        return TYPE.getHIT_RATE();
    }

    /**
     * The accessor of the word to describe the weapon when hitting the target.
     *
     * @return this weapon hitting description
     */
    public String getVerb() {
        return TYPE.getVERB();
    }
}
