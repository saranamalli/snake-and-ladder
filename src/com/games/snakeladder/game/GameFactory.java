package game;

import java.util.List;
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

    public Game createClassicSnakeLadder(List<Player> players) {
        return new SnakeLadderGame.Builder()
                .snakes(5)
                .ladders(7)
                .boardWidth(10)
                .boardHeight(10)
                .players(players)
                .build();
    }
}
