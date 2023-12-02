package git.foxminded.dzaimenko;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacersSorter {

    public List<Racer> sortRacersByLapTime(Map<String, Racer> racers) {
        return racers.values().stream()
                .sorted(Comparator.comparing(Racer::getLapTime))
                .collect(Collectors.toList());
    }

}
