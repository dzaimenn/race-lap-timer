package foxminded.dzaimenko.services;

import foxminded.dzaimenko.models.Racer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class RacersData {
    private final List<Racer> racerList = new ArrayList<>();

    public List<Racer> getRacers() {
        return racerList;
    }

    private List<String> readFile(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        if (!Files.exists(path) || !Files.isReadable(path)) {
            throw new IOException("Cannot access " + filePath);
        }

        try (Stream<String> lines = Files.lines(path)) {
            return lines.toList();
        }
    }

    private void readFileWriteResult() throws IOException {
        String abbreviationsFile = "src/main/resources/abbreviations.txt";
        String endTimeFile = "src/main/resources/end.log";
        String startTimeFile = "src/main/resources/start.log";

        List<String> abbreviationsList = readFile(abbreviationsFile);
        for (String abbreviationsLine : abbreviationsList) {
            String[] split = abbreviationsLine.split("_");
            racerList.add(new Racer(split[0], split[1], split[2]));
        }

        List<String> startTimeList = readFile(startTimeFile);
        for (String startTimeLine : startTimeList) {
            racerList.stream().filter(racer -> startTimeLine.substring(0, 3).equals(racer.getAbbreviation()))
                    .forEach(racer -> racer.setStartTime(LocalTime.parse(startTimeLine.substring(14))));
        }

        List<String> endTimeList = readFile(endTimeFile);
        for (String endTimeLine : endTimeList) {
            racerList.stream().filter(racer -> endTimeLine.substring(0, 3).equals(racer.getAbbreviation()))
                    .forEach(racer -> racer.setEndTime(LocalTime.parse(endTimeLine.substring(14))));
        }
    }

    public void startInitRacersData() throws IOException {
        readFileWriteResult();
    }

}