import edu.monash.fit2099.engine.GameEngineException;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.*;
import game.actors.animals.*;
import game.actors.animals.AnimalInfo;
import game.actors.statuses.Poisoning;
import game.actors.statuses.StatusType;
import game.behaviours.AttackBehaviourInjector;
import game.grounds.*;
import game.grounds.spawnable.*;
import game.grounds.trees.yewBerrys.YewBerryTree;
import game.items.fruits.Apple;
import game.items.fruits.YewBerry;

import game.weapons.WeaponInfo;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for Requirement 2 & 3: Tree Growth System and Swamp Spawning.
 */
public class Req2UnitTest {

    /**
     * A simple World subclass used only for testing.
     * It ensures GameMap.actorLocations is initialised.
     */
    class DummyWorld extends World {
        public DummyWorld() {
            super(new Display());
        }

        @Override
        public void run() {
            // No game loop needed for unit tests
        }
    }

    private GameMap testMap;

    private GameMap createMiniTestMap() throws GameEngineException {
        DefaultGroundCreator groundCreator = new DefaultGroundCreator();
        groundCreator.registerGround('.', Snow::new);
        List<String> mapLayout = Arrays.asList("...", "...", "...");
        GameMap map = new GameMap("MiniTestMap", groundCreator, mapLayout);
        map.at(0, 0).tick();
        return map;
    }

    @BeforeEach
    void setUp() throws GameEngineException {
        testMap = createMiniTestMap();
        DummyWorld world = new DummyWorld();
        world.addGameMap(testMap);
    }

    // ---------------- CROCODILE TESTS ---------------- //
    @Nested
    class CrocodileTests {

        /**
         * Positive Test:
         */
        @Test
        void testCrocodileInitialStats() {
            assertEquals(300, AnimalInfo.CROCODILE.getHIT_POINT(), "Crocodile should start with 300 HP");
            assertEquals(55, AnimalInfo.CROCODILE.getWARMTH_LEVEL(), "Crocodile should start with warmth 55");
            assertEquals(80, WeaponInfo.TEETH.getDamage());
            assertEquals(75, WeaponInfo.TEETH.getHitChance());
        }


        /**
         * Positive Test: The actor become unconscious when warm is 0
         */
        @Test
        void testCrocodileBecomesUnconsciousWhenWarmthZero() {
            Crocodile croc = new Crocodile();
            for (int i = 0; i < 56; i++) {
                croc.decreaseWarmthLevel();
            }
            assertTrue(croc.isCold());
        }

        /**
         * Negative Test: The actor remain conscious when the warm is above 0
         */
        @Test
        void testCrocodileStillConsciousWhenWarmthAboveZero() {
            Crocodile croc = new Crocodile();

            // Decrease warmth only partially (e.g. to 10)
            for (int i = 0; i < 45; i++) {  // assuming initial = 55 → now 10
                croc.decreaseWarmthLevel();
            }

            // Expect crocodile to still be conscious (not cold)
            assertFalse(croc.isCold(), "Crocodile should still be conscious when warmth is above 0");
        }

        /**
         * Edge Test: Happens right before and after the threshold value — warmth = 1 or 0.
         *
         */
        @Test
        void testCrocodileAtWarmthOneStillConsciousButBecomesColdAtZero() {
            Crocodile croc = new Crocodile();

            // Reduce to warmth = 1
            for (int i = 0; i < 54; i++) { // initial = 55
                croc.decreaseWarmthLevel();
            }
            assertFalse(croc.isCold(), "Crocodile should still be conscious at warmth = 1");

            // One more decrease → warmth = 0
            croc.decreaseWarmthLevel();
            assertTrue(croc.isCold(), "Crocodile should become unconscious when warmth = 0");
        }


        /**
         * Positive Test: Bite action occur when actor is nearby
         *
         */
        @Test
        void testCrocodileGeneratesBiteActionWhenActorNearby() throws GameEngineException {
            Crocodile crocodile = new Crocodile();
            Actor target = new Actor("Dummy", 'D', 100) {
                @Override
                public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
                    return new DoNothingAction();
                }
            };

            testMap.at(1, 1).addActor(crocodile);
            testMap.at(1, 2).addActor(target);

            Action action = AttackBehaviourInjector.getAttackBehaviour().generateAction(crocodile, testMap);
            assertNotNull(action, "Crocodile should generate a bite action when target nearby");

            String description = action.menuDescription(crocodile).toLowerCase();
            assertTrue(description.contains("bite"), "Generated action description should contain 'bite'");
        }

