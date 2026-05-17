package dice.impl;

import dice.DiceRollSnakeLadderStrategy;

public class ComputerDiceRollSnakeLadderStrategy implements DiceRollSnakeLadderStrategy {
    private static ComputerDiceRollSnakeLadderStrategy singleton_instance;

    private ComputerDiceRollSnakeLadderStrategy() {}

    public static ComputerDiceRollSnakeLadderStrategy getInstance() {
        if(singleton_instance == null) {
            synchronized(ComputerDiceRollSnakeLadderStrategy.class) {
                if(singleton_instance == null) {
                    singleton_instance = new ComputerDiceRollSnakeLadderStrategy();
                }
            }
        }
        return singleton_instance;
    }

    @Override
    public int roll(String userName) {
        return (int) (Math.random() * 6);
    }
}
