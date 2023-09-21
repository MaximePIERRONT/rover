package me.pierrontmaxi.rover.domain;

import me.pierrontmaxi.rover.domain.exception.RoverOnTheWayException;
import me.pierrontmaxi.rover.usecase.MarsMissionParser;
import me.pierrontmaxi.rover.usecase.exception.InvalidFileFormatException;
import me.pierrontmaxi.rover.domain.exception.OutOfPlateauException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoverTest {
    private Plateau plateau;
    private Rover rover;

    @BeforeEach
    public void setup() {
        plateau = new Plateau(5, 5);
    }

    @Test
    public void testValidMoves() {
        rover = new Rover(1, 2, CardinalCompassPoint.NORTH, "LMLMLMLMMRRRR");
        plateau.addRover(rover);
        rover.processMoves(plateau);
        assertEquals("1 3 N", rover.getCurrentPosition());
    }

    @Test
    public void testRoverOutOfPlateau() {
        Exception exception = assertThrows(OutOfPlateauException.class, () -> {
            rover = new Rover(5, 5, CardinalCompassPoint.NORTH, "MMMM");
            rover.processMoves(plateau);
        });
        assertTrue(exception.getMessage().contains("Rover is out of plateau"));
    }

    @Test
    public void testRoverOnTheWay() {
        Rover firstRover = new Rover(1, 2, CardinalCompassPoint.NORTH, "M");
        Rover secondRover = new Rover(1, 3, CardinalCompassPoint.SOUTH, "M");
        plateau.addRover(firstRover);
        plateau.addRover(secondRover);

        Exception exception = assertThrows(RoverOnTheWayException.class, () -> {
            firstRover.processMoves(plateau);
            secondRover.processMoves(plateau);
        });
        assertTrue(exception.getMessage().contains("Rover is on the way"));
    }
}
