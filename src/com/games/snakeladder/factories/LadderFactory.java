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
        Point start = Utils.generatePoint(0, 0, boardWidth, boardHeight);
        Point end = null;
        do {
            end = Utils.generatePoint((int)start.getX(), (int)start.getY(), boardWidth, boardHeight);
        } while(start.equals(end));
        return new Ladder(start, end);
    }
}
