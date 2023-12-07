package foxminded.dzaimenko;

import foxminded.dzaimenko.services.RacersData;
import foxminded.dzaimenko.services.RacingResultPrinter;

import java.io.IOException;


public class RacingMain {
    public static void main(String[] args) {
        RacersData racersData = new RacersData();

        try {
            racersData.startInitRacersData();
        } catch (IOException e) {
            e.printStackTrace();
        }

        RacingResultPrinter resultPrinter = new RacingResultPrinter();
        resultPrinter.viewRacingResult(racersData.getRacers());

    }

}