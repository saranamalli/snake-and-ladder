package dice.impl;

import dice.DiceRollSnakeLadderStrategy;

public class ComputerDiceRollSnakeLadderStrategy implements DiceRollSnakeLadderStrategy {

    @Override
    public int roll(String userName) {
        return (int) (Math.random() * 6);
    }
}
