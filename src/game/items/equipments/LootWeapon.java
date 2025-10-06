package game.items.equipments;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.items.coat.Coatable;
import game.items.coat.Coating;

import java.util.Random;

/**
 * <h1>LootWeapon class</h1>
 * <p>
 * The {@code LootWeapon} is an {@link Item} and {@link Weapon} at the same time (i.e. equipment).
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-09-24
 */
public abstract class LootWeapon extends Item implements Weapon, Coatable{

    /**
     * Weapon damage.
     */
    protected final int DAMAGE;

    /**
     * The rate of hitting a target actor.
     */
    protected final int HIT_RATE;

    /**
     * The cue word for actor attack.
     */
    protected final String VERB;

    /**
     * A random object.
     */
    public static final Random RAND = new Random();

    /**
     * Current coating applied to this weapon (if any).
     */
    private Coating coating;

    /**
     * The constructor of LoopWeapon class.
     *
     * @param name        the weapon name
     * @param displayChar the symbol represents a weapon on the game map
     * @param portable    weapon portability
     * @param damage      the weapon damage
     * @param hitRate     the rate of hitting a target actor
     * @param verb        the cue word for actor attack
     */
    public LootWeapon(String name, char displayChar, boolean portable, int damage, int hitRate,
                      String verb) {
        super(name, displayChar, portable);
        this.DAMAGE = damage;
        this.HIT_RATE = hitRate;
        this.VERB = verb;
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
        if (!(RAND.nextInt(100) <= this.HIT_RATE)) {
            return attacker + " misses " + target + ".";
        }

        target.hurt(this.DAMAGE);
        this.hit(attacker, target, map);

        // coating effect if present
        if (coating != null) {
            coating.applyOnHit(attacker, target, map);
        }

        return String.format("%s %s %s for %d damage", attacker, this.VERB, target, this.DAMAGE);
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
        actions.add(new AttackAction(otherActor, location.toString(), this.VERB, this));
        return actions;
    }

    /**
     * Get the current coating applied to this weapon.
     *
     * @return The current {@link Coating}, or {@code null} if none.
     */
    @Override
    public Coating getCoating() {
        return coating;
    }

    /**
     * Apply (or replace) the coating on this weapon.
     *
     * @param coating The {@link Coating} to set; may overwrite an existing one.
     */
    @Override
    public void setCoating(Coating coating) {
        this.coating = coating;
    }

    /**
     * Remove the current coating from this weapon.
     */
    @Override
    public void clearCoating() {
        this.coating = null;
    }

    /**
     * Get the display name including coating info if present.
     *
     * @return {@code "<baseName> [<coating>]" } when coated; otherwise {@code toString()}.
     */
    @Override
    public String coatedName() {
        if (coating != null) {
            return this.toString() + " [" + coating.name() + "]";
        }
        return this.toString();
    }

}
