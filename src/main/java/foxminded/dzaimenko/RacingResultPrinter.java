package foxminded.dzaimenko;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class RacingResultPrinter {

    private int maxLengthNameRacer;
    private int maxLengthNameTeam;

    public List<Racer> sortRacersByLapTime(Map<String, Racer> racers) {
        return racers.values().stream()
                .sorted(Comparator.comparing(Racer::getLapTime))
                .toList();
    }

    private String formatLapTime(long lapTime) {

        int minutes = (int) (lapTime / 60000);
        int seconds = (int) (lapTime % 60000) / 1000;
        int milliseconds = (int) (lapTime % 1000);

        return String.format("%d:%02d.%03d", minutes, seconds, milliseconds);
    }

    private void nameMaxLength(List<Racer> sortedRacers) {
        maxLengthNameRacer = sortedRacers.stream()
                .map(racer -> racer.getNameRacer().length())
                .max(Integer::compare)
                .orElse(0);

    }

    private void teamMaxLength(List<Racer> sortedRacers) {
        maxLengthNameTeam = sortedRacers.stream()
                .map(racer -> racer.getTeamRacing().length())
                .max(Integer::compare)
                .orElse(0);
    }

    public void printRacingResults(List<Racer> sortedRacers) {
        nameMaxLength(sortedRacers);
        teamMaxLength(sortedRacers);

        int rank = 1;
        for (Racer racer : sortedRacers) {
            String formattedOutput = String.format("%3d. %-" + maxLengthNameRacer + "s | %-" + maxLengthNameTeam + "s | %s",
                    rank++,
                    racer.getNameRacer(),
                    racer.getTeamRacing(),
                    formatLapTime(racer.getLapTime()));
            System.out.println(formattedOutput);

            if (rank == 16) {
                System.out.println("-".repeat(70));
            }
        }
    }

}
