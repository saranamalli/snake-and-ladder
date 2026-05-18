package factories;

import java.awt.*;

import elements.Snake;
import elements.SnakeLadderGameElement;
import utils.Utils;

public class SnakeFactory {
    

    private SnakeFactory() {
    }

    private static class SingletonHolder {
        static final SnakeFactory INSTANCE = new SnakeFactory();
    }

    public static SnakeFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    // What is the need of Singleton, a static method would suffice.
    public SnakeLadderGameElement getRandomSnake(int boardWidth, int boardHeight) {
        Point start = Utils.generatePoint(0, 0, boardWidth, boardHeight);
        Point end = null;
        do {
            end = Utils.generatePoint((int)start.getX(), (int)start.getY(), boardWidth, boardHeight);
        } while(start.equals(end));
        return new Snake(start, end);
    }
}
