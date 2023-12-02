package git.foxminded.dzaimenko;

import java.util.List;

public class RacingResultPrinter {

    public void printRacingResults(List<Racer> sortedRacers) {
        int rank = 1;
        for (Racer racer : sortedRacers) {
            String formattedOutput = String.format("%d. %-20s | %-30s | %s",
                    rank++,
                    racer.getNameRacer(),
                    racer.getTeamRacing(),
                    formatLapTime(racer.getLapTime()));
            System.out.println(formattedOutput);
        }
    }

    private String formatLapTime(long lapTime) {

        int minutes = (int) (lapTime / 60000);
        int seconds = (int) (lapTime % 60000) / 1000;
        int milliseconds = (int) (lapTime % 1000);

        return String.format("%d:%02d.%03d", minutes, seconds, milliseconds);
    }

}
