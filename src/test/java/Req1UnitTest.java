import edu.monash.fit2099.engine.positions.Location;
import game.grounds.trees.ProduceableFruitTree;
import game.grounds.trees.apples.AppleChild;
import game.grounds.trees.apples.AppleSapling;
import game.grounds.trees.apples.AppleSprouts;
import game.grounds.trees.apples.AppleTree;
import game.grounds.trees.yewBerrys.YewBerryChild;
import game.grounds.trees.yewBerrys.YewBerrySapling;
import game.grounds.trees.yewBerrys.YewBerryTree;
import game.items.fruits.Apple;
import game.items.fruits.Fruit;
import game.items.fruits.YewBerry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for Requirement 1: Tree Growth System and produce fruit.
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class Req1UnitTest {

    private Location mockLocation;
    @BeforeEach
    public void setUp() {
        mockLocation = mock(Location.class);

        // Create storage for whatever is set as ground
        final ProduceableFruitTree[] storedGround = new ProduceableFruitTree[1];

        // When setGround() is called, save the argument
        doAnswer(invocation -> {
            storedGround[0] = invocation.getArgument(0);
            return null; // because setGround returns void
        }).when(mockLocation).setGround(any());

        // When getGround() is called, return whatever was last set
        when(mockLocation.getGround()).thenAnswer(invocation -> storedGround[0]);

        when(mockLocation.getNearbyLocations(anyInt())).thenReturn(List.of(mockLocation));

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
        for (int i = 0; i < ticks; i++) {
            tree.tick(location);
        }
        try {
            verify(location, times(1)).setGround(
                    argThat(expectedClass::isInstance));
        } catch (AssertionError e) {
            throw new AssertionError("After " + ticks + " ticks, expected " + expectedClass.getSimpleName()
                    + " but got: " + location.getGround());
        }
    }

    /**
     * Helper function to count the fruit appearance and rise assertion if not same as expected.
     * @param location The location of the tree
     * @param expectedFruitCount expected value
     * @param fruitType type to check with
     * @param tick how many tick (for message)
     */
    private void countFruitAndAssert(Location location, int expectedFruitCount, Class<? extends Fruit> fruitType, int tick){
        // Assert It should have produced expectedFruitCount
        try
        {
            verify(location, times(expectedFruitCount)).addItem(argThat(fruitType::isInstance));
        }
        catch (AssertionError e)
        {
            throw new AssertionError(
                    "After " + tick  + " tick the " + fruitType.getSimpleName() +
                            " count should be " + expectedFruitCount);
        }
    }

    /**
     * Apple tree test
     *
     * @author Shee Seng Cheng
     * @version 1.0
     */
    @Nested
    class AppleTreeTest {
        // Forest
        /**
         * Positive test case: sprouts should grow after 3 tick (on 4th).
         */
        @Test
        public void testForest_SproutsGrowthTriggersSetGround() {
            AppleSprouts sprouts = AppleChild.createAppleSprouts(false, true);
            for (int i = 0; i < 4; i++) {
                sprouts.tick(mockLocation);
            }
            verify(mockLocation, times(1)).setGround(any(AppleSapling.class));
        }

        /**
         * Positive test case: sprouts should become Sapling after 3 ticks.
         */
        @Test
        public void testForest_SproutActuallyReplacedBySaplingOnRealLocation() {
            simulateGrowthAndAssert(mockLocation,
                    AppleChild.createAppleSprouts(
                            false, true), 4, AppleSapling.class);
        }

        /**
         * Edge test case: sprouts at exactly 3 tick should remain the same type.
         */
        @Test
        public void testForest_SproutRemainsSproutAtExactlyThreeTicks() {
            AppleSprouts sprouts = AppleChild.createAppleSprouts(false, true);
            mockLocation.setGround(sprouts);
            for (int i = 0; i < 3; i++) {
                sprouts.tick(mockLocation);
            }
            assertInstanceOf(AppleSprouts.class, mockLocation.getGround(),
                    "At exactly 3 ticks, the sprout should still remain AppleSprouts");
        }

        /**
         * Negative test case: sprouts should stay sprouts for all ticks before growth.
         */
        @Test
        public void testForest_SproutRemainsSproutBeforeGrowth() {
            AppleSprouts sprouts = AppleChild.createAppleSprouts(false, true);
            mockLocation.setGround(sprouts);
            for (int i = 0; i < 2; i++) {
                sprouts.tick(mockLocation);
                assertInstanceOf(AppleSprouts.class, mockLocation.getGround(),
                        "At tick " + (i + 1) + ", " +
                        "sprout should still remain AppleSprouts before 4 ticks but its stage changed");
            }
        }

        /**
         * Positive test case: sapling should grow into a mature AppleTree after 5 ticks (on 6th).
         */
        @Test
        public void testForest_SaplingGrowthToTreeAfterFiveTicks() {
            simulateGrowthAndAssert(mockLocation, AppleChild.createAppleSapling(true), 6,
                    game.grounds.trees.apples.AppleTree.class);
        }

        /**
         * Edge test case: sapling at exactly 5 ticks should still remain a sapling.
         */
        @Test
        public void testForest_SaplingRemainAtExactlyFiveTicks() {
            AppleSapling sapling = AppleChild.createAppleSapling(true);
            mockLocation.setGround(sapling);
            for (int i = 0; i < 5; i++) sapling.tick(mockLocation);
            assertInstanceOf(AppleSapling.class, mockLocation.getGround(),
                    "At exactly 5 ticks, the sapling should still remain AppleSapling");
        }

        /**
         * Negative test case: sapling should remain sapling before 5 ticks.
         */
        @Test
        public void testForest_SaplingStillSaplingBeforeFiveTicks() {
            AppleSapling sapling = AppleChild.createAppleSapling(true);
            mockLocation.setGround(sapling);
            for (int i = 0; i < 4; i++) {
                sapling.tick(mockLocation);
                assertInstanceOf(AppleSapling.class, mockLocation.getGround(),
                        "At tick " + (i + 1) + ", the sapling should still remain AppleSapling");
            }
        }

        /**
         * Positive test case: Forest AppleSapling and Tree should produce apples every 2 and 3 turns respectively.
         * (Forest: Sapling produces every 2 turns after growth)
         */
        @Test
        public void testForest_AppleSaplingProducesEveryTwoTurns() {
            AppleSapling sapling = AppleChild.createAppleSapling(true);

            // Simulate 4 ticks should drop apples twice
            for (int i = 0; i < 4; i++) {
                sapling.tick(mockLocation);
            }

            //Count the apple should equal to 2
            countFruitAndAssert(mockLocation, 2, Apple.class, 4);
        }

        /**
         * Positive test case: Mature Forest AppleTree should produce apples every 3 turns.
         */
        @Test
        public void testForest_AppleTreeProducesEveryThreeTurns() {
            game.grounds.trees.apples.AppleTree tree = AppleChild.createMatureAppleTree();

            // Simulate 9 ticks should drop apples 3 times
            for (int i = 0; i < 9; i++) {
                tree.tick(mockLocation);
            }

            // Count apples in nearby tiles
            countFruitAndAssert(mockLocation, 3, Apple.class, 9);
        }

        //Plains

        /**
         * Plains AppleSprouts should produce apples every turn and skip the sapling stage.
         */
        @Test
        public void testPlains_AppleSproutProducesEveryTurnAndSkipsSapling() {
            // Arrange: Place Plains Sprout on real test map
            AppleSprouts plainsSprout = AppleChild.createSkipSaplingAppleSprouts(true);
            mockLocation.setGround(plainsSprout);

            // Act: Simulate 4 turns (each tick = 1 turn)
            for (int i = 0; i < 4; i++) {
                plainsSprout.tick(mockLocation);
            }

            // Assert It should become a mature AppleTree (skip sapling stage)
            assertInstanceOf(AppleTree.class, mockLocation.getGround(),
                    "After 3 ticks, Plains AppleSprout should have become " +
                            "a mature AppleTree (skipping sapling stage).");
            assertFalse(mockLocation.getGround() instanceof game.grounds.trees.apples.AppleSapling,
                    "Plains AppleSprout should skip the AppleSapling stage.");

            // Assert It should have produced one apple every turn (3 apples total)
            countFruitAndAssert(mockLocation, 3, Apple.class, 4);
            }
    }

    /**
     * Yew berry tree test
     *
     * @author Shee Seng Cheng
     * @version 1.0
     */
    @Nested
    class YewBerryTreeTest {
        // Forest

        /**
         * Positive test case: YewBerrySapling on Forest should grow into YewBerryTree on the 3-turn check (forced success).
         */
        @Test
        public void testForest_YewBerrySaplingGrowsOnThreeTurnCheck_ForcedSuccess() {
            YewBerrySapling sapling = YewBerryChild.createYewBerrySapling(false);
            sapling.setTransformRate(100); // force success
            simulateGrowthAndAssert(mockLocation, sapling, 3, YewBerryTree.class);
        }

        /**
         * Edge test case: Before the 3-turn check (ticks 1-2) the YewBerrySapling must remain a sapling.
         */
        @Test
        public void testForest_YewBerrySaplingRemainsBeforeThreeTurnCheck() {
            YewBerrySapling sapling = YewBerryChild.createYewBerrySapling(false);
            sapling.setTransformRate(100);
            mockLocation.setGround(sapling);
            for (int i = 0; i < 2; i++) {
                sapling.tick(mockLocation);
                assertInstanceOf(YewBerrySapling.class, mockLocation.getGround(),
                        "At tick " + (i + 1) +
                                ", before the 3-turn check, it should remain a YewBerrySapling");
            }
        }

        /**
         * Negative test case: If the 50% check fails (forced 0%), the sapling should remain a
         * sapling after the 3-turn check. (not using helper because need custom message for clarity)
         */
        @Test
        public void testForest_YewBerrySaplingFailsToGrowWhenChanceFails() {
            YewBerrySapling sapling = YewBerryChild.createYewBerrySapling(false);
            sapling.setTransformRate(0); // force failure
            mockLocation.setGround(sapling);
            for (int i = 0; i < 3; i++) {
                sapling.tick(mockLocation);
            }
            assertInstanceOf(YewBerrySapling.class, mockLocation.getGround(),
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
            for (int i = 0; i < 6; i++) {
                sapling.tick(mockLocation);
            }
            verify(mockLocation, never()).addItem(any(YewBerry.class));
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

            // Simulate 4 ticks (should produce fruit twice)
            for (int i = 0; i < 4; i++) {
                sapling.tick(mockLocation);
            }

            countFruitAndAssert(mockLocation, 2, YewBerry.class, 4);
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
            simulateGrowthAndAssert(mockLocation, sapling, 3, YewBerryTree.class);
        }

        /**
         * Positive test case: Mature YewBerryTree should produce yew berry every 5 turns (same for both maps).
         */
        @Test
        public void testYewBerryTreeProducesEveryFiveTurns() {
            YewBerryTree tree = YewBerryChild.createMatureYewBerryTree();
            for (int i = 0; i < 10; i++){
                tree.tick(mockLocation);
            }
            countFruitAndAssert(mockLocation, 2, YewBerry.class, 10);
        }
    }
}
