package foxminded.dzaimenko;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class RacersData {
    private final Map<String, Racer> racers;

    public Map<String, Racer> getRacers() {
        return racers;
    }

    public RacersData() {
        racers = new HashMap<>();
    }

    private boolean isValidAbbreviation(String abbreviation) {

        // Regex to check if the abbreviation is exactly 3 uppercase letters
        String regex = "^[A-Z]{3}$";
        return abbreviation.matches(regex);
    }

    private long convertTimeToLong(String timeString) throws ParseException {

        // Regex to validate date and time in the format "yyyy-MM-dd_HH:mm:ss.SSS"
        String regex = "^\\d{4}-\\d{2}-\\d{2}_\\d{2}:\\d{2}:\\d{2}\\.\\d{3}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(timeString);

        if (matcher.matches()) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
            Date time = dateFormat.parse(timeString);
            return time.getTime();
        } else {
            throw new ParseException("Invalid time format: " + timeString, 0);
        }
    }

    private Stream<String> readLinesFromInputStream(InputStream inputStream) {
        return new BufferedReader(new InputStreamReader(inputStream)).lines();
    }

    private void loadRacersData(InputStream inputStream) {
        try (Stream<String> lines = readLinesFromInputStream(inputStream)) {
            lines.map(line -> line.split("_"))
                    .forEach(parts -> {
                        if (isValidAbbreviation(parts[0])) {
                            racers.put(parts[0], new Racer(parts[0], parts[1], parts[2]));
                        } else {
                            throw new IllegalArgumentException("Invalid abbreviation format: " + parts[0]);
                        }
                    });
        }
    }

    private void loadStartTime(InputStream inputStream) {
        try (Stream<String> lines = readLinesFromInputStream(inputStream)) {
            lines.forEach(line -> {
                String abbreviation = line.substring(0, 3);
                try {
                    long startTime = convertTimeToLong(line.substring(3));
                    racers.get(abbreviation).setStartTime(startTime);
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Failed to parse end time for racer: " + abbreviation, e);
                }
            });
        }
    }

    private void loadEndTime(InputStream inputStream) {
        try (Stream<String> lines = readLinesFromInputStream(inputStream)) {
            lines.forEach(line -> {
                String abbreviation = line.substring(0, 3);
                try {
                    long endTime = convertTimeToLong(line.substring(3));
                    racers.get(abbreviation).setEndTime(endTime);
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Failed to parse end time for racer: " + abbreviation, e);
                }
            });
        }
    }

    public void startRacersData(InputStream abbreviationsFilePath, InputStream startTimeFilePath, InputStream endTimeFilePath) {

        loadRacersData(abbreviationsFilePath);
        loadStartTime(startTimeFilePath);
        loadEndTime(endTimeFilePath);

    }

}
