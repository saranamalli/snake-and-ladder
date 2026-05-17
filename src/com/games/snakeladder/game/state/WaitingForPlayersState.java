package game.state;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import dice.Move;
import exceptions.InvalidGamePlayerException;
import exceptions.InvalidGameStateForActionException;
import factories.LadderFactory;
import factories.SnakeFactory;
import game.SnakeLadderGame;
import game.stats.GameStats;
import players.Player;
import turntracker.RoundRobinTurnTracker;

public class WaitingForPlayersState implements State {

    public void initialize(SnakeLadderGame game) {
        System.out.println("Initializing Classic Snake & Ladder Game..");
        game.setElements(new ArrayList<>());
        game.setPlayerLocations(new HashMap<>());
        for (Player player : game.getPlayers()) {
            game.getPlayerLocations().put(player, new Point(0, 0));
        }
        for (int s = 0; s < game.getSnakesCount(); s++) {
            game.getElements().add(SnakeFactory.getInstance().getRandomSnake(game.getBoardWidth(), game.getBoardHeight()));
        }
        for (int s = 0; s < game.getLaddersCount(); s++) {
            game.getElements().add(LadderFactory.getInstance().getRandomLadder(game.getBoardWidth(), game.getBoardHeight()));
        }
        game.setPlayerWithTurn(0);
        game.setTurnTracker(new RoundRobinTurnTracker(game.getPlayers().size()));
        
        System.out.println("Initialized!");
    }

    public void addPlayer(Player player, SnakeLadderGame game) {
        game.getPlayers().add(player);
        game.getPlayerLocations().put(player, new Point(0, 0));
        System.out.println("Player " + player.toString() + " added!");
    }

    public void removePlayer(Player player, SnakeLadderGame game) {
        if (!game.getPlayerLocations().containsKey(player)) {
            throw new InvalidGamePlayerException("This player was not registered for this game");
        }
        game.getPlayers().remove(player);
        game.getPlayerLocations().remove(player);
        System.out.println("Player " + player.toString() + " removed.");
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
