package git.foxminded.dzaimenko;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class RacingMain {
    public static void main(String[] args) {
        RacersData racersData = new RacersData();

        try (InputStream abbreviationsStream = Files.newInputStream(Paths.get("src/main/resources/abbreviations.txt"));
             InputStream startTimeStream = Files.newInputStream(Paths.get("src/main/resources/start.log"));
             InputStream endTimeStream = Files.newInputStream(Paths.get("src/main/resources/end.log"))) {

            racersData.startRacersData(abbreviationsStream, startTimeStream, endTimeStream);

        } catch (IOException e) {
            e.printStackTrace();
        }

        RacingResultPrinter resultPrinter = new RacingResultPrinter();
        List<Racer> sortedRacers = resultPrinter.sortRacersByLapTime(racersData.getRacers());
        resultPrinter.printRacingResults(sortedRacers);

    }

}