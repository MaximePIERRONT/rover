package me.pierrontmaxi.rover.usecase;

import me.pierrontmaxi.rover.domain.Plateau;

public class MarsNasaMission {
    final Plateau plateau;

    public MarsNasaMission(String fileName) {
        try {
            MarsMissionParser marsMissionParser = new MarsMissionParser(fileName);
            this.plateau = marsMissionParser.parse();
            RoverMovementManager roverMovementManager = new RoverMovementManager(this.plateau);
            roverMovementManager.moveRovers();
            marsMissionParser.closeBufferedReader();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Plateau getPlateau() {
        return this.plateau;
    }

}
