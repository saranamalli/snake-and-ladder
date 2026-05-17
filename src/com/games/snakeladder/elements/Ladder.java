package elements;


import java.awt.Point;

public class Ladder implements SnakeLadderGameElement{
    private final Point start, end;  // Ladder begins at start and takes us to its end.

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public Ladder(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);
    }
    
    @Override
    public SnakeLadderGameElement clone() {
        return new Ladder(this.start, this.end);
    }
}
