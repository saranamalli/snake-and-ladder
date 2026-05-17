package factories;

import java.awt.Point;

import elements.Ladder;
import elements.SnakeLadderGameElement;
import utils.Utils;

public class LadderFactory {
    private static LadderFactory INSTANCE;

    private LadderFactory() {
        if(INSTANCE != null)
            throw new RuntimeException("Please use getInstance to get object.");
    }

    public static LadderFactory getInstance() {
        if (INSTANCE == null) {
            synchronized (LadderFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new LadderFactory();
                }
            }
        }
        return INSTANCE;
    }

    // What is the need of Singleton, a static method would suffice.
    public SnakeLadderGameElement getRandomLadder(int boardWidth, int boardHeight) {
        Point start = Utils.generatePoint(boardWidth, boardHeight);
        Point end = Utils.generatePoint(start.getX(), start.getY(), boardWidth, boardHeight);
        return new Ladder(start, end);
    }
}
