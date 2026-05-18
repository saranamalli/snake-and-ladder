package game;

import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import dice.Move;
import elements.Ladder;
import elements.Snake;
import elements.SnakeLadderGameElement;
import exceptions.InvalidGameStateForActionException;
import exceptions.validation.InvalidBoardSizeException;
import game.state.GameStates;
import game.stats.GameStats;
import game.stats.SnakeLadderGameStats;
import players.Player;
import turntracker.TurnTracker;
import utils.Utils;

public class SnakeLadderGame implements Game {
    private final int snakesCount, laddersCount, boardWidth, boardHeight;
    private final List<Player> players;
    private GameStates gameState;
    private TurnTracker turnTracker;
    private Integer playerWithTurn;
    Map<Player, Point> playerLocations;
    List<SnakeLadderGameElement> elements;

    private SnakeLadderGame(Builder builder) {
        this.gameState = builder.gameState;
        this.snakesCount = builder.snakes;
        this.laddersCount = builder.ladders;
        this.boardWidth = builder.boardWidth;
        this.boardHeight = builder.boardHeight;
        this.players = builder.players;
    }

    @Override
    public void initialize() {
        this.gameState.getStateWorker().initialize(this);
    }

    @Override
    public void addPlayer(Player player) {
        this.gameState.getStateWorker().addPlayer(player, this);
    }

    @Override
    public void removePlayer(Player player) {
        this.gameState.getStateWorker().removePlayer(player, this);
    }

    @Override
    public void startGame() {
        this.gameState.getStateWorker().startGame(this);
    }

    @Override
    public void terminateGame() {
        this.gameState.getStateWorker().terminateGame(this);
    }

    @Override
    public void playerQuitGame(Player quittingPlayer) {
        this.gameState.getStateWorker().playerQuitGame(quittingPlayer, this);
    }

    @Override
    public Player getPlayerWithTurn() {
        return players.get(playerWithTurn);
    }

    @Override
    public boolean isMoveValid(Move obj) {
        return this.gameState.getStateWorker().isMoveValid(obj, this);
    }

    @Override
    public void makeMove(Move move) {
        this.gameState.getStateWorker().makeMove(move, this);
    }

    @Override
    public boolean isADraw() {
        return false;
    }

    @Override
    public GameStats getWinningPlayerStats() {
        if (gameState.equals(GameStates.ENDED))
            return new SnakeLadderGameStats(players.get(playerWithTurn));
        else
            throw new InvalidGameStateForActionException("Game in progress: winner hasn't been decided yet");
    }

    @Override
    public GameStates getGameState() {
        return gameState;
    }

    public static class Builder {
        private int snakes, ladders, boardWidth, boardHeight;
        private List<Player> players;
        private GameStates gameState;

        public Builder() {
        }

        public Builder snakes(int snakes) {
            this.snakes = snakes;
            return this;
        }

        public Builder ladders(int ladders) {
            this.ladders = ladders;
            return this;
        }

        public Builder boardWidth(int boardWidth) {
            this.boardWidth = boardWidth;
            return this;
        }

        public Builder boardHeight(int boardHeight) {
            this.boardHeight = boardHeight;
            return this;
        }

        public Builder players(List<Player> players) {
            this.players = players;
            return this;
        }

        public Builder gameState(GameStates gameState) {
            this.gameState = gameState;
            return this;
        }

        public SnakeLadderGame build() {
            if (boardWidth < 3 || boardHeight < 3) {
                throw new InvalidBoardSizeException("Board size should be atleast 3x3");
            }
            return new SnakeLadderGame(this);
        }
    }

    @Override
    public void printBoardState() {
        StringBuffer[][] result = new StringBuffer[boardHeight][boardWidth];
        String baseString = " ()";
        for(StringBuffer[] temp: result) {
            Arrays.fill(temp,new StringBuffer(baseString));  // we will fill with 
        }
        
        int maxLengthOfCellString = 3;
        for(int i=0; i<elements.size(); i++) {
            SnakeLadderGameElement elem = elements.get(i);
            StringBuffer boardCellTemp;
            int bracketIndexTemp;
            if(elem instanceof Snake snake) {
                boardCellTemp = result[(int) snake.getHead().getY()][(int) snake.getHead().getX()];
                bracketIndexTemp = boardCellTemp.toString().lastIndexOf(" (");
                result[(int) snake.getHead().getY()][(int) snake.getHead().getX()] = new StringBuffer(boardCellTemp).insert(bracketIndexTemp, " 🐍 "+i);
                boardCellTemp = result[(int) snake.getTail().getY()][(int) snake.getTail().getX()];
                bracketIndexTemp = boardCellTemp.toString().lastIndexOf(" (");
                result[(int) snake.getTail().getY()][(int) snake.getTail().getX()] = new StringBuffer(boardCellTemp).insert(bracketIndexTemp, " 🤕 "+i);
                maxLengthOfCellString = Math.max(maxLengthOfCellString, result[(int) snake.getTail().getY()][(int) snake.getTail().getX()].length());
            } else if(elem instanceof Ladder ladder) {
                boardCellTemp = result[(int) ladder.getStart().getY()][(int) ladder.getStart().getX()] ;
                bracketIndexTemp = boardCellTemp.toString().lastIndexOf(" (");
                result[(int) ladder.getStart().getY()][(int) ladder.getStart().getX()] = new StringBuffer(boardCellTemp).insert(bracketIndexTemp, " 🪜 "+i);
                boardCellTemp = result[(int) ladder.getEnd().getY()][(int) ladder.getEnd().getX()];
                bracketIndexTemp = boardCellTemp.toString().lastIndexOf(" (");
                result[(int) ladder.getEnd().getY()][(int) ladder.getEnd().getX()] = new StringBuffer(boardCellTemp).insert(bracketIndexTemp, " 😃 "+i);
                maxLengthOfCellString = Math.max(maxLengthOfCellString, result[(int) ladder.getEnd().getY()][(int) ladder.getEnd().getX()].length());
            }
        }
        
        Player currPlayer;
        Point cuurPlayerLocation;
        for(Entry<Player, Point> playerLocation: playerLocations.entrySet()) {
            currPlayer = playerLocation.getKey();
            cuurPlayerLocation = playerLocation.getValue();
            StringBuffer boardCell = result[(int) cuurPlayerLocation.getY()][(int) cuurPlayerLocation.getX()];
            int bracketIndex = boardCell.toString().lastIndexOf("(")+1;
            String nameToInsert = currPlayer.getSymbol()
             + ", ";
            result[(int) cuurPlayerLocation.getY()][(int) cuurPlayerLocation.getX()] = new StringBuffer(boardCell).insert(bracketIndex, nameToInsert);
            System.out.println(currPlayer.getName()+" Location: X: "+ cuurPlayerLocation.getX()+" Y: "+cuurPlayerLocation.getY());
            maxLengthOfCellString = Math.max(maxLengthOfCellString, result[(int) cuurPlayerLocation.getY()][(int) cuurPlayerLocation.getX()].length());
        }

        // Print Board
        System.out.println("=".repeat(maxLengthOfCellString+1)+"Board Situation"+"=".repeat(maxLengthOfCellString+1));
        String hyphenString = "-".repeat((maxLengthOfCellString+3)*boardWidth);
        System.out.println(hyphenString);
        for(int i=boardHeight-1; i>=0; i--) {
            System.out.print("|");                
            for(int j=0; j<=boardWidth-1; j++) {  
                if(baseString.equals(result[i][j].toString())) {
                    System.out.print(String.format("%"+maxLengthOfCellString+"s", " "));
                } else if(maxLengthOfCellString != result[i][j].length()) {
                    System.out.print(result[i][j]+String.format("%"+(maxLengthOfCellString-result[i][j].length())+"s", " "));
                } else {
                    System.out.print(result[i][j].toString());
                }
                
                System.out.print(" | ");
            }
            System.out.println();
            System.out.println(hyphenString);
        }
    }

    public int getSnakesCount() {
        return snakesCount;
    }

    public int getLaddersCount() {
        return laddersCount;
    }

    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setGameState(GameStates gameState) {
        this.gameState = gameState;
    }

    public TurnTracker getTurnTracker() {
        return turnTracker;
    }

    public void setTurnTracker(TurnTracker turnTracker) {
        this.turnTracker = turnTracker;
    }

    public void setPlayerWithTurn(Integer playerWithTurn) {
        this.playerWithTurn = playerWithTurn;
    }

    public Map<Player, Point> getPlayerLocations() {
        return playerLocations;
    }

    public void setPlayerLocations(Map<Player, Point> playerLocations) {
        this.playerLocations = playerLocations;
    }

    public List<SnakeLadderGameElement> getElements() {
        return elements;
    }

    public void setElements(List<SnakeLadderGameElement> elements) {
        this.elements = Utils.deepClone(elements);
    }
}


// Publisher Subscriber
// Observer => Events