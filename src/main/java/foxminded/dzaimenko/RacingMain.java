package foxminded.dzaimenko;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RacingMain {
    public static void main(String[] args) {
        RacersData racersData = new RacersData();

        try {
            Path abbreviationsPath = Paths.get("src/main/resources/abbreviations.txt");
            Path startTimePath = Paths.get("src/main/resources/start.log");
            Path endTimePath = Paths.get("src/main/resources/end.log");

            if (!Files.exists(abbreviationsPath) || !Files.isReadable(abbreviationsPath)) {
                throw new IOException("Cannot access abbreviations file");
            }
            if (!Files.exists(startTimePath) || !Files.isReadable(startTimePath)) {
                throw new IOException("Cannot access start time file");
            }
            if (!Files.exists(endTimePath) || !Files.isReadable(endTimePath)) {
                throw new IOException("Cannot access end time file");
            }

            try (InputStream abbreviationsStream = Files.newInputStream(abbreviationsPath);
                 InputStream startTimeStream = Files.newInputStream(startTimePath);
                 InputStream endTimeStream = Files.newInputStream(endTimePath)) {

                racersData.startRacersData(abbreviationsStream, startTimeStream, endTimeStream);

            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        RacingResultPrinter resultPrinter = new RacingResultPrinter();
        List<Racer> sortedRacers = resultPrinter.sortRacersByLapTime(racersData.getRacers());
        resultPrinter.printRacingResults(sortedRacers);
    }

}