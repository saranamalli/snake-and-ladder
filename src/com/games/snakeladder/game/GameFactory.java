package game;

import java.util.List;
import game.state.GameStates;
import players.Player;

public class GameFactory {
    private static volatile GameFactory INSTANCE;

    private GameFactory() {
        // prevent reflection attack
        if(INSTANCE != null) 
            throw new RuntimeException("Not allowed to use constructor, use getInstance()");
    }

    public static GameFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (GameFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new GameFactory();
                }
            }
        }
        return INSTANCE;
    }

    public Game createEasySnakeLadderGame(List<Player> players) {
        return new SnakeLadderGame.Builder()
                .snakes(3)
                .ladders(10)
                .boardWidth(10)
                .boardHeight(10)
                .players(players)
                .gameState(GameStates.WAITING_FOR_PLAYERS)
                .build();
    }

    public Game createClassicSnakeLadder(List<Player> players) {
        return new SnakeLadderGame.Builder()
                .snakes(5)
                .ladders(7)
                .boardWidth(10)
                .boardHeight(10)
                .players(players)
                .gameState(GameStates.WAITING_FOR_PLAYERS)
                .build();
    }
    
    public Game createHardLevelSnakeLadder(List<Player> players) {
        return new SnakeLadderGame.Builder()
        .snakes(10)
        .ladders(5)
        .boardWidth(15)
        .boardHeight(15)
        .players(players)
        .gameState(GameStates.WAITING_FOR_PLAYERS)
        .build();
    }
    
    public Game createQuickSnakeLadder(List<Player> players) {
        return new SnakeLadderGame.Builder()
        .snakes(3)
        .ladders(5)
        .boardWidth(5)
        .boardHeight(5)
        .players(players)
        .gameState(GameStates.WAITING_FOR_PLAYERS)
        .build();
    }
    
    public Game createQuickHardLevelSnakeLadder(List<Player> players) {
        return new SnakeLadderGame.Builder()
                .snakes(5)
                .ladders(3)
                .boardWidth(5)
                .boardHeight(5)
                .players(players)
                .gameState(GameStates.WAITING_FOR_PLAYERS)
                .build();
    }
}
