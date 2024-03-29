package foxminded.dzaimenko;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RacingMainTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void testRacingMainOutput() {
        RacingMain.main(new String[]{});

        String expectedOutput = """
                  1. Sebastian Vettel  | FERRARI                   | 1:04.415
                  2. Daniel Ricciardo  | RED BULL RACING TAG HEUER | 1:12.013
                  3. Valtteri Bottas   | MERCEDES                  | 1:12.434
                  4. Lewis Hamilton    | MERCEDES                  | 1:12.460
                  5. Stoffel Vandoorne | MCLAREN RENAULT           | 1:12.463
                  6. Kimi Raikkonen    | FERRARI                   | 1:12.639
                  7. Fernando Alonso   | MCLAREN RENAULT           | 1:12.657
                  8. Sergey Sirotkin   | WILLIAMS MERCEDES         | 1:12.706
                  9. Charles Leclerc   | SAUBER FERRARI            | 1:12.829
                 10. Sergio Perez      | FORCE INDIA MERCEDES      | 1:12.848
                 11. Romain Grosjean   | HAAS FERRARI              | 1:12.930
                 12. Pierre Gasly      | SCUDERIA TORO ROSSO HONDA | 1:12.941
                 13. Carlos Sainz      | RENAULT                   | 1:12.950
                 14. Esteban Ocon      | FORCE INDIA MERCEDES      | 1:13.028
                 15. Nico Hulkenberg   | RENAULT                   | 1:13.065
                ----------------------------------------------------------------------
                 16. Brendon Hartley   | SCUDERIA TORO ROSSO HONDA | 1:13.179
                 17. Marcus Ericsson   | SAUBER FERRARI            | 1:13.265
                 18. Lance Stroll      | WILLIAMS MERCEDES         | 1:13.323
                 19. Kevin Magnussen   | HAAS FERRARI              | 1:13.393
                """;
        String actualOutput = outputStream.toString().replace("\r\n", "\n");

        assertEquals(expectedOutput, actualOutput);
    }

}
