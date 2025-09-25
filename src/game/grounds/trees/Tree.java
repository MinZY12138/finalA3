package game.grounds.trees;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

import java.util.List;
import java.util.Random;

/**
 * <h1>Class represent ProduceableFruitTree</h1>
 *
 * <p>
 *     Represent a produceable fruit tree in this system.
 *     With associate attributes and method for managing
 *     its information and functionality.
 * </p>
 *
 * Extends from {@link Ground}
 *
 * @author  Shee Seng Cheng
 * {@code @modifiedBy}  Ng Jun Jie
 * @version 2.0
 */
public abstract class Tree extends Ground
{
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
     * Use to generate random number
     * (usage: choice where to summon 8 out of 1 location)
     */
    protected static final Random RANDOM = new Random();

    /**
     * Constructor for ProduceableFruitTree
     * @param displayChar character representation of the tree
     * @param name of the tree (e,g., Apple etc..)
     * @param constant how many turns will cause this tree to summon a fruit.
     */
    public Tree(char displayChar, String name, int constant)
    {
        super(displayChar, name);
        this.CONSTANT_RESET = constant;
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
        this.numberOfTurnsToSpawn -= 1;
        if (this.numberOfTurnsToSpawn == 0)
        {
            //Summon fruit
            this.summonFruit(this.getRandomSurrounding(location));

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
     * Method to get a random location from its surrounding.
     * @param location the tree location
     * @return {@code Location} random location
     */
    protected Location getRandomSurrounding(Location location)
    {
        //Get all the 8 locations
        List<Exit> surrounding = location.getExits();

        //Get random location
        Exit desiredSpawnLocation;
        int index = RANDOM.nextInt(
                0, surrounding.size());

        desiredSpawnLocation = surrounding.get(index);

        return desiredSpawnLocation.getDestination();
    }

    /**
     * Method to summon a fruits on a specific location.
     * @param location the place to drop the fruit to.
     */
    protected abstract void summonFruit(Location location);



}
