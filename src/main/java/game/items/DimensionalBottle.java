package game.items;


import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.BreakBottleAction;
import game.grounds.dimensional.DimensionalGround;
import game.grounds.dimensional.DimensionalSite;
import game.grounds.dimensional.StoreLifecycle;
import game.mysteriostore.DimensionalStoreType;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

    /**
     * <h1>Dimensional Bottle</h1>
     *
     * <p>
     * An artefact that fractures reality when shattered on the ground. The bottle can be thrown
     * to a nearby location, converting the ground into {@link DimensionalGround} which will later
     * manifest a store themed by the chosen {@link DimensionalStoreType}.
     * </p>
     */
    public class DimensionalBottle extends Item {

        private static final int DEFAULT_THROW_RADIUS = 3;

        private final List<DimensionalStoreType> storeOptions;
        private final Map<DimensionalStoreType, StoreLifecycle> lifecyclePlans;
        private final int throwRadius;
        private final Random random;

        /**
         * Creates a bottle that can spawn any registered store type.
         */
        public DimensionalBottle() {
            this(createDefaultPlans(), DEFAULT_THROW_RADIUS);
        }

        /**
         * Creates a bottle that can spawn stores from the provided plans.
         *
         * @param lifecyclePlans lifecycle hooks keyed by store type
         * @param throwRadius    how far the bottle can be thrown
         */
        public DimensionalBottle(Map<DimensionalStoreType, StoreLifecycle> lifecyclePlans, int throwRadius) {
            super(ItemInfo.DIMENSIONAL_BOTTLE.getNAME(), ItemInfo.DIMENSIONAL_BOTTLE.getCHAR(), ItemInfo.DIMENSIONAL_BOTTLE.isPORTABLE());
            Objects.requireNonNull(lifecyclePlans, "Lifecycle plans cannot be null.");
            if (lifecyclePlans.isEmpty()) {
                throw new IllegalArgumentException("At least one store plan must be provided.");
            }

            this.lifecyclePlans = Map.copyOf(lifecyclePlans);
            this.storeOptions = List.copyOf(lifecyclePlans.keySet());
            this.throwRadius = Math.max(0, throwRadius);
            this.random = new Random();
        }

        private static Map<DimensionalStoreType, StoreLifecycle> createDefaultPlans() {
            Map<DimensionalStoreType, StoreLifecycle> defaults = new EnumMap<>(DimensionalStoreType.class);
            for (DimensionalStoreType type : DimensionalStoreType.values()) {
                defaults.put(type, StoreLifecycle.NONE);
            }
            return defaults;
        }

        @Override
        public ActionList allowableActions(Actor owner, GameMap map) {
            ActionList actions = super.allowableActions(owner, map);
            if (owner == null) {
                return actions;
            }

            Location origin = map.locationOf(owner);
            if (origin.getGroundAs(DimensionalSite.class) == null) {
                actions.add(new BreakBottleAction(this, origin));
            }

            for (Location target : origin.getNearbyLocations(throwRadius)) {
                if (target.getGroundAs(DimensionalSite.class) != null) {
                    continue;
                }
                actions.add(new BreakBottleAction(this, target));
            }

            return actions;
        }

        /**
         * Shatters the bottle at the specified location, replacing the existing ground with dimensional
         * terrain that remembers the consumed ground and chosen store type.
         *
         * @param thrower the actor breaking the bottle
         * @param target  the location that will transform
         * @return a description of the event
         */
        public String shatterAt(Actor thrower, Location target) {
            DimensionalSite existingDimensionalSite = target.getGroundAs(DimensionalSite.class);
            if (existingDimensionalSite != null) {
                return thrower + " finds that " + target + " is already warped by a dimensional rift.";
            }

            DimensionalStoreType chosenType = pickStoreType();
            DimensionalGround dimensionalGround = new DimensionalGround(
                    target.getGround(),
                    chosenType,
                    lifecyclePlans.getOrDefault(chosenType, StoreLifecycle.NONE)
            );

            target.setGround(dimensionalGround);

            return thrower + " shatters " + this + " at " + target + ", beckoning the "
                    + chosenType.getDescription() + " store.";
        }

        private DimensionalStoreType pickStoreType() {
            int choice = random.nextInt(storeOptions.size());
            return storeOptions.get(choice);
        }

        /**
         * Provides the effective throw radius of the bottle.
         *
         * @return the throw radius
         */
        public int getThrowRadius() {
            return throwRadius;
        }

        /**
         * Exposes a defensive copy of the store plans for integration or testing.
         *
         * @return the lifecycle plans keyed by store type
         */
        public Map<DimensionalStoreType, StoreLifecycle> storePlans() {
            return Map.copyOf(lifecyclePlans);
        }
    }