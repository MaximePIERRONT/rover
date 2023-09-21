package me.pierrontmaxi.rover;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    public static final String INPUT_TXT = "src/test/resources/input.txt";
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    private final String lineSeparator = System.lineSeparator();

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testMainShouldPrintUsageIfNoArgument() {
        Main.main(new String[]{});
        assertEquals("Usage java -jar rover.jar <filePath>" + lineSeparator, outContent.toString());
    }

    @Test
    public void testMainShouldPrintUsageIfMoreThanOneArgument() {
        Main.main(new String[]{"arg1", "arg2"});
        assertEquals("Usage java -jar rover.jar <filePath>" + lineSeparator, outContent.toString());
    }

    @Test
    public void testMainShouldSuccess() {
        Main.main(new String[]{INPUT_TXT});
        assertEquals("1 3 N" + lineSeparator + "5 1 E" + lineSeparator + lineSeparator, outContent.toString());
    }
}