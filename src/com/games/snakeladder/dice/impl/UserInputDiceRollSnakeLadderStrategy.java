package dice.impl;

import java.util.Scanner;

import dice.DiceRollSnakeLadderStrategy;

public class UserInputDiceRollSnakeLadderStrategy implements DiceRollSnakeLadderStrategy{

    private static UserInputDiceRollSnakeLadderStrategy singleton_instance;

    private UserInputDiceRollSnakeLadderStrategy() {
        if(singleton_instance != null) 
            throw new RuntimeException("Use getInstance method to obtain the instance.");
    }

    public static UserInputDiceRollSnakeLadderStrategy getInstance() {
        if(singleton_instance == null) {
            synchronized(UserInputDiceRollSnakeLadderStrategy.class) {
                if(singleton_instance == null) {
                    singleton_instance = new UserInputDiceRollSnakeLadderStrategy();
                }
            }
        }
        return singleton_instance;
    }

    @Override
    public int roll(String username) {
        int userInp = 0;
        try(Scanner sc = new Scanner(System.in)) {
            System.out.println(username + ", Press any number from 1 to 6:");
            userInp = sc.nextInt();
        }
        if(userInp <= 0 || userInp > 6) {
            System.out.println("Please enter valid input: ");
            userInp = roll(username);
        }
        return userInp;
    }

}
