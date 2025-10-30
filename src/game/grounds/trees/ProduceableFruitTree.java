package game.grounds.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.RandomLocation;


/**
 * <h1>Class represent ProduceableFruitTree</h1>
 *
 * <p>
 * Represent a produceable fruit tree in this system.
 * With associate attributes and method for managing
 * its information and functionality.
 * </p>
 * <p>
 * Extends from {@link Ground}
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public abstract class ProduceableFruitTree extends Ground
{
    /**
     * Lower bound value
     */
    protected static final int LOWER_BOUND = 0;

    /**
     * Storing the remaining time left for summon another fruit
     * to its surrounding.
     */
    protected int numberOfTurnsToSpawn;

    /**
     * An integer to reset the number of time left to summon fruit.
     */
    private final int CONSTANT_RESET;

    /**
     * Constructor for ProduceableFruitTree
     *
     * @param displayChar         character representation of the tree
     * @param name                of the tree (e,g., Apple etc...)
     * @param turnsToProduceFruit how many turns will cause this tree to summon a fruit.
     */
    public ProduceableFruitTree(char displayChar, String name, int turnsToProduceFruit)
    {
        super(displayChar, name);
        this.CONSTANT_RESET = turnsToProduceFruit;
        this.resetNumberOfTurnsToSpawn();
    }

    /**
     * Reset the number of turns to spawn.
     */
    private void resetNumberOfTurnsToSpawn()
    {
        this.numberOfTurnsToSpawn = this.CONSTANT_RESET;
    }

    /**
     * impassable terrain
     *
     * @param actor the Actor to check
     * @return false
     */
    @Override
    public boolean canActorEnter(Actor actor)
    {
        return false;
    }

    /**
     * Ground can also experience the joy of time.
     * Each tick will decrement the number of turn to spawn
     * once reaches 0 it will spawn a fruit to its random surrounding.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location)
    {
        this.numberOfTurnsToSpawn--;

        if (this.numberOfTurnsToSpawn == LOWER_BOUND)
        {
            //Summon fruit in radius of 1 of the current location
            int radius = 1;
            this.summonFruit(RandomLocation.randomChooseSurrounding(location, radius));

            //Reset the number of turn to spawn.
            this.resetNumberOfTurnsToSpawn();
        }
    }

    /**
     * Override this to implement terrain that blocks thrown objects but not
     * movement, or vice versa
     *
     * @return true
     */
    @Override
    public boolean blocksThrownObjects()
    {
        return true;
    }

    /**
     * Method to summon a fruits on a specific location.
     *
     * @param location the place to drop the fruit to.
     */
    protected abstract void summonFruit(Location location);
}
