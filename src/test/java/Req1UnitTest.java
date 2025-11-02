import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.DefaultGroundCreator;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.grounds.Snow;
import game.grounds.trees.ProduceableFruitTree;
import game.grounds.trees.apples.AppleChild;
import game.grounds.trees.apples.AppleSapling;
import game.grounds.trees.apples.AppleSprouts;
import game.grounds.trees.apples.AppleTree;
import game.grounds.trees.yewBerrys.YewBerryChild;
import game.grounds.trees.yewBerrys.YewBerrySapling;
import game.grounds.trees.yewBerrys.YewBerryTree;
import game.items.fruits.Apple;
import game.items.fruits.YewBerry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for Requirement 1: Tree Growth System.
 */
public class Req1UnitTest {

    /**
     * Creates a simple 3x3 GameMap filled with Snow.
     * This ensures Location.map() is never null and all RandomLocation calls are safe.
     */
    private GameMap createMiniTestMap() throws GameEngineException {
        DefaultGroundCreator groundCreator = new DefaultGroundCreator();
        groundCreator.registerGround('.', Snow::new);
        List<String> mapLayout = Arrays.asList("...", "...", "...");
        return new GameMap("MiniTestMap", groundCreator, mapLayout);
    }

    private GameMap testMap;

    @BeforeEach
    public void setUp() throws GameEngineException {
        testMap = createMiniTestMap(); // safely throws GameEngineException if needed
    }

    /**
     * Helper function to simulate ticks and check that a tree object
     * transforms into the expected class after the given number of ticks.
     *
     * @param location The location of the tree
     * @param tree the ground (tree) to set to the location
     * @param ticks how many tick wanted
     * @param expectedClass expected produceable fruit tree type.
     */
    private void simulateGrowthAndAssert(Location location, ProduceableFruitTree tree, int ticks,
                                         Class<? extends ProduceableFruitTree> expectedClass) {
        location.setGround(tree);
        for (int i = 0; i < ticks; i++) {
            (tree).tick(location);
        }
        assertTrue(expectedClass.isInstance(location.getGround()),
                "After " + ticks + " ticks, expected " + expectedClass.getSimpleName()
                        + " but got: " + location.getGround());
    }

    /**
     * Helper function to count the fruit appearance and rise assertion if not same as expected.
     * @param location The location of the tree
     * @param expectedFruitCount expected value
     * @param fruitTypeName type name to check with
     * @param tick how many tick (for message)
     */
    private void countFruitAndAssert(Location location, int expectedFruitCount, String fruitTypeName, int tick){
        // Assert It should have produced expectedFruitCount
        int fruitCount = 0;
        List<Location> nearby = location.getNearbyLocations(1);
        for (Location loc : nearby) {
            for (Item item : loc.getItems()) {
                if (item.getClass().getSimpleName().equals(fruitTypeName)) {
                    fruitCount++;
                }
            }
        }
        assertEquals(expectedFruitCount, fruitCount,
                "After " + tick  + " tick the " + fruitTypeName +
                        " count should be " + expectedFruitCount);
    }

    @Nested
    class AppleTreeTest {
        private Location mockLocation;
        private Location realLocation;

        @BeforeEach
        public void setUp() {
            mockLocation = mock(Location.class);
            realLocation = testMap.at(1, 1);
        }


        // Forest
        /**
         * Positive test case: sprouts should grow after 3 tick (on 4th).
         */
        @Test
        public void testForest_SproutsGrowthTriggersSetGround() {
            AppleSprouts sprouts = AppleChild.createAppleSprouts(false, true);
            for (int i = 0; i < 4; i++) sprouts.tick(mockLocation);
            verify(mockLocation, times(1)).setGround(any(AppleSapling.class));
        }

        /**
         * Positive test case: sprouts should become Sapling after 3 ticks.
         */
        @Test
        public void testForest_SproutActuallyReplacedBySaplingOnRealLocation() {
            simulateGrowthAndAssert(realLocation,
                    AppleChild.createAppleSprouts(
                            false, true), 4, AppleSapling.class);
        }

        /**
         * Edge test case: sprouts at exactly 3 tick should remain the same type.
         */
        @Test
        public void testForest_SproutRemainsSproutAtExactlyThreeTicks() {
            AppleSprouts sprouts = AppleChild.createAppleSprouts(false, true);
            realLocation.setGround(sprouts);
            for (int i = 0; i < 3; i++) sprouts.tick(realLocation);
            assertInstanceOf(AppleSprouts.class, realLocation.getGround(),
                    "At exactly 3 ticks, the sprout should still remain AppleSprouts");
        }

