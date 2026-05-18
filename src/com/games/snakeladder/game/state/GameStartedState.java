package game.state;

import java.awt.Point;

import dice.Move;
import dice.impl.SnakeLadderMove;
import elements.Ladder;
import elements.Snake;
import elements.SnakeLadderGameElement;
import exceptions.InvalidGameStateForActionException;
import game.SnakeLadderGame;
import game.stats.GameStats;
import players.Player;

public class GameStartedState implements State {

    public void initialize(SnakeLadderGame game) {
        throw new InvalidGameStateForActionException("Game has already Started, Terminate to start new game.");
    }

    public void addPlayer(Player player, SnakeLadderGame game) {
        throw new InvalidGameStateForActionException("Can't add player mid game");
    }

    public void removePlayer(Player player, SnakeLadderGame game) {
        throw new InvalidGameStateForActionException("Can't remove player mid game");
    }

    public void startGame(SnakeLadderGame game) {
        throw new InvalidGameStateForActionException("Game has already started.");
    }

    public void terminateGame(SnakeLadderGame game) {
        System.out.println("Received request to terminate game!");
        game.setGameState(GameStates.ENDED);
    }

    public void playerQuitGame(Player quittingPlayer, SnakeLadderGame game) {
        System.out.println(quittingPlayer.getName() + " has left the game.");
        game.getPlayers().remove(quittingPlayer);
        game.getPlayerLocations().remove(quittingPlayer);
        System.out.println("Player " + quittingPlayer.toString() + " removed!");
    }

    public boolean isMoveValid(Move obj, SnakeLadderGame game) {
        SnakeLadderMove move = (SnakeLadderMove) obj;
        Point location = game.getPlayerLocations().get(game.getPlayerWithTurn()); // current player's location
        return (location.getY() != game.getBoardHeight() - 1)
                || (location.getX() + move.getRoll() <= game.getBoardWidth());
    }

    public void makeMove(Move move, SnakeLadderGame game) {
        System.out.println("Making the move " + move.toString());
        int roll = ((SnakeLadderMove) move).getRoll();
        Player player = game.getPlayerWithTurn();
        Point location = game.getPlayerLocations().get(player);
        Point newLocation = new Point(
                (int) ((location.getX() + roll) % game.getBoardWidth()),
                (int) (location.getY() + ((location.getX() + roll) >= game.getBoardWidth() ? 1 : 0))
        );
        // notify all my players
        for (Player p : game.getPlayers()) {
            p.notifyMoveMade(move);
        }
        boolean moveValidBool = true;
        if (!isMoveValid(move, game)) {
            System.out.println("Invalid move");
            moveValidBool = false;
        }
        if (isPlayerWinning(newLocation, game)) {
            // the current player has won the game!
            game.setGameState(GameStates.ENDED);
            return;
        }
        if(moveValidBool) {
            // System.out.println("Location before checking Snakes / Ladders: "+newLocation);
            newLocation = updateLocationBySnakesOrLadders(newLocation, game);
            // System.out.println("Location after checking Snakes / Ladders: "+newLocation);
            game.getPlayerLocations().put(player, newLocation);
        }
        game.printBoardState();
        // make sure that the turn is advanced
        game.setPlayerWithTurn(game.getTurnTracker().getNext());
    }

    private boolean isPlayerWinning(Point playerLocation, SnakeLadderGame game) {
        return playerLocation.getX() == 0
                && playerLocation.getY() == game.getBoardHeight();
    }

    private Point updateLocationBySnakesOrLadders(Point location, SnakeLadderGame game) {
        Point newLocation = location;
        for(SnakeLadderGameElement element: game.getElements()) {
            if(element instanceof Snake snake) {
                if(snake.getHead().equals(location)) {
                    System.out.println("User got bit with Snake! "+snake.getHead());
                    newLocation = snake.getTail();
                }
            } else if(element instanceof Ladder ladder) {
                if(ladder.getStart().equals(location)) {
                    System.out.println("User hit ladder! " + ladder.getStart());
                    newLocation = ladder.getEnd();
                }
            }
        }
        return newLocation != location ? updateLocationBySnakesOrLadders(newLocation, game) : newLocation;
    }

    public GameStats getWinningPlayerStats() {
        throw new InvalidGameStateForActionException("Game hasn't started yet.");
    }
}
