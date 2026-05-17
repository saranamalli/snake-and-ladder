package dice.impl;

import dice.Move;
import exceptions.InvalidMoveException;

public class SnakeLadderMove extends Move {
    private final int roll;

    public SnakeLadderMove(int roll) {
        if(roll < 0 || roll > 6) {
            throw new InvalidMoveException("Dice roll can only be from 0 to 6.");
        }
        this.roll = roll;
    }

    public int getRoll() {
        return roll;
    }

    @Override
    public String toString() {
        return "SnakeLadderMove(" + roll + ")";
    }
}
