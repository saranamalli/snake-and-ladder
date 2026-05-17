package players;

import dice.DiceRollSnakeLadderStrategy;
import dice.Move;
import dice.impl.SnakeLadderMove;

public class HumanPlayer implements Player {
    private User user;
    private String symbol;
    private final DiceRollSnakeLadderStrategy diceRollStrategy; 

    public HumanPlayer(User user, String symbol, DiceRollSnakeLadderStrategy strategy) {
        this.user = user;
        this.symbol = symbol;
        this.diceRollStrategy = strategy;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return this.getUser().getName();
    }

    @Override
    public Move generateMove() {
        int roll = diceRollStrategy.roll(user.getName());
        System.out.println(this.toString() + " and I rolled a " + roll);
        return new SnakeLadderMove(roll);
    }

    @Override
    public void notifyMoveMade(Move move) {
        System.out.println("Hi, I'm " + user.getName() + " and I observed the move " + move);
    }

    @Override
    public String toString() {
        return "hi, I'm " + user.getName();
    }
}
