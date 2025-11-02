package game.grounds;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.teleportable.DoorStore;
import game.grounds.teleportable.TeleDoor;

import java.util.List;
import java.util.Random;

public class DimensionalGround extends Ground
{
    private static final Random RAND = new Random();
    /**
     * Indicate the dimensional ground transform time
     */
    private int duration;

    /**
     * Indicate time to end
     */
    private static final int END = 0;

    private static final int TRANSFORM_TIME = 3;

    private final List<DoorStore> DOOR_TYPE;
    private Location sourceLocation;

    /**
     * Constructor.
     */
    public DimensionalGround(List<DoorStore> DOOR_TYPE)
    {
        super(GroundInfo.DIMENSIONAL_GROUND.getDISPLAY_CHAR(), GroundInfo.DIRT.getNAME());
        this.DOOR_TYPE = DOOR_TYPE;
        this.duration = TRANSFORM_TIME;
    }

    public void setSourceLocation (Location sourceLocation){
        this.sourceLocation = sourceLocation;
    }

    /**
     * DimensionalGround can also experience the joy of time.
     * @param location The location of the DimensionalGround
     */
    @Override
    public void tick(Location location) {
        if (duration == END) {
            // Pick a random DoorStore type (store variant)
            DoorStore template = DOOR_TYPE.get(RAND.nextInt(DOOR_TYPE.size()));

            // Create a fresh DoorStore instance (entrance)
            DoorStore entrance = new DoorStore(template.getDESTINATION());

            // Replace this dimensional ground with the new door entrance
            location.setGround(entrance);

            // Create an exit door inside the store
            Location storeLocation = template.getDESTINATION();
            Location randomReturn = RandomLocation.randomChooseLocation(sourceLocation.map());

            // Create TeleDoor (exit from store to random original map location)
            TeleDoor exitDoor = new TeleDoor(List.of(randomReturn), storeLocation);
            storeLocation.setGround(exitDoor);
        }

        duration--;
    }

}
