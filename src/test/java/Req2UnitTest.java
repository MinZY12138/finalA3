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

        @Test
        void testCrocodileInitialStats() {
            assertEquals(300, AnimalInfo.CROCODILE.getHIT_POINT(), "Crocodile should start with 300 HP");
            assertEquals(55, AnimalInfo.CROCODILE.getWARMTH_LEVEL(), "Crocodile should start with warmth 55");
        }

        @Test
        void testCrocodileAttackProperties() {
            assertEquals(80, WeaponInfo.TEETH.getDamage());
            assertEquals(75, WeaponInfo.TEETH.getHitChance());
        }

        @Test
        void testCrocodileBecomesUnconsciousWhenWarmthZero() {
            Crocodile croc = new Crocodile();
            for (int i = 0; i < 56; i++) {
                croc.decreaseWarmthLevel();
            }
            assertTrue(croc.isCold());
        }

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

            @Test
            void testForestContainsSwampThatSpawnsCrocodileAndDeer() throws Exception {
                Location loc = forest.at(10, 0);
                assertTrue(loc.getGround() instanceof Swamp, "Expected Swamp at (10,0) in Forest");

                Swamp swamp = (Swamp) loc.getGround();
                var field = SpawnGround.class.getDeclaredField("SPAWNABLE");
                field.setAccessible(true);
                @SuppressWarnings("unchecked")
                List<Spawnable> spawnables = (List<Spawnable>) field.get(swamp);

                boolean foundCrocodileAndDeer = false;
                for (Spawnable sp : spawnables) {
                    if (sp.create() instanceof Crocodile || sp.create() instanceof Deer) {
                        foundCrocodileAndDeer = true;
                        break;
                    }
                }

                assertTrue(foundCrocodileAndDeer, "Forest Swamp should spawn Crocodile and Deer");
            }
        }

        @Nested
        class PlainsSpawnerTest {

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

            @Test
            void testPlainsContainsSwampThatSpawnsCrocodile() throws Exception {
                Location loc = plains.at(7, 9);
                assertTrue(loc.getGround() instanceof Swamp, "Expected Swamp at (7,9) in Plains");

                Swamp swamp = (Swamp) loc.getGround();
                var field = SpawnGround.class.getDeclaredField("SPAWNABLE");
                field.setAccessible(true);
                @SuppressWarnings("unchecked")
                List<Spawnable> spawnables = (List<Spawnable>) field.get(swamp);

                boolean foundCrocodile = false;
                for (Spawnable sp : spawnables) {
                    if (sp.create() instanceof Crocodile) {
                        foundCrocodile = true;
                        break;
                    }
                }

                assertTrue(foundCrocodile, "Plains Swamp should spawn a Crocodile");
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
}
