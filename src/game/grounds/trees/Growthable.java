package game.grounds.trees;

import edu.monash.fit2099.engine.positions.Location;

import java.util.Random;

public abstract class Growthable extends ProduceableFruitTree {

    private int turnToGrowth;

    private final boolean CAN_PRODUCE;

    private ProduceableFruitTree nextStage;
    private int transformRate;
    private final int CONSTANT_RESET;
    private static final Random RAND = new Random();
    private static final int MAXIMUM_TRANSFORM_BOUND = 100;

    /**
     * Constructor for Growthable
     * @param displayChar character representation of the tree
     * @param name of the tree (e,g., Apple etc..)
     * @param turnsToProduceFruit how many turns will cause this tree to summon a fruit.
     * @param CAN_PRODUCE indicate this stage of tree can produce fruit or not.
     * @param turnToGrowth indicate how many turn for this stage of tree to growth.
     */
    public Growthable(char displayChar, String name, int turnsToProduceFruit, int turnToGrowth, boolean CAN_PRODUCE)
    {
        super(displayChar, name, turnsToProduceFruit);
        this.turnToGrowth = turnToGrowth;
        this.CAN_PRODUCE = CAN_PRODUCE;
        this.CONSTANT_RESET = turnToGrowth;
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
        if (CAN_PRODUCE)
        {
            super.tick(location);
        }

        if (turnToGrowth == 0){
            int chancesToTransform = RAND.nextInt(MAXIMUM_TRANSFORM_BOUND);

//            System.out.println(this.toString() + " " + chancesToTransform + " " + this.transformRate); //debug purposes

            if (chancesToTransform < transformRate){
                location.setGround(nextStage);
            }
            else {
                turnToGrowth += CONSTANT_RESET;
            }
        }

        turnToGrowth --;
    }

    public void setNextStage(ProduceableFruitTree nextStage)
    {
        this.nextStage = nextStage;
    }

    protected void setTransformRate(int transformRate)
    {
        this.transformRate = transformRate;
    }
}