        /**
         * Negative test case: sprouts should stay sprouts for all ticks before growth.
         */
        @Test
        public void testForest_SproutRemainsSproutBeforeGrowth() {
            AppleSprouts sprouts = AppleChild.createAppleSprouts(false, true);
            realLocation.setGround(sprouts);
            for (int i = 0; i < 2; i++) {
                sprouts.tick(realLocation);
                assertInstanceOf(AppleSprouts.class, realLocation.getGround(),
                        "At tick " + (i + 1) + ", " +
                        "sprout should still remain AppleSprouts before 4 ticks but its stage changed");
            }
        }

        /**
         * Positive test case: sapling should grow into a mature AppleTree after 5 ticks (on 6th).
         */
        @Test
        public void testForest_SaplingGrowthToTreeAfterFiveTicks() {
            simulateGrowthAndAssert(realLocation, AppleChild.createAppleSapling(true), 6,
                    game.grounds.trees.apples.AppleTree.class);
        }

        /**
         * Edge test case: sapling at exactly 5 ticks should still remain a sapling.
         */
        @Test
        public void testForest_SaplingRemainAtExactlyFiveTicks() {
            AppleSapling sapling = AppleChild.createAppleSapling(true);
            realLocation.setGround(sapling);
            for (int i = 0; i < 5; i++) sapling.tick(realLocation);
            assertInstanceOf(AppleSapling.class, realLocation.getGround(),
                    "At exactly 5 ticks, the sapling should still remain AppleSapling");
        }

        /**
         * Negative test case: sapling should remain sapling before 5 ticks.
         */
        @Test
        public void testForest_SaplingStillSaplingBeforeFiveTicks() {
            AppleSapling sapling = AppleChild.createAppleSapling(true);
            realLocation.setGround(sapling);
            for (int i = 0; i < 4; i++) {
                sapling.tick(realLocation);
                assertInstanceOf(AppleSapling.class, realLocation.getGround(),
                        "At tick " + (i + 1) + ", the sapling should still remain AppleSapling");
            }
        }

        /**
         * Positive test case: Forest AppleSapling and Tree should produce apples every 2 and 3 turns respectively.
         * (Forest: Sapling produces every 2 turns after growth)
         */
        @Test
        public void testForest_AppleSaplingProducesEveryTwoTurns() {
            Location realLocation = testMap.at(1, 1);
            AppleSapling sapling = AppleChild.createAppleSapling(true); // true = forest
            realLocation.setGround(sapling);

            // Simulate 4 ticks → should drop apples twice
            for (int i = 0; i < 4; i++) {
                sapling.tick(realLocation);
            }

            //Count the apple should equal to 2
            countFruitAndAssert(realLocation, 2, Apple.class.getSimpleName(), 4);
        }

        /**
         * Positive test case: Mature Forest AppleTree should produce apples every 3 turns.
         */
        @Test
        public void testForest_AppleTreeProducesEveryThreeTurns() {
            Location realLocation = testMap.at(1, 1);
            game.grounds.trees.apples.AppleTree tree = AppleChild.createMatureAppleTree();
            realLocation.setGround(tree);

            // Simulate 9 ticks should drop apples 3 times
            for (int i = 0; i < 9; i++) {
                tree.tick(realLocation);
            }

            // Count apples in nearby tiles
            countFruitAndAssert(realLocation, 3, Apple.class.getSimpleName(), 9);
        }

        //Plains

        /**
         * Plains AppleSprouts should produce apples every turn and skip the sapling stage.
         */
        @Test
        public void testPlains_AppleSproutProducesEveryTurnAndSkipsSapling() {
            // Arrange: Place Plains Sprout on real test map
            Location realLocation = testMap.at(1, 1);
            AppleSprouts plainsSprout = AppleChild.createSkipSaplingAppleSprouts(true);
            realLocation.setGround(plainsSprout);

            // Act: Simulate 4 turns (each tick = 1 turn)
            for (int i = 0; i < 4; i++) {
                plainsSprout.tick(realLocation);
            }

            // Assert It should become a mature AppleTree (skip sapling stage)
            assertInstanceOf(AppleTree.class, realLocation.getGround(),
                    "After 3 ticks, Plains AppleSprout should have become " +
                            "a mature AppleTree (skipping sapling stage).");
            assertFalse(realLocation.getGround() instanceof game.grounds.trees.apples.AppleSapling,
                    "Plains AppleSprout should skip the AppleSapling stage.");

            // Assert It should have produced one apple every turn (3 apples total)
            countFruitAndAssert(realLocation, 3, Apple.class.getSimpleName(), 4);
            }
    }

    @Nested
    class YewBerryTreeTest {
        private Location mockLocation;
        private Location realLocation;

