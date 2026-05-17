package dice.impl;

import java.util.Scanner;

import dice.DiceRollSnakeLadderStrategy;

// Needs to be singletons as they are initialized by client and there is a risk of having multiple objects
public class RandomDiceRollSnakeLadderStrategy implements DiceRollSnakeLadderStrategy {

    Scanner sc = new Scanner(System.in);
    private static RandomDiceRollSnakeLadderStrategy INSTANCE;

    private RandomDiceRollSnakeLadderStrategy() {
        if(INSTANCE != null)
            throw new RuntimeException("Please use getInstance to get object.");
    }

    public RandomDiceRollSnakeLadderStrategy getInstance() {
        if(INSTANCE == null) {
            synchronized(RandomDiceRollSnakeLadderStrategy.class) {
                if(INSTANCE == null) {
                    INSTANCE = new RandomDiceRollSnakeLadderStrategy();
                }
            } 
        }
        return INSTANCE;
    }

    @Override
    public int roll(String username) {
        System.out.println(username + ", Press any key to roll a dice.");
        sc.nextLine();
        return (int) (Math.random() * 6);
    }
}
