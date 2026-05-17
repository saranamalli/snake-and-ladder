package game.state;

import dice.Move;
import game.SnakeLadderGame;
import game.stats.GameStats;
import players.Player;

public interface State {
    void initialize(SnakeLadderGame game);

    void addPlayer(Player player, SnakeLadderGame game);

    void removePlayer(Player player, SnakeLadderGame game);

    void startGame(SnakeLadderGame game);

    void terminateGame(SnakeLadderGame game);

    void playerQuitGame(Player quittingPlayer, SnakeLadderGame game);

    boolean isMoveValid(Move move, SnakeLadderGame game);

    void makeMove(Move move, SnakeLadderGame game);

    GameStats getWinningPlayerStats();
}
