package git.foxminded.dzaimenko;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RacerTest {


    @Test
    void testSetEndTimeBeforeStartTime() {
        Racer racer = new Racer("QWE", "Test Racer", "Test Team");
        racer.setStartTime(7000);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            racer.setEndTime(5000);
        });

        String expected = "End time cannot be before start time";
        String actual = exception.getMessage();

        assertEquals(expected, actual);
    }



}
