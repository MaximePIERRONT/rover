package me.pierrontmaxi.rover.domain;

import me.pierrontmaxi.rover.domain.CardinalCompassPoint;
import me.pierrontmaxi.rover.domain.Plateau;
import me.pierrontmaxi.rover.domain.Rover;
import me.pierrontmaxi.rover.usecase.MarsNasaMission;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlateauTest {

    public static final String EXISTING_FILE = "src/test/resources/input.txt";
    private Plateau plateau;

    @BeforeEach
    void setUp() {
        plateau = new Plateau(5, 5);
    }

    @Test
    void testEqualsPlateauDimensions() {
        assertEquals(new Plateau(5, 5), plateau);
    }

    @Test
    void testSetAndGetRovers() {
        Rover rover1 = new Rover(1, 2, CardinalCompassPoint.WEST, "LMLMLMLMM");
        Rover rover2 = new Rover(3, 3, CardinalCompassPoint.NORTH, "MMRMMRMRRM");
        List<Rover> rovers = List.of(
                rover1,
                rover2
        );

        plateau.addRover(rover1);
        plateau.addRover(rover2);

        assertEquals(rovers, plateau.getRovers());
    }

    @Test
    public void testGetRoversFinalCoordinates() {
        MarsNasaMission mission = new MarsNasaMission(EXISTING_FILE);
        String finalCoordinates = mission.getPlateau().getRoversFinalCoordinates().trim();
        assertEquals("1 3 N" + System.lineSeparator() + "5 1 E", finalCoordinates);
    }
}
