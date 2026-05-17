package elements;


import java.awt.Point;

public class Snake implements SnakeLadderGameElement{
    private final Point tail, head; 

    public Point getTail() {
        return tail;
    }

    public Point getHead() {
        return head;
    }

    public Snake(Point start, Point end) {
        this.tail = new Point(start);
        this.head = new Point(end);
    }

    public Snake(Snake snake) {
        this(snake.getTail(), snake.getHead());
    }

    @Override
    public SnakeLadderGameElement clone() {
        return new Snake(this);
    }
}
