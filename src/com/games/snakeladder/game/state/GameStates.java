package game.state;

public enum GameStates {
    WAITING_FOR_PLAYERS(new WaitingForPlayersState()),
    STARTED(new GameStartedState()),
    ENDED(new GameEndedState());

    private final State state;

    GameStates(State state) {
        this.state = state;
    }

    public State getStateWorker() {
        return this.state;
    }
}
