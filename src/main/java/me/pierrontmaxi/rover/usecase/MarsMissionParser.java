package me.pierrontmaxi.rover.usecase;

import me.pierrontmaxi.rover.usecase.exception.InvalidFileFormatException;
import me.pierrontmaxi.rover.domain.CardinalCompassPoint;
import me.pierrontmaxi.rover.domain.Plateau;
import me.pierrontmaxi.rover.domain.Rover;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MarsMissionParser {

    private final BufferedReader bufferedReader;

    public MarsMissionParser(String fileName) throws FileNotFoundException {
        this.bufferedReader = new BufferedReader(new FileReader(fileName));
    }

    private static String manageRoverInstructionsLine(BufferedReader bufferedReader) throws IOException {
        String instructions = bufferedReader.readLine();
        checkInstructionsNotNull(instructions);
        checkMoveAreCorrect(instructions);
        return instructions;
    }

    public static void checkMoveAreCorrect(String moves) {
        for (int i = 0; i < moves.length(); i++) {
            if (!"LRM".contains(String.valueOf(moves.charAt(i)))) {
                throw new InvalidFileFormatException("Rover moves line is not correct: " + moves);
            }
        }
    }

    public static void checkInstructionsNotNull(String instructions) {
        if (instructions == null)
            throw new InvalidFileFormatException("Rover instructions is missing line: " + instructions);
    }

    public Plateau parse() throws Exception {
        String firstLine = bufferedReader.readLine();
        checkIfTheFileIsEmpty(firstLine);
        Plateau plateau = parsePlateauFromString(firstLine);
        parseRoversAndOnPlateau(plateau);
        return plateau;
    }

    public static Plateau parsePlateauFromString(String firstLine) {
        try {
            String[] plateauCoordinates = firstLine.split(" ");
            int x = Integer.parseInt(plateauCoordinates[0]);
            int y = Integer.parseInt(plateauCoordinates[1]);
            return new Plateau(x, y);
        } catch (Exception e) {
            throw new InvalidFileFormatException("First line of file is not correct: " + firstLine);
        }
    }

    private static void checkIfTheFileIsEmpty(String firstLine) {
        if (firstLine == null) {
            throw new InvalidFileFormatException("Your file is empty");
        }
    }

    private void parseRoversAndOnPlateau(Plateau plateau) throws IOException {
        String coordinatesLine;
        while ((coordinatesLine = this.bufferedReader.readLine()) != null) {
            Rover rover = parseRover(coordinatesLine, this.bufferedReader);
            plateau.addRover(rover);
        }
    }

    private static Rover parseRover(String coordinatesLine, BufferedReader bufferedReader) {
        try {
            String[] coordinates = coordinatesLine.split(" ");
            int x = Integer.parseInt(coordinates[0]);
            int y = Integer.parseInt(coordinates[1]);
            CardinalCompassPoint direction = CardinalCompassPoint.fromValue(coordinates[2]);
            String instructions = manageRoverInstructionsLine(bufferedReader);
            return new Rover(x, y, direction, instructions);
        } catch (Exception e) {
            throw new InvalidFileFormatException("Rover coordinates line is not correct: " + coordinatesLine);
        }
    }

    public void closeBufferedReader() throws IOException {
        bufferedReader.close();
    }
}
