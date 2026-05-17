package players;

import dice.Move;

public interface Player {
    Move generateMove();

    void notifyMoveMade(Move move);

    String getName();
}
