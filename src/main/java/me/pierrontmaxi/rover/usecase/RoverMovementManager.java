package me.pierrontmaxi.rover.usecase;

import me.pierrontmaxi.rover.domain.Plateau;
import me.pierrontmaxi.rover.domain.Rover;

public class RoverMovementManager {
    private final Plateau plateau;

    public RoverMovementManager(Plateau plateau) {
        this.plateau = plateau;
    }

    public void moveRovers() {
        for (Rover rover : plateau.getRovers()) {
            rover.processMoves(plateau);
        }
    }
}
