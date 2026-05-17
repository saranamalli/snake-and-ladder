package game;

import dice.Move;
import game.state.GameStates;
import game.stats.GameStats;
import players.Player;

public interface Game {
    void initialize();

    void startGame();

    void terminateGame();

    void addPlayer(Player player);

    void removePlayer(Player player);

    void playerQuitGame(Player quittingPlayer);

    default boolean isOver() {
        return this.getGameState().equals(GameStates.ENDED);
    }

    Player getPlayerWithTurn();

    boolean isMoveValid(Move move);

    void makeMove(Move move);

    boolean isADraw();

    GameStats getWinningPlayerStats();

    GameStates getGameState();

    void printBoardState();
}