        /**
         * Negative Test: When no nearby target exists, no attack action is generated.
         */
        @Test
        void testCrocodileDoesNotGenerateBiteActionWhenNoActorNearby() throws GameEngineException {
            Crocodile crocodile = new Crocodile();
            testMap.at(1, 1).addActor(crocodile); // Only crocodile, no target

            Action action = AttackBehaviourInjector.getAttackBehaviour().generateAction(crocodile, testMap);

            assertNull(action, "Crocodile should NOT generate a bite action when no actor is nearby");
        }

        /**
         * Edge Test: a target exists but at the farthest possible distance that still counts as nearby
         *
         */
        @Test
        void testCrocodileBiteActionAtEdgeOfAttackRange() throws GameEngineException {
            Crocodile crocodile = new Crocodile();
            Actor target = new Actor("EdgeTarget", 'T', 100) {
                @Override
                public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
                    return new DoNothingAction();
                }
            };

            // Place target diagonally adjacent (edge of adjacency)
            testMap.at(1, 1).addActor(crocodile);
            testMap.at(2, 2).addActor(target);

            Action action = AttackBehaviourInjector.getAttackBehaviour().generateAction(crocodile, testMap);

            assertNotNull(action, "Crocodile should generate a bite action when target is at the edge of attack range");
            assertTrue(action.menuDescription(crocodile).toLowerCase().contains("bite"),
                    "Bite action description should appear even for diagonal adjacency");
        }


    }

    // ---------------- SPAWNER TESTS ---------------- //
    @Nested
    class SpawnerTests {
        private Earth earth;
        private GameMap forest;
        private GameMap plains;

        @BeforeEach
        void setUp() throws Exception {
            earth = new Earth(new Display());
            earth.constructWorld();

            var field = World.class.getDeclaredField("gameMaps");
            field.setAccessible(true);
            @SuppressWarnings("unchecked")
            ArrayList<GameMap> maps = (ArrayList<GameMap>) field.get(earth);

            forest = null;
            plains = null;
            for (GameMap map : maps) {
                if (map.toString().equals("Forest")) {
                    forest = map;
                } else if (map.toString().equals("Plains")) {
                    plains = map;
                }
            }

            assertNotNull(forest, "Forest map should exist in Earth world");
            assertNotNull(plains, "Plains map should exist in Earth world");
        }

        @Nested
        class ForestSpawnerTest {

            /**
             * Positive Test: Meadow in Forest spawn Crocodile
             */
            @Test
            void testForestContainsMeadowThatSpawnsCrocodile() throws Exception {
                Location loc = forest.at(0, 1);
                assertTrue(loc.getGround() instanceof Meadow, "Expected Meadow at (0,1) in Forest");

                Meadow meadow = (Meadow) loc.getGround();
                var field = SpawnGround.class.getDeclaredField("SPAWNABLE");
                field.setAccessible(true);
                @SuppressWarnings("unchecked")
                List<Spawnable> spawnables = (List<Spawnable>) field.get(meadow);

                boolean foundCrocodile = false;
                for (Spawnable sp : spawnables) {
                    if (sp.create() instanceof Crocodile) {
                        foundCrocodile = true;
                        break;
                    }
                }

                assertTrue(foundCrocodile, "Forest Meadow should spawn a Crocodile");
            }

            /**
             * Negative Test: Meadow should not spawn Bear of Wolf
             */
            @Test
            void testForestMeadowDoesNotSpawnBearOrWolf() throws Exception {
                Location loc = forest.at(0, 1);
                assertTrue(loc.getGround() instanceof Meadow, "Expected Meadow at (0,1) in Forest");

                Meadow meadow = (Meadow) loc.getGround();
                var field = SpawnGround.class.getDeclaredField("SPAWNABLE");
                field.setAccessible(true);
                @SuppressWarnings("unchecked")
                List<Spawnable> spawnables = (List<Spawnable>) field.get(meadow);

                boolean hasInvalidAnimal = false;
                for (Spawnable sp : spawnables) {
                    if (sp.create() instanceof Bear || sp.create() instanceof Wolf) {
                        hasInvalidAnimal = true;
                        break;
                    }
                }

                assertFalse(hasInvalidAnimal, "Forest Meadow should NOT spawn Bear or Wolf");
            }

        }

        @Nested
        class PlainsSpawnerTest {

            /**
             * Positive Test: Tundra in Plain spawn Crocodile
             */
            @Test
            void testPlainsContainsTundraThatSpawnsCrocodile() throws Exception {
                Location loc = plains.at(0, 4);
                assertTrue(loc.getGround() instanceof Tundra, "Expected Tundra at (0,4) in Plains");

                Tundra tundra = (Tundra) loc.getGround();
                var field = SpawnGround.class.getDeclaredField("SPAWNABLE");
                field.setAccessible(true);
                @SuppressWarnings("unchecked")
                List<Spawnable> spawnables = (List<Spawnable>) field.get(tundra);

                boolean foundCrocodile = false;
                for (Spawnable sp : spawnables) {
                    if (sp.create() instanceof Crocodile) {
                        foundCrocodile = true;
                        break;
                    }
                }

                assertTrue(foundCrocodile, "Plains Tundra should spawn a Crocodile");
            }


        }
    }

    // ---------------- SWAMP SPAWNER TESTS ---------------- //
    @Nested
    class SwampSpawnerTests {
        private GameMap forestMap;
        private GameMap plainsMap;
        private Location forestSwampLoc;
        private Location plainsSwampLoc;

        @BeforeEach
        void setupMaps() throws GameEngineException {
            DefaultGroundCreator groundCreator = new DefaultGroundCreator();
            groundCreator.registerGround('.', Snow::new);

            List<String> layout = Arrays.asList("...", "...", "...");
            forestMap = new GameMap("Forest", groundCreator, layout);
            plainsMap = new GameMap("Plains", groundCreator, layout);

            DummyWorld world = new DummyWorld();
            world.addGameMap(forestMap);
            world.addGameMap(plainsMap);

            Swamp forestSwamp = new Swamp(List.of(Crocodile::new, Deer::new));
            forestSwamp.setSpawnChance(100);
            forestSwampLoc = forestMap.at(1, 1);
            forestSwampLoc.setGround(forestSwamp);

            Swamp plainsSwamp = new Swamp(List.of(Crocodile::new));
            plainsSwamp.setSpawnChance(100);
            plainsSwampLoc = plainsMap.at(1, 1);
            plainsSwampLoc.setGround(plainsSwamp);
        }

        /**
         * Positive Test: Swamp spawn animal when there is actor nearby
         */
        @Test
        void testSwampSpawnsWhenActorNearby() throws GameEngineException {
            Swamp swamp = new Swamp(List.of(Crocodile::new));
            swamp.setSpawnChance(100);
            Location loc = forestMap.at(0, 0);
            loc.setGround(swamp);

            swamp.tick(loc);
            assertFalse(loc.containsAnActor(), "Swamp should NOT spawn without nearby actor");

            Actor nearby = new Actor("Explorer", '@', 100) {
                @Override
                public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
                    return new DoNothingAction();
                }
            };
            forestMap.at(0, 1).addActor(nearby);

            swamp.tick(loc);
            assertTrue(loc.containsAnActor(), "Swamp should spawn an animal when actor nearby");
        }

        /**
         * Negative Test: Swamp should not spawn when there are no nearby actors.
         */
        @Test
        void testSwampDoesNotSpawnWithoutNearbyActor() throws GameEngineException {
            Swamp swamp = new Swamp(List.of(Crocodile::new));
            swamp.setSpawnChance(100); // even with 100% chance, no actor means no spawn
            Location loc = forestMap.at(0, 0);
            loc.setGround(swamp);

            // Run several ticks — still no nearby actor
            for (int i = 0; i < 5; i++) {
                swamp.tick(loc);
            }

            assertFalse(loc.containsAnActor(),
                    "Swamp should NOT spawn any animal when no actor is nearby, even after multiple ticks.");
        }

        /**
         * Edge Test: Swamp should not spawn when spawn chance is 0%.
         */
        @Test
        void testSwampDoesNotSpawnWhenChanceIsZero() throws GameEngineException {
            Swamp swamp = new Swamp(List.of(Crocodile::new));
            swamp.setSpawnChance(0);
            Location loc = forestMap.at(0, 0);
            loc.setGround(swamp);

            Actor nearby = new Actor("Explorer", '@', 100) {
                @Override
                public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
                    return new DoNothingAction();
                }
            };
            forestMap.at(0, 1).addActor(nearby);

            // Even with nearby actor, 0% spawn chance should prevent spawning
            for (int i = 0; i < 5; i++) {
                swamp.tick(loc);
            }

            assertFalse(loc.containsAnActor(),
                    "Swamp should NOT spawn any animal when spawn chance is 0%, even if actor is nearby.");
        }

        /**
         * Positive Test: Swamp in Forest spawns Crocodile and Deer
         */
        @Test
        void testForestSwampSpawnsCrocodileAndDeer() throws GameEngineException {
            Actor observer = new Actor("Ranger", '@', 100) {
                @Override
                public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
                    return new DoNothingAction();
                }
            };
            forestMap.at(1, 2).addActor(observer);

            Ground swamp = forestSwampLoc.getGround();
            for (int i = 0; i < 5; i++) {
                swamp.tick(forestSwampLoc);
            }

            assertTrue(forestSwampLoc.containsAnActor(), "Forest swamp should spawn an animal");
            Actor spawned = forestSwampLoc.getActor();
            boolean validType = (spawned instanceof Crocodile) || (spawned instanceof Deer);
            assertTrue(validType, "Forest swamp must spawn either a Crocodile or a Deer");
        }

        /**
         * Positive Test: Swamp on Plain spawn Crocodile
         */
        @Test
        void testPlainsSwampSpawnsOnlyCrocodile() throws GameEngineException {
            Actor villager = new Actor("Villager", '@', 100) {
                @Override
                public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
                    return new DoNothingAction();
                }
            };
            plainsMap.at(1, 2).addActor(villager);

            Ground swamp = plainsSwampLoc.getGround();
            for (int i = 0; i < 5; i++) {
                swamp.tick(plainsSwampLoc);
            }

            assertTrue(plainsSwampLoc.containsAnActor(), "Plains swamp should spawn an animal");
            Actor spawned = plainsSwampLoc.getActor();
            assertTrue(spawned instanceof Crocodile, "Plains swamp must spawn only Crocodile");
        }

        /**
         * Positive Test: Actor spawn from swamp will get a poison status
         */
        @Test
        void testSwampSpawnedAnimalIsPoisoned() throws GameEngineException {
            Actor traveler = new Actor("Traveler", '@', 100) {
                @Override
                public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
                    return new DoNothingAction();
                }
            };
            forestMap.at(2, 1).addActor(traveler);

            Swamp swamp = new Swamp(List.of(Crocodile::new));
            swamp.setSpawnChance(100);
            Location loc = forestMap.at(2, 2);
            loc.setGround(swamp);

            swamp.tick(loc);

            assertTrue(loc.containsAnActor(), "Swamp should spawn an animal");
            Actor croc = loc.getActor();

            boolean hasPoison = false;

            for (Status effect : croc.statuses()) {
                if (effect instanceof Poisoning) {
                    hasPoison = true;
                    assertEquals(10, StatusType.POISONING2.getDURATION(), "Poison should last 10 turns");
                    assertEquals(5, StatusType.POISONING2.getDAMAGE(), "Poison should deal 5 damage per turn");
                }
            }

            assertTrue(hasPoison, "Spawned crocodile should have a Poisoning status");
        }



    }


    @Nested
    class spawnCapabilitiesTest{

        private Location centerLoc;

        @BeforeEach
        void setupCenterLocation() {
            // Assign a valid location in the shared testMap before each test
            centerLoc = testMap.at(1, 1);
        }


        /**
         * Positive Test: When spawner spawn deer, it will spawn an apple in one "exit"
         */
        @Test
        void testDeerSpawnsAppleInNearbyLocation() {
            Deer deer = new Deer();
            // Trigger deer’s spawnCapability at center
            deer.spawnCapability(centerLoc);

            boolean foundApple = false;
            for (Location nearby : centerLoc.getNearbyLocations(1)) {
                if (nearby.getItems().stream().anyMatch(item -> item instanceof Apple)) {
                    foundApple = true;
                    break;
                }
            }

            assertTrue(foundApple, "Deer spawn should create at least one Apple nearby.");
        }

        /**
         * Negative Test: Non-Deer animals should NOT spawn apples when spawned.
         */
        @Test
        void testNonDeerAnimalsDoNotSpawnApples() {
            // Use Bear (could also test Crocodile or Wolf)
            Bear bear = new Bear();
            bear.spawnCapability(centerLoc);

            boolean foundApple = false;
            for (Location nearby : centerLoc.getNearbyLocations(1)) {
                if (nearby.getItems().stream().anyMatch(item -> item instanceof Apple)) {
                    foundApple = true;
                    break;
                }
            }

            assertFalse(foundApple, "Non-Deer animals should NOT spawn Apples nearby.");
        }

        /**
         * Edge Test: Deer should spawn exactly 1 Apple in nearby locations.
         */
        @Test
        void testDeerSpawnsExactlyOneApple() {
            Deer deer = new Deer();
            deer.spawnCapability(centerLoc);

            int appleCount = 0;
            for (Location nearby : centerLoc.getNearbyLocations(1)) {
                appleCount += (int) nearby.getItems().stream().filter(item -> item instanceof Apple).count();
            }

            assertEquals(1, appleCount, "Deer should spawn exactly one Apple nearby.");
        }


        /**
         * Positive Test: When spawner spawn bear, it will spawn yew berry for each exit with 50% chance
         */
        @Test
        void testBearSpawnsYewBerriesNearby() {
            Bear bear = new Bear();
            bear.spawnCapability(centerLoc);

            boolean foundYewBerry = false;
            for (Location nearby : centerLoc.getNearbyLocations(1)) {
                if (nearby.getItems().stream().anyMatch(item -> item instanceof YewBerry)) {
                    foundYewBerry = true;
                    break;
                }
            }

            assertTrue(foundYewBerry, "Bear spawn should create at least one YewBerry nearby.");
        }

        /**
         * Negative Test: Non-Bear animals should NOT spawn YewBerries when spawned.
         */
        @Test
        void testNonBearAnimalsDoNotSpawnYewBerries() {
            // Try with a Deer (could also test Wolf or Crocodile)
            Deer deer = new Deer();
            deer.spawnCapability(centerLoc);

            boolean foundYewBerry = false;
            for (Location nearby : centerLoc.getNearbyLocations(1)) {
                if (nearby.getItems().stream().anyMatch(item -> item instanceof YewBerry)) {
                    foundYewBerry = true;
                    break;
                }
            }

            assertFalse(foundYewBerry, "Non-Bear animals should NOT spawn YewBerries nearby.");
        }

        /**
         * Edge Test: Bear at map edge should still spawn YewBerries in available nearby locations.
         */
        @Test
        void testBearSpawnsYewBerriesAtMapEdge() {
            Location edgeLoc = testMap.at(0, 0); // corner — minimal exits
            Bear bear = new Bear();
            bear.spawnCapability(edgeLoc);

            boolean foundYewBerry = false;
            for (Location nearby : edgeLoc.getNearbyLocations(1)) {
                if (nearby.getItems().stream().anyMatch(item -> item instanceof YewBerry)) {
                    foundYewBerry = true;
                    break;
                }
            }

            assertTrue(foundYewBerry, "Bear should still spawn YewBerries even at the map edge.");
        }


        /**
         * Positive Test: When spawner spawn wolf, a yew berry tree will spawn at one exit
         *                  and drop yew berry when actor nearby instead of five turns
         */
        @Test
        void testWolfSpawnsMatureYewBerryTreeWithDetectMode() {
            Wolf wolf = new Wolf();
            wolf.spawnCapability(centerLoc);

            int treeCount = 0;
            boolean detectModeEnabled = false;

            for (Location nearby : centerLoc.getNearbyLocations(1)) {
                if (nearby.getGround() instanceof YewBerryTree tree) {
                    treeCount++;
                    detectModeEnabled = treeDetectMode(tree);
                }
            }

            assertEquals(1, treeCount, "Wolf should grow exactly one YewBerryTree nearby.");
            assertTrue(detectModeEnabled, "YewBerryTree grown by Wolf should have detect mode enabled.");
        }

        /**
         * Helper to use reflection to check detectMode flag in YewBerryTree.
         */
        private boolean treeDetectMode(YewBerryTree tree) {
            try {
                var field = tree.getClass().getSuperclass().getDeclaredField("detectMode");
                field.setAccessible(true);
                return field.getBoolean(tree);
            } catch (Exception e) {
                fail("Unable to inspect detectMode field: " + e.getMessage());
                return false;
            }
        }

        /**
         * Negative Test: Non-Wolf animals should NOT spawn a YewBerryTree nearby.
         */
        @Test
        void testNonWolfAnimalsDoNotSpawnYewBerryTree() {
            Bear bear = new Bear(); // can replace with Deer or Crocodile
            bear.spawnCapability(centerLoc);

            boolean foundTree = false;
            for (Location nearby : centerLoc.getNearbyLocations(1)) {
                if (nearby.getGround() instanceof YewBerryTree) {
                    foundTree = true;
                    break;
                }
            }

            assertFalse(foundTree, "Non-Wolf animals should NOT spawn YewBerryTree nearby.");
        }

        /**
         * Edge Test: Wolf should only grow one YewBerryTree even with multiple available exits.
         */
        @Test
        void testWolfSpawnsOnlyOneTree() {
            Wolf wolf = new Wolf();
            wolf.spawnCapability(centerLoc);

            int treeCount = 0;
            for (Location nearby : centerLoc.getNearbyLocations(1)) {
                if (nearby.getGround() instanceof YewBerryTree) {
                    treeCount++;
                }
            }

            assertEquals(1, treeCount, "Wolf should spawn exactly one YewBerryTree, not more.");
        }




        /**
         * Positive Test: When spawner spawn crocodile, nearby actor will get poison for 3 turn and 10 damage
         */
        @Test
        void testCrocodilePoisonsNearbyActors() throws GameEngineException {
            Crocodile crocodile = new Crocodile();

            // Place two nearby actors
            Actor actor1 = new Actor("Villager1", '@', 100) {
                @Override
                public edu.monash.fit2099.engine.actions.Action playTurn(
                        edu.monash.fit2099.engine.actions.ActionList actions,
                        edu.monash.fit2099.engine.actions.Action lastAction,
                        GameMap map, Display display) {
                    return new edu.monash.fit2099.engine.actions.DoNothingAction();
                }
            };

            Actor actor2 = new Actor("Villager2", '@', 100) {
                @Override
                public edu.monash.fit2099.engine.actions.Action playTurn(
                        edu.monash.fit2099.engine.actions.ActionList actions,
                        edu.monash.fit2099.engine.actions.Action lastAction,
                        GameMap map, Display display) {
                    return new edu.monash.fit2099.engine.actions.DoNothingAction();
                }
            };

            testMap.at(0, 1).addActor(actor1);
            testMap.at(1, 0).addActor(actor2);

            // Trigger crocodile spawn
            crocodile.spawnCapability(centerLoc);

            boolean actor1Poisoned = false;
            boolean actor2Poisoned = false;

            for (Status effect : actor1.statuses()) {
                if (effect instanceof Poisoning) {
                    actor1Poisoned = true;
                    assertEquals(10, StatusType.POISONING1.getDAMAGE(), "Poison damage should be 10 per turn.");
                    assertEquals(3, StatusType.POISONING1.getDURATION(), "Poison duration should be 3 turns.");
                }
            }

            for (Status effect : actor2.statuses()) {
                if (effect instanceof Poisoning) {
                    actor2Poisoned = true;
                    assertEquals(10, StatusType.POISONING1.getDAMAGE(), "Poison damage should be 10 per turn.");
                    assertEquals(3, StatusType.POISONING1.getDURATION(), "Poison duration should be 3 turns.");
                }
            }

            assertTrue(actor1Poisoned || actor2Poisoned, "At least one nearby actor should be poisoned by Crocodile spawn.");
        }

        /**
         * Negative Test: Non-Crocodile animals should NOT poison nearby actors when spawned.
         */
        @Test
        void testNonCrocodileAnimalsDoNotPoisonNearbyActors() throws GameEngineException {
            Bear bear = new Bear();  // You can also test with Deer or Wolf

            // Place nearby actor
            Actor villager = new Actor("Villager", '@', 100) {
                @Override
                public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
                    return new DoNothingAction();
                }
            };
            testMap.at(0, 1).addActor(villager);

            // Trigger spawn (bear should NOT poison)
            bear.spawnCapability(centerLoc);

            boolean hasPoison = villager.statuses().stream().anyMatch(s -> s instanceof Poisoning);

            assertFalse(hasPoison, "Non-Crocodile animals should NOT apply poison to nearby actors.");
        }

        /**
         * Edge Test: Crocodile poisons actor even when actor is at map edge.
         */
        @Test
        void testCrocodilePoisonsActorAtMapEdge() throws GameEngineException {
            Crocodile crocodile = new Crocodile();

            // Place crocodile near corner
            Location edgeLoc = testMap.at(0, 0);
            Location adjacent = testMap.at(0, 1);

            Actor villager = new Actor("EdgeVillager", '@', 100) {
                @Override
                public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
                    return new DoNothingAction();
                }
            };
            adjacent.addActor(villager);

            crocodile.spawnCapability(edgeLoc);

            boolean poisoned = villager.statuses().stream().anyMatch(s -> s instanceof Poisoning);
            assertTrue(poisoned, "Even at map edge, nearby actor should be poisoned by Crocodile spawn.");
        }




    }
}
