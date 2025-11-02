import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.AttackAction;
import game.items.currency.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * <h1>Requirement 4 Unit Test</h1>
 * <p>
 *     This class provides unit tests for the diamond collection and wallet mechanics in the game.
 *     It validates the behaviour of the {@link Wallet} and related currency classes, such as
 *     {@link GreenDiamond}, {@link BlueDiamond}, and {@link RedDiamond} as well as their
 *     integration with the {@link AttackAction} when an {@link Actor} defeats an animal.
 * </p>
 *
 * @author Tay Chee Hsian
 * @version 1.0.0
 * @since 2025-11-01
 */
public class Req4UnitTest {

    private Wallet mockWallet;
    private Actor mockActor;

    /**
     * Set up a new wallet instance and a mock actor before each test.
     */
    @BeforeEach
    public void setUp() {
        mockWallet = WalletInjector.getNewWallet();
        mockActor = mock(Actor.class);
    }

    /**
     * Normal Case.
     * This test case collects a single Green Diamond should correctly increase its amount in
     * the wallet.
     */
    @Test
    public void testCollectSingleDiamond() {
        mockWallet.collect(mockActor, new GreenDiamond());

        assertEquals(1, mockWallet.getAmount(GreenDiamond.class),
                "Wallet should contain exactly one Green Diamond");
        assertEquals(0, mockWallet.getAmount(BlueDiamond.class),
                "Wallet should not yet contain any Blue Diamond");

    }

    /**
     * Normal Case.
     * Two Green Diamonds should automatically combine into one Blue Diamond.
     */
    @Test
    public void testCollectDoubleDiamond() {
        mockWallet.collect(mockActor, new GreenDiamond());
        mockWallet.collect(mockActor, new GreenDiamond());

        assertEquals(0, mockWallet.getAmount(GreenDiamond.class),
                "No Green Diamond should remain");
        assertEquals(1, mockWallet.getAmount(BlueDiamond.class),
                "Two Green Diamond should combine into one Blue Diamond");
    }

    /**
     * Edge Case.
     * Collecting four Green Diamonds should result in a chain combination producing one Red
     * Diamond.
     */
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

    /**
     * Edge Case.
     * Red Diamonds are the highest tier and should not combine further.
     */
    @Test
    public void testCombineRedDiamond() {
        mockWallet.collect(mockActor, new RedDiamond());
        String combination = mockWallet.collect(mockActor, new RedDiamond());

        assertEquals(2, mockWallet.getAmount(RedDiamond.class),
                "Wallet should contain exactly two Red Diamond");
        assertTrue(combination.isEmpty(),
                "Red Diamond cannot combine further");
    }

    /**
     * Normal Case.
     * Deducting a diamond should remove it from the wallet correctly.
     */
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

    /**
     * Normal Case
     * Deducting one diamond when multiple exist should only remove one instance.
     */
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

    /**
     * Edge Case.
     * Attempting to deduct from an empty wallet should not cause negative values or errors.
     */
    @Test
    public void testDeductDiamondFromEmptyWallet() {
        GreenDiamond green = new GreenDiamond();
        mockWallet.deduct(green);

        assertEquals(0, mockWallet.getAmount(GreenDiamond.class),
                "Wallet should not have negative diamond counts");
    }

    /**
     * Invalid Case.
     * Collecting a non-diamond item should not be accepted.
     */
    @Test
    public void testCollectNonDiamondType() {
        Item nonDiamond = mock(Item.class);
        assertThrows(ClassCastException.class, () -> mockWallet.collect(
                mockActor, (Diamond) nonDiamond),
                "Collecting a non-diamond item");
    }

    /**
     * Normal Case.
     * When an actor defeats an animal, their wallet should receive one random diamond.
     */
    @Test
    public void testAnimalDeadThenWalletReceivesDiamond() {
        Actor mockActor = mock(Actor.class);
        Actor mockAnimal = mock(Actor.class);
        GameMap mockMap = mock(GameMap.class);
        Weapon mockWeapon = mock(Weapon.class);
        WalletFunction mockWallet = mock(WalletFunction.class);
        Diamond mockDiamond = mock(Diamond.class);

        when(mockAnimal.isConscious()).thenReturn(false);
        when(mockActor.getItemInventoryAs(WalletFunction.class)).thenReturn(List.of(mockWallet));

        try (var mockedDiamond = mockStatic(Diamond.class)) {
            mockedDiamond.when(Diamond::getRandomDiamond).thenReturn(mockDiamond);
            AttackAction action = new AttackAction(mockAnimal, "north", "attacks", mockWeapon);
            action.execute(mockActor, mockMap);
            verify(mockWallet, times(1)).collect(mockActor, mockDiamond);
        }
    }

    /**
     * Edge Case.
     * If the animal is still alive after the attack, no diamond should be awarded.
     */
    @Test
    public void testAnimalAliveThenNoDiamondReceived() {
        Actor mockActor = mock(Actor.class);
        Actor mockAnimal = mock(Actor.class);
        GameMap mockMap = mock(GameMap.class);
        Weapon mockWeapon = mock(Weapon.class);
        WalletFunction mockWallet = mock(WalletFunction.class);

        when(mockAnimal.isConscious()).thenReturn(true);
        when(mockActor.getItemInventoryAs(WalletFunction.class)).thenReturn(List.of(mockWallet));

        try (var mockedDiamond = mockStatic(Diamond.class)) {
            AttackAction action = new AttackAction(mockAnimal, "north", "attacks", mockWeapon);
            action.execute(mockActor, mockMap);
            mockedDiamond.verifyNoInteractions();
            verify(mockWallet, never()).collect(any(), any());
        }
    }

    /**
     * Invalid Case.
     * Tests when the actor defeats an animal but has no wallet.
     */
    @Test
    public void testAnimalDeadButActorHasNoWallet() {
        Actor mockActor = mock(Actor.class);
        Actor mockAnimal = mock(Actor.class);
        GameMap mockMap = mock(GameMap.class);
        Weapon mockWeapon = mock(Weapon.class);
        Diamond mockDiamond = mock(Diamond.class);

        when(mockAnimal.isConscious()).thenReturn(false);
        when(mockActor.getItemInventoryAs(WalletFunction.class)).thenReturn(List.of());

        try (var mockedDiamond = mockStatic(Diamond.class)) {
            mockedDiamond.when(Diamond::getRandomDiamond).thenReturn(mockDiamond);
            AttackAction action = new AttackAction(mockAnimal, "north", "attacks", mockWeapon);
            action.execute(mockActor, mockMap);
            mockedDiamond.verify(Diamond::getRandomDiamond, times(1));
        }
    }
}
