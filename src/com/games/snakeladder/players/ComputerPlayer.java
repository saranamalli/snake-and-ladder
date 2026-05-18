package players;

import dice.DiceRollSnakeLadderStrategy;
import dice.Move;
import dice.impl.SnakeLadderMove;

public class ComputerPlayer implements Player {
    private final String name, symbol;
    private final DiceRollSnakeLadderStrategy strategy;

    public ComputerPlayer(String name, String symbol, DiceRollSnakeLadderStrategy strategy) {
        this.name = name;
        this.symbol = symbol;
        this.strategy = strategy;
    }

    @Override
    public Move generateMove() {
        int roll = strategy.roll(name);
        System.out.println(this.toString() + " and I rolled a " + roll);
        return new SnakeLadderMove(roll);
    }

    @Override
    public void notifyMoveMade(Move move) {

    }
    @Override
    public String toString() {
        return "hi, I'm Bot " + name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getSymbol() {
        String sym = this.symbol != null && !this.symbol.isBlank() ? this.symbol : getName().substring(0, Math.min(2, getName().length()));
        return sym;
    }

    public DiceRollSnakeLadderStrategy getStrategy() {
        return strategy;
    }
}