        @BeforeEach
        public void setUp() {
            mockLocation = mock(Location.class);
            realLocation = testMap.at(1, 1);
        }


        // Forest

        /**
         * Positive test case: YewBerrySapling on Forest should grow into YewBerryTree on the 3-turn check (forced success).
         */
        @Test
        public void testForest_YewBerrySaplingGrowsOnThreeTurnCheck_ForcedSuccess() {
            YewBerrySapling sapling = YewBerryChild.createYewBerrySapling(false);
            sapling.setTransformRate(100); // force success
            simulateGrowthAndAssert(realLocation, sapling, 3, YewBerryTree.class);
        }

        /**
         * Edge test case: Before the 3-turn check (ticks 1-2) the YewBerrySapling must remain a sapling.
         */
        @Test
        public void testForest_YewBerrySaplingRemainsBeforeThreeTurnCheck() {
            YewBerrySapling sapling = YewBerryChild.createYewBerrySapling(false);
            sapling.setTransformRate(100);
            realLocation.setGround(sapling);
            for (int i = 0; i < 2; i++) {
                sapling.tick(realLocation);
                assertInstanceOf(YewBerrySapling.class, realLocation.getGround(),
                        "At tick " + (i + 1) +
                                ", before the 3-turn check, it should remain a YewBerrySapling");
            }
        }

        /**
         * Negative test case: If the 50% check fails (forced 0%), the sapling should remain a sapling after the 3-turn check.
         */
        @Test
        public void testForest_YewBerrySaplingFailsToGrowWhenChanceFails() {
            YewBerrySapling sapling = YewBerryChild.createYewBerrySapling(false);
            sapling.setTransformRate(0); // force failure
            realLocation.setGround(sapling);
            for (int i = 0; i < 3; i++) sapling.tick(realLocation);
            assertInstanceOf(YewBerrySapling.class, realLocation.getGround(),
                    "If the 50% growth check fails, the sapling should remain " +
                            "YewBerrySapling after the 3-turn check");
        }

        /**
         * Negative test case: YewBerrySapling on Forest should NOT produce any yew berry.
         */
        @Test
        public void testForest_YewBerrySaplingDoesNotProduceFruit() {
            YewBerrySapling sapling = YewBerryChild.createYewBerrySapling(false);
            sapling.setTransformRate(0);
            realLocation.setGround(sapling);
            for (int i = 0; i < 6; i++) {
                sapling.tick(realLocation);
            }
            countFruitAndAssert(realLocation, 0, YewBerry.class.getSimpleName(), 6);
        }

        // Plains

        /**
         * Positive test case: Plains YewBerrySapling produces yew berry every 2 turns.
         */
        @Test
        public void testPlains_YewBerrySaplingProducesEveryTwoTurns() {

            // Create a plains YewBerrySapling
            YewBerrySapling sapling = YewBerryChild.createYewBerrySapling(true);

            // To test it can produce correctly need to force it to be sapling
            sapling.setTransformRate(0);
            realLocation.setGround(sapling);

            // Simulate 4 ticks (should produce fruit twice)
            for (int i = 0; i < 4; i++) {
                sapling.tick(realLocation);
            }

            countFruitAndAssert(realLocation, 2, YewBerry.class.getSimpleName(), 4);
        }


        /**
         * Edge test case: Plains YewBerrySapling should not produce before 2 turns (tick 1 produces nothing).
         */
        @Test
        public void testPlains_YewBerrySaplingNoFruitBeforeTwoTurns() {
            YewBerrySapling sapling = YewBerryChild.createYewBerrySapling(true);
            sapling.tick(mockLocation);
            verify(mockLocation, never()).addItem(any(YewBerry.class));
        }

        /**
         * Positive test case: Plains YewBerrySapling growth behavior (3-turn check) - forced success.
         */
        @Test
        public void testPlains_YewBerrySaplingGrowsOnThreeTurnCheck_ForcedSuccess() {
            YewBerrySapling sapling = YewBerryChild.createYewBerrySapling(true);
            sapling.setTransformRate(100); // force growth success
            simulateGrowthAndAssert(realLocation, sapling, 3, YewBerryTree.class);
        }

        /**
         * Positive test case: Mature YewBerryTree should produce yew berry every 5 turns (same for both maps).
         */
        @Test
        public void testYewBerryTreeProducesEveryFiveTurns() {
            YewBerryTree tree = YewBerryChild.createMatureYewBerryTree();
            realLocation.setGround((tree));
            for (int i = 0; i < 10; i++) tree.tick(realLocation);
            countFruitAndAssert(realLocation, 2, YewBerry.class.getSimpleName(), 10);
        }
    }
}
