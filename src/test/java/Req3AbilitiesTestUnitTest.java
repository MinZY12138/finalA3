import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.WearAction;
import game.actors.Abilities;
import game.actors.Player;
import game.actors.animals.Deer;
import game.items.equipments.armors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Unit tests for Requirement 3: Armor system (Abilities and block/heal)
 *
 * @author Shee Seng Cheng
 * @version 1.0
 */
public class Req3AbilitiesTestUnitTest
{
    /**
     * Test abilities of armor
     * @author Shee Seng Cheng
     * @version 1.0
     */
    @Nested
    class ArmorAbilitiesTest{
        private Actor actor1, actor2;
        private Armor leatherArmor, ironArmor, diamondArmor;
        private GameMap mockMap;

        @BeforeEach
        public void setUp(){
            // Player originally should have one armor holder
            actor1 = new Player("Testing", 'ඞ',100, 30);

            //animal deer has no armor holder at first (will need to manually add if it needs to have 1)
            actor2 = new Deer();

            // Fake map
            mockMap = mock(GameMap.class);

            // Armor object
            leatherArmor = new LeatherArmor();
            ironArmor = new IronArmor();
            diamondArmor = new DiamondArmor();
        }

        /**
         * Helper to let actor wear an armor
         * @param armor the armor to wear
         * @param armor_holder the armor holder
         * @param target the target actor
         */
        private void wearArmor(Wearable armor, Wearing armor_holder, Actor target) {
            new WearAction(armor, armor_holder, target).execute(target, mockMap);
        }

        /**
         * Helper to check if actor has certain ability or not.
         * @param actor the actor to check with
         * @param ability the ability to check with
         */
        private void assertHasAbility(Actor actor, Enum<Abilities> ability) {
            assertTrue(actor.hasAbility(ability), "Actor should have " + ability);
        }

        /**
         * Helper to check if actor has no ability or not
         * @param actor the actor to check with
         * @param ability the ability to check with
         */
        private void assertNoAbility(Actor actor, Enum<Abilities> ability) {
            assertFalse(actor.hasAbility(ability), "Actor should not have " + ability);
        }

        /**
         * Helper to check if wear action has/has not in the action list or not
         * pre-conditions needed only when location has an actor (display message is fixed)
         * @param wantWearAction indicate either wear action must inside the list or not
         * @param actionList the list to check with
         */
        private void assertWearAction(boolean wantWearAction, ActionList actionList){
            boolean success = false;
            for (Action action : actionList){
                if (action instanceof WearAction)
                {
                    success = true;
                    break;
                }
            }
            if (wantWearAction)
            {
                assertTrue(success,
                        "Armor should provide WearAction when actor has an ArmorHolder");
            }
            else{
                assertFalse(success,
                        "Armor should not provide WearAction when actor has no ArmorHolder");
            }
        }

        /**
         * Helper to check actor only can have 1 ability.
         * @param actor the actor to check with
         * @param expectedAbility the ability to check with
         */
        private void assertOnlyAbility(Actor actor, Enum<Abilities> expectedAbility) {
            if (expectedAbility == Abilities.IMMUNE_STATUSES) {
                assertHasAbility(actor, Abilities.IMMUNE_STATUSES);
                assertNoAbility(actor, Abilities.COLD_RESISTANT);
            } else if (expectedAbility == Abilities.COLD_RESISTANT) {
                assertNoAbility(actor, Abilities.IMMUNE_STATUSES);
                assertHasAbility(actor, Abilities.COLD_RESISTANT);
            }
        }


        //Test armor

        /**
         * Positive test case:
         *      Armor at Player's location should return a
         *      WearAction when actor has an ArmorHolder.
         */
        @Test
        void testAllowableActions_ReturnsWearActionWhenActorHasArmorHolder() {
            // Arrange
            Location mockLocation = mock(Location.class);
            when(mockLocation.containsAnActor()).thenReturn(true);
            when(mockLocation.getActor()).thenReturn(actor1);

            // Action list
            ActionList actions = leatherArmor.allowableActions(mockLocation);

            // Assert it should return wear action
            assertWearAction(true, actions);
        }

        /**
         * Negative test case:
         *      Armor at actor's location should NOT return a
         *      WearAction when actor has NO ArmorHolder.
         */
        @Test
        void testAllowableActions_NoWearActionWithoutArmorHolder() {
            // Arrange
            Location mockLocation = mock(Location.class);
            when(mockLocation.containsAnActor()).thenReturn(true);
            when(mockLocation.getActor()).thenReturn(actor2);

            // Act
            ActionList actions = leatherArmor.allowableActions(mockLocation);

            // Assert
            assertWearAction(false, actions);
        }

        /**
         * Edge test case: If location has no actor, allowableActions should return an empty ActionList.
         */
        @Test
        void testAllowableActions_NoActorAtLocation_ReturnsEmptyActionList() {
            // Arrange
            Location mockLocation = mock(Location.class);
            when(mockLocation.containsAnActor()).thenReturn(false);

            // Act
            ActionList actions = ironArmor.allowableActions(mockLocation);

            // Assert
            assertEquals(0, actions.size(),
                    "Armor should return empty ActionList when no actor is present at the location");
        }

        // Test abilities

