package turntracker;
public class RoundRobinTurnTracker implements TurnTracker {
    private final int size;
    private int turn;

    public RoundRobinTurnTracker(int size) {
        this.size = size;
        turn = 0;
    }

    @Override
    public int getNext() {
        turn = (turn + 1) % size;
        return turn;
    }
}
