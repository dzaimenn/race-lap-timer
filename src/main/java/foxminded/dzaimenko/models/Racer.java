package foxminded.dzaimenko.models;

import java.time.Duration;
import java.time.LocalTime;

public class Racer {

    private String abbreviation;
    private String nameRacer;
    private String teamRacing;
    private LocalTime startTime;
    private LocalTime endTime;
    private Duration lapTime;

    public String getAbbreviation() {
        return abbreviation;
    }

    public Racer(String abbreviation, String nameRacer, String teamRacing) {

        this.abbreviation = abbreviation;
        this.nameRacer = nameRacer;
        this.teamRacing = teamRacing;
    }

    public String getNameRacer() {
        return nameRacer;
    }

    public String getTeamRacing() {
        return teamRacing;
    }

    public Duration getLapTime() {
        return lapTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalTime endTime) {
        if (startTime != null && endTime.isAfter(startTime)) {
            this.endTime = endTime;
            setLapTime();
        }
    }

    public void setLapTime() {
        if (startTime != null && endTime != null) {
            this.lapTime = Duration.between(startTime, endTime);
        }
    }

}
