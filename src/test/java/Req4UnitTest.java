import edu.monash.fit2099.engine.actors.Actor;
import game.items.currency.*;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class Req4UnitTest {

    @Nested
    class DiamondCollectionAndCombinationTest {
        private Wallet mockWallet;
        private Actor mockActor;

        @BeforeEach
        public void setUp() {
            mockWallet = WalletInjector.getNewWallet();
            mockActor = mock(Actor.class);
        }

        @Test
        public void testCollectSingleDiamond() {
            mockWallet.collect(mockActor, new GreenDiamond());

            assertEquals(1, mockWallet.getAmount(GreenDiamond.class),
                    "Wallet should contain exactly one Green Diamond");
            assertEquals(0, mockWallet.getAmount(BlueDiamond.class),
                    "Wallet should not yet contain any Blue Diamond");

        }

        @Test
        public void testCollectDoubleDiamond() {
            mockWallet.collect(mockActor, new GreenDiamond());
            mockWallet.collect(mockActor, new GreenDiamond());

            assertEquals(0, mockWallet.getAmount(GreenDiamond.class),
                    "No Green Diamond should remain");
            assertEquals(1, mockWallet.getAmount(BlueDiamond.class),
                    "Two Green Diamond should combine into one Blue Diamond");
        }

        @Test
        public void testCollectMultipleDiamond() {
            for (int i=0; i<4; i++) {
                mockWallet.collect(mockActor, new GreenDiamond());
            }

            assertEquals(0, mockWallet.getAmount(GreenDiamond.class),
                    "No Green Diamond should remain");
            assertEquals(0, mockWallet.getAmount(BlueDiamond.class),
                    "No Blue Diamond should remain");
            assertEquals(1, mockWallet.getAmount(RedDiamond.class),
                    "Wallet should contain exactly one Red Diamond");
        }

        @Test
        public void testCombineRedDiamond() {
            mockWallet.collect(mockActor, new RedDiamond());
            String combination = mockWallet.collect(mockActor, new RedDiamond());

            assertEquals(2, mockWallet.getAmount(RedDiamond.class),
                    "Wallet should contain exactly two Red Diamond");
            assertTrue(combination.isEmpty(),
                    "Red Diamond cannot combine further");
        }

        @Test
        public void testDeductDiamondFromWallet() {
            GreenDiamond green = new GreenDiamond();
            mockWallet.collect(mockActor, green);

            assertEquals(1, mockWallet.getAmount(GreenDiamond.class),
                    "Wallet should contain exactly one Green Diamond");

            mockWallet.deduct(green);

            assertEquals(0, mockWallet.getAmount(GreenDiamond.class),
                    "No Green Diamond should remain");
        }

        @Test
        public void testDeductSingleDiamondFromWallet() {
            RedDiamond red1 = new RedDiamond();
            RedDiamond red2 = new RedDiamond();
            mockWallet.collect(mockActor, red1);
            mockWallet.collect(mockActor, red2);
            mockWallet.deduct(red1);

            assertEquals(1, mockWallet.getAmount(RedDiamond.class),
                    "Deducting one diamond should leave one remaining");
        }

        @Test
        public void testDeductDiamondFromEmptyWallet() {
            GreenDiamond green = new GreenDiamond();
            mockWallet.deduct(green);

            assertEquals(0, mockWallet.getAmount(GreenDiamond.class),
                    "Wallet should not have negative diamond counts");
        }
    }
}
