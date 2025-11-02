package game.grounds.dimensional;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.LeaveStoreAction;
import game.grounds.GroundInfo;

public class ExitGround extends Ground {
    public ExitGround() {
        super(GroundInfo.EXIT.getDISPLAY_CHAR(), GroundInfo.EXIT.getNAME());
    }

    @Override
    public boolean canActorEnter(Actor actor) { return true; }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        return Action(new LeaveStoreAction());
    }
}