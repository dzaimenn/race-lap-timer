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

    private void loadRacersData() throws IOException {

        InputStream is = RacingMain.class.getClassLoader().getResourceAsStream(RacersData.ABBREVIATIONS_FILE);
        if (is == null) {
            throw new IOException("Resource file " + ABBREVIATIONS_FILE + " not found");
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            reader.lines()
                    .map(line -> line.split("_"))
                    .forEach(parts -> racers.put(parts[0], new Racer(parts[0], parts[1], parts[2])));
        }
    }

    private void loadStartTime() throws IOException {

        InputStream is = RacingMain.class.getClassLoader().getResourceAsStream(RacersData.START_TIME_FILE);
        if (is == null) {
            throw new IOException("Resource file " + START_TIME_FILE + " not found");
        }

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            reader.lines()
                    .forEach(line -> {
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

        InputStream is = RacingMain.class.getClassLoader().getResourceAsStream(RacersData.END_TIME_FILE);
        if (is == null) {
            throw new IOException("Resource file " + END_TIME_FILE + " not found");
        }

        try (
                BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            reader.lines()
                    .forEach(line -> {
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
