package git.foxminded.dzaimenko;

import java.util.List;

public class RacingMain {
    public static void main(String[] args) {

        RacersData racersData = new RacersData();
        racersData.startRacersData();

        RacingResultPrinter resultPrinter = new RacingResultPrinter();
        List<Racer> sortedRacers = resultPrinter.sortRacersByLapTime(racersData.getRacers());
        resultPrinter.printRacingResults(sortedRacers);


    }
}