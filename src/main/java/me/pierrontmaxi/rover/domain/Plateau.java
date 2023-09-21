package me.pierrontmaxi.rover.domain;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.lineSeparator;

public class Plateau {
    private final int x;
    private final int y;

    private final List<Rover> rovers = new ArrayList<>();

    public Plateau(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void addRover(Rover rover) {
        rovers.add(rover);
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    public String getRoversFinalCoordinates() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Rover rover : getRovers()) {
            stringBuilder.append(rover.getCurrentPosition())
                    .append(lineSeparator());
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plateau plateau)) return false;
        return x == plateau.x && y == plateau.y;
    }
}
