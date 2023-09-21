package me.pierrontmaxi.rover.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoverMoveTest {
    @Test
    void testRoverMoveFromValueLShouldReturnLeft() {
        assertEquals(RoverMove.LEFT, RoverMove.fromValue('L'));
    }

    @Test
    void testRoverMoveFromValueRShouldReturnRight() {
        assertEquals(RoverMove.RIGHT, RoverMove.fromValue('R'));
    }

    @Test
    void testRoverMoveFromValueMShouldReturnMove() {
        assertEquals(RoverMove.MOVE, RoverMove.fromValue('M'));
    }

    @Test
    void testRoverMoveFromValueUnknownShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> RoverMove.fromValue('X'));
    }

}