package git.foxminded.dzaimenko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class RacersData {
    private static final String ABBREVIATIONS_FILE = "abbreviations.txt";
    private static final String START_TIME_FILE = "start.log";
    private static final String END_TIME_FILE = "end.log";
    private final Map<String, Racer> racers;

    public Map<String, Racer> getRacers() {
        return racers;
    }

    public RacersData() {
        racers = new HashMap<>();
    }

    private long convertTimeToLong(String timeString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        Date time = dateFormat.parse(timeString);
        return time.getTime();
    }

    private Stream<String> readLinesFromFile(String filename) throws IOException {
        InputStream is = RacingMain.class.getClassLoader().getResourceAsStream(filename);

        if (is == null) {
            throw new IOException("Resource file " + filename + " not found");
        }

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        return reader.lines().onClose(() -> {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void loadRacersData() throws IOException {
        try (Stream<String> lines = readLinesFromFile(ABBREVIATIONS_FILE)) {
            lines.map(line -> line.split("_"))
                    .forEach(parts -> racers.put(parts[0], new Racer(parts[0], parts[1], parts[2])));
        }
    }

    private void loadStartTime() throws IOException {
        try (Stream<String> lines = readLinesFromFile(START_TIME_FILE)) {
            lines.forEach(line -> {
                String abbreviation = line.substring(0, 3);
                try {
                    long startTime = convertTimeToLong(line.substring(3));
                    racers.get(abbreviation).setStartTime(startTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void loadEndTime() throws IOException {
        try (Stream<String> lines = readLinesFromFile(END_TIME_FILE)) {
            lines.forEach(line -> {
                String abbreviation = line.substring(0, 3);
                try {
                    long endTime = convertTimeToLong(line.substring(3));
                    racers.get(abbreviation).setEndTime(endTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public void startRacersData() {
        try {

            loadRacersData();
            loadStartTime();
            loadEndTime();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
