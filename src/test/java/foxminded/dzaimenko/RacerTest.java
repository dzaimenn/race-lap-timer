package foxminded.dzaimenko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RacerTest {

    @Test
    void constructorWithValidArgumentsShouldNotThrowException() {
        assertDoesNotThrow(() -> {
            new Racer("ABC", "Test Name", "Test Team");
        });
    }

    @Test
    void constructorWithNullAbbreviationShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Racer(null, "Test Name", "Test Team");
        });

        String expected = "Abbreviation cannot be null or empty";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void constructorWithEmptyNameShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Racer("ABC", "", "Test Team");
        });

        String expected = "Racer name cannot be null or empty";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void constructorWithNullTeamShouldThrowException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Racer("ABC", "Test Name", null);
        });

        String expected = "Team racing cannot be null or empty";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void setEndTimeBeforeStartTimeShouldThrowException() {
        Racer racer = new Racer("ABC", "Test Name", "Test Team");
        racer.setStartTime(1000); // 1 second

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            racer.setEndTime(500); // 0.5 seconds
        });

        String expected = "End time cannot be before start time";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }

    @Test
    void getLapTimeShouldReturnCorrectValue() {
        Racer racer = new Racer("ABC", "Test Name", "Test Team");
        racer.setStartTime(10000);
        racer.setEndTime(15000);

        long expected = 5000;
        long actual = racer.getLapTime();

        assertEquals(expected, actual);
    }

}