package git.foxminded.dzaimenko;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RacersDataTest {

    @Test
    void loadRacersDataFileNotFound(){
        RacersData racersData = new RacersData();
        Exception exception = assertThrows (IOException.class, () -> {
            racersData.startRacersData();
        });

        String expected = exception.getMessage();
        String actual = "Resource file abbreviations.txt not found";

        assertTrue(expected.contains(actual));
    }
}