        /**
         * Combined test case:
         *      -Wearing Diamond Armor should grant IMMUNE ability.
         *      -Wearing Iron Armor should have no ability
         *      -Wearing Leather should have Cold Resistance
         */
        @Test
        void testWearArmor_GivesAbility() {
            // Test player
            Wearing holder = actor1.getItemInventoryAs(Wearing.class).get(0);

            // Arrange
            Location mockLocation = mock(Location.class);
            when(mockMap.locationOf(actor1)).thenReturn(mockLocation);

            // player wear diamond
            wearArmor(diamondArmor, holder, actor1);
            assertOnlyAbility(actor1, Abilities.IMMUNE_STATUSES);

            //Iron
            wearArmor(ironArmor, holder, actor1);
            assertNoAbility(actor1, Abilities.IMMUNE_STATUSES);
            assertNoAbility(actor1, Abilities.COLD_RESISTANT);

            //Leather
            wearArmor(leatherArmor, holder, actor1);
            assertOnlyAbility(actor1, Abilities.COLD_RESISTANT);

            // Test animal deer
            // append an armor holder into the deer inventory for testing
            Wearing holder1 = ArmorHolderInjector.createArmorHolder(actor2);
            actor2.addItemToInventory((Item)holder1);

            // Arrange
            Location mockLocation1 = mock(Location.class);
            when(mockMap.locationOf(actor2)).thenReturn(mockLocation1);

            //Check animal deer should have the same abiliites as player.
            wearArmor(diamondArmor, holder1, actor2);
            assertOnlyAbility(actor2, Abilities.IMMUNE_STATUSES);

            //Iron
            wearArmor(ironArmor, holder1, actor2);
            assertNoAbility(actor2, Abilities.IMMUNE_STATUSES);
            assertNoAbility(actor2, Abilities.COLD_RESISTANT);

            //Leather
            wearArmor(leatherArmor, holder1, actor2);
            assertOnlyAbility(actor2, Abilities.COLD_RESISTANT);
        }

        /**
         * Edge test case:
         *      Wearing Diamond Armor after Leather Armor should
         *      replace COLD_RESISTANT with IMMUNE ability.
         */
        @Test
        void testWearDiamondAfterLeather_ReplacesAbility() {
            Wearing holder = actor1.getItemInventoryAs(Wearing.class).get(0);
            Location mockLocation = mock(Location.class);
            when(mockMap.locationOf(actor1)).thenReturn(mockLocation);

            // Wear Leather first
            wearArmor(leatherArmor, holder, actor1);
            assertOnlyAbility(actor1, Abilities.COLD_RESISTANT);

            // Then wear Diamond
            wearArmor(diamondArmor, holder, actor1);
            assertOnlyAbility(actor1, Abilities.IMMUNE_STATUSES);
        }

        /**
         * Edge test case: Switching from Leather to Diamond replaces ability correctly.
         */
        @Test
        void testSwitchArmor_LeatherToDiamond_ReplacesColdWithImmune() {
            Wearing holder = actor1.getItemInventoryAs(Wearing.class).get(0);
            Location mockLocation = mock(Location.class);
            when(mockMap.locationOf(actor1)).thenReturn(mockLocation);

            wearArmor(leatherArmor, holder, actor1);
            assertOnlyAbility(actor1, Abilities.COLD_RESISTANT);

            wearArmor(diamondArmor, holder, actor1);
            assertOnlyAbility(actor1, Abilities.IMMUNE_STATUSES);
        }

        /**
         * Negative test case:
         *      Armor should never grant both IMMUNE and COLD_RESISTANT simultaneously.
         */
        @Test
        void testNoArmorCanGrantMultipleAbilities() {
            Wearing holder = actor1.getItemInventoryAs(Wearing.class).get(0);
            Location mockLocation = mock(Location.class);
            when(mockMap.locationOf(actor1)).thenReturn(mockLocation);

            wearArmor(diamondArmor, holder, actor1);
            assertFalse(actor1.hasAbility(Abilities.COLD_RESISTANT) &&
                            actor1.hasAbility(Abilities.IMMUNE_STATUSES),
                    "Actor should not have both IMMUNE and COLD_RESISTANT at the same time");
        }

        /**
         * Functional test case:
         *      Wearing new armor should replace the currently worn armor.
         *      Actor can only have one active armor at a time.
         */
        @Test
        void testOnlyOneArmorActive_ReplaceOldWithNew() {
            Wearing holder = actor1.getItemInventoryAs(Wearing.class).get(0);
            Location mockLocation = mock(Location.class);
            when(mockMap.locationOf(actor1)).thenReturn(mockLocation);

            // Wear Leather Armor first (block = 2)
            wearArmor(leatherArmor, holder, actor1);
            assertEquals(2, holder.getBlockArmor(),
                    "After wearing Leather Armor, block value should be 2");

            //Wear Iron Armor next (block = 5)
            wearArmor(ironArmor, holder, actor1);
            assertEquals(5, holder.getBlockArmor(),
                    "After wearing Iron Armor, block value should now be 5");

            // Wear Diamond Armor next (block = 10)
            wearArmor(diamondArmor, holder, actor1);
            assertEquals(10, holder.getBlockArmor(),
                    "After wearing Diamond Armor, block value should now be 10");

            // Old armor should be replaced getArmorInfo() should reflect Diamond Armor
            assertTrue(holder.getArmorInfo().toLowerCase().contains("diamond"),
                    "Holder armor info should now refer to Diamond Armor only");
        }
    }
}
