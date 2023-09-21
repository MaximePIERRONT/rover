package me.pierrontmaxi.rover.domain;

public enum CardinalCompassPoint {
    NORTH("N"),
    EAST("E"),
    SOUTH("S"),
    WEST("W");

    private final String value;

    CardinalCompassPoint(String value) {
        this.value = value;
    }

    public static CardinalCompassPoint fromValue(String value) {
        for (CardinalCompassPoint cardinalCompassPoint : CardinalCompassPoint.values()) {
            if (cardinalCompassPoint.value.equals(value)) {
                return cardinalCompassPoint;
            }
        }
        throw new IllegalArgumentException("Unknown cardinal compass point: " + value);
    }

    public String getValue() {
        return value;
    }
}
