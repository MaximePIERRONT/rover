package me.pierrontmaxi.rover.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardinalCompassPointTest {
    @Test
    public void testCardinalCompassPointFromValueShouldReturnNorth() {
        assertEquals(CardinalCompassPoint.NORTH, CardinalCompassPoint.fromValue("N"));
    }

    @Test
    public void testCardinalCompassPointFromValueShouldReturnEast() {
        assertEquals(CardinalCompassPoint.EAST, CardinalCompassPoint.fromValue("E"));
    }

    @Test
    public void testCardinalCompassPointFromValueShouldReturnSouth() {
        assertEquals(CardinalCompassPoint.SOUTH, CardinalCompassPoint.fromValue("S"));
    }

    @Test
    public void testCardinalCompassPointFromValueShouldReturnWest() {
        assertEquals(CardinalCompassPoint.WEST, CardinalCompassPoint.fromValue("W"));
    }

    @Test
    public void testCardinalCompassPointFromValueShouldThrowIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> CardinalCompassPoint.fromValue("Z"));
    }

    @Test
    public void testCardinalCompassPointGetValueShouldReturnN() {
        assertEquals("N", CardinalCompassPoint.NORTH.getValue());
    }
}