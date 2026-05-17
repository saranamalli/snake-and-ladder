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
        Point start = Utils.generatePoint(boardWidth, boardHeight);
        Point end = Utils.generatePoint(start.getX(), start.getY(), boardWidth, boardHeight);
        return new Snake(start, end);
    }
}
