package me.pierrontmaxi.rover.usecase;

import me.pierrontmaxi.rover.usecase.MarsNasaMission;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MarsNasaMissionTest {
    public static final String EXISTING_FILE = "src/test/resources/input.txt";
    public static final String EMPTY_TXT = "src/test/resources/empty.txt";

    @Test
    public void testThrowException() {
        Exception exception = assertThrows(RuntimeException.class, () -> new MarsNasaMission(EMPTY_TXT));
        assertTrue(exception.getMessage().contains("Your file is empty"));
    }

    @Test
    public void testSuccessfulRoverInitialization() {
        MarsNasaMission mission = new MarsNasaMission(EXISTING_FILE);
        assertNotNull(mission.getPlateau());
        assertEquals(2, mission.getPlateau().getRovers().size());
    }


}

