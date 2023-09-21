package me.pierrontmaxi.rover.domain;

public enum RoverMove {
    LEFT('L'),
    RIGHT('R'),
    MOVE('M');

    private final char value;

    RoverMove(char value) {
        this.value = value;
    }

    public static RoverMove fromValue(char value) {
        for (RoverMove roverMove : RoverMove.values()) {
            if (roverMove.value == value) {
                return roverMove;
            }
        }
        throw new IllegalArgumentException("Unknown rover move: " + value);
    }
}
