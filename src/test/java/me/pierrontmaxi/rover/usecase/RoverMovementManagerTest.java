package me.pierrontmaxi.rover.usecase;

import me.pierrontmaxi.rover.domain.CardinalCompassPoint;
import me.pierrontmaxi.rover.domain.Plateau;
import me.pierrontmaxi.rover.domain.Rover;
import me.pierrontmaxi.rover.usecase.RoverMovementManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverMovementManagerTest {
    @Test
    void testMoveRovers() {
        Plateau plateau = new Plateau(5, 5);
        RoverMovementManager roverMovementManager = new RoverMovementManager(plateau);
        Rover rover1 = new Rover(1, 2, CardinalCompassPoint.NORTH, "LMLMLMLMM");
        Rover rover2 = new Rover(3, 3, CardinalCompassPoint.EAST, "MMRMMRMRRM");

        plateau.addRover(rover1);
        plateau.addRover(rover2);

        roverMovementManager.moveRovers();

        assertEquals("1 3 N",rover1.getCurrentPosition());
        assertEquals("5 1 E",rover2.getCurrentPosition());
    }

}