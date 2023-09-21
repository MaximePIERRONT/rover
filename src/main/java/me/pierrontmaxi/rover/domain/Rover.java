package me.pierrontmaxi.rover.domain;

import me.pierrontmaxi.rover.domain.exception.OutOfPlateauException;
import me.pierrontmaxi.rover.domain.exception.RoverOnTheWayException;

import java.util.List;

public class Rover {

    private int x;
    private int y;
    private CardinalCompassPoint direction;
    private final String instructions;

    public Rover(int x, int y, CardinalCompassPoint direction, String instructions) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.instructions = instructions;
    }

    public void processMoves(Plateau plateau) {
        for (int i = 0; i < instructions.length(); i++) {
            move(instructions.charAt(i), plateau);
        }
    }

    public void move(char moveChoice , Plateau plateau) {
        switch (RoverMove.fromValue(moveChoice)) {
            case LEFT:
                turnLeft();
                break;
            case RIGHT:
                turnRight();
                break;
            case MOVE:
                moveForward(plateau);
                break;
        }
    }

    private void moveForward(Plateau plateau) {
        switch (direction) {
            case NORTH:
                y++;
                break;
            case EAST:
                x++;
                break;
            case SOUTH:
                y--;
                break;
            case WEST:
                x--;
                break;
        }
        checkIfRoverOutOfPlateau(plateau);
        checkIfRoverIsOnTheWay(plateau);
    }

    private void checkIfRoverIsOnTheWay(Plateau plateau) {
        List<Rover> rovers = plateau.getRovers();
        for (Rover rover : rovers) {
            if (rover != this && rover.x == x && rover.y == y) {
                throw new RoverOnTheWayException("Rover is on the way");
            }
        }
    }

    private void checkIfRoverOutOfPlateau(Plateau plateau) {
        if (x < 0 || x > plateau.getX() || y < 0 || y > plateau.getY()) {
            throw new OutOfPlateauException("Rover is out of plateau");
        }
    }

    private void turnRight() {
        switch (direction){
            case NORTH:
                direction = CardinalCompassPoint.EAST;
                break;
            case EAST:
                direction = CardinalCompassPoint.SOUTH;
                break;
            case SOUTH:
                direction = CardinalCompassPoint.WEST;
                break;
            case WEST:
                direction = CardinalCompassPoint.NORTH;
                break;
        }
    }

    private void turnLeft() {
        switch (direction){
            case NORTH:
                direction = CardinalCompassPoint.WEST;
                break;
            case EAST:
                direction = CardinalCompassPoint.NORTH;
                break;
            case SOUTH:
                direction = CardinalCompassPoint.EAST;
                break;
            case WEST:
                direction = CardinalCompassPoint.SOUTH;
                break;
        }
    }

    public String getCurrentPosition() {
        return x + " " + y + " " + direction.getValue();
    }
}
