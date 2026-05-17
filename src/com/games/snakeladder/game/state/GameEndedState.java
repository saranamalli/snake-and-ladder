package game.state;

import dice.Move;
import exceptions.InvalidGameStateForActionException;
import game.SnakeLadderGame;
import game.stats.GameStats;
import players.Player;

public class GameEndedState implements State {
    public void initialize(SnakeLadderGame game) {
        throw new InvalidGameStateForActionException("Game has Ended.");
    }

    public void addPlayer(Player player, SnakeLadderGame game) {
        throw new InvalidGameStateForActionException("Can't add player mid game");
    }

    public void removePlayer(Player player, SnakeLadderGame game) {
        throw new InvalidGameStateForActionException("Game has already ended.");
    }

    public void startGame(SnakeLadderGame game) {
        game.setGameState(GameStates.STARTED);
        System.out.println("Game has started!!");
    }

    public void terminateGame(SnakeLadderGame game) {
        throw new InvalidGameStateForActionException("Can't terminate game that hasn't started.");
    }

    public void playerQuitGame(Player quittingPlayer, SnakeLadderGame game) {
        removePlayer(quittingPlayer, game);
    }

    public boolean isMoveValid(Move move, SnakeLadderGame game) {
        throw new InvalidGameStateForActionException("Game hasn't started yet.");
    }

    public void makeMove(Move move, SnakeLadderGame game) {
        throw new InvalidGameStateForActionException("Game hasn't started yet.");
    }

    public GameStats getWinningPlayerStats() {
        throw new InvalidGameStateForActionException("Game hasn't started yet.");
    }
}
