package dice.impl;

import java.util.Scanner;

import dice.DiceRollSnakeLadderStrategy;

public class UserInputDiceRollSnakeLadderStrategy implements DiceRollSnakeLadderStrategy{

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
