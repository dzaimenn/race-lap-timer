package git.foxminded.dzaimenko;

import java.util.List;

public class RacingMain {
    public static void main(String[] args) {

        RacersData racersData = new RacersData();
        racersData.startRacersData();

        RacersSorter racersSorter = new RacersSorter();
        List<Racer> sortedRacers = racersSorter.sortRacersByLapTime(racersData.getRacers());

        RacingResultPrinter resultPrinter = new RacingResultPrinter();
        resultPrinter.printRacingResults(sortedRacers);


    }
}