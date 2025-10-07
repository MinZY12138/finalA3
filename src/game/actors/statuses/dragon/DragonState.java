package game.actors.statuses.dragon;

public interface DragonState {
    String getStateName();

    DragonState getNextState();

}
