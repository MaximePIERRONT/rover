package me.pierrontmaxi.rover.usecase;

import me.pierrontmaxi.rover.domain.Plateau;
import me.pierrontmaxi.rover.domain.Rover;
import me.pierrontmaxi.rover.usecase.exception.InvalidFileFormatException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MarsMissionParserTest {

    public static final String EMPTY_TXT = "src/test/resources/empty.txt";
    File tempFile;
    MarsMissionParser parser;

    @BeforeEach
    void setUp() throws IOException {
        tempFile = File.createTempFile("marsMission", ".txt");
    }

    @AfterEach
    void tearDown() throws IOException {
        if (parser != null) {
            parser.closeBufferedReader();
        }
        tempFile.delete();
    }

    @Test
    void testParsePlateauFromStringValid() {
        Plateau expectedPlateau = MarsMissionParser.parsePlateauFromString("5 5");
        Plateau actualPlateau = MarsMissionParser.parsePlateauFromString("5 5");
        assertEquals(expectedPlateau, actualPlateau);
    }

    @Test
    void testParsePlateauFromStringInvalid() {
        assertThrows(RuntimeException.class, () -> MarsMissionParser.parsePlateauFromString("5 Z"));
    }

    @Test
    public void testEmptyFileException() {
        Exception exception = assertThrows(RuntimeException.class, () -> new MarsNasaMission(EMPTY_TXT));
        assertEquals("Your file is empty", exception.getMessage());
    }

    @Test
    void testParseRoverValid() throws Exception {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("5 5\n");
            writer.write("1 2 N\n");
            writer.write("LMLMLMLMM\n");
            writer.flush();
        }

        parser = new MarsMissionParser(tempFile.getAbsolutePath());
        Plateau plateau = parser.parse();
        assertEquals(1, plateau.getRovers().size());
        Rover rover = plateau.getRovers().get(0);
        assertEquals("1 2 N", rover.getCurrentPosition());
    }

    @Test
    void testParseRoverInvalidCoordinates() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write("5 5\n");
            writer.write("1 Z N\n");
            writer.write("LMLMLMLMM\n");
            writer.flush();
        }

        parser = new MarsMissionParser(tempFile.getAbsolutePath());
        assertThrows(InvalidFileFormatException.class, parser::parse);
    }

    @Test
    public void testIncorrectMoves() {
        Exception exception = assertThrows(InvalidFileFormatException.class, () -> {
            MarsMissionParser.checkMoveAreCorrect("LMZ");
        });
        assertTrue(exception.getMessage().contains("Rover moves line"));
    }

    @Test
    public void testMissingInstructions() {
        Exception exception = assertThrows(InvalidFileFormatException.class, () -> {
            MarsMissionParser.checkInstructionsNotNull(null);
        });
        assertTrue(exception.getMessage().contains("Rover instructions is missing line"));
    }
}
