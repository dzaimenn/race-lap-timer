package foxminded.dzaimenko;

public class Racer {

    private String abbreviation;
    private String nameRacer;
    private String teamRacing;
    private long startTime;
    private long endTime;
    private long lapTime;

    public Racer(String abbreviation, String nameRacer, String teamRacing) {

        if (abbreviation == null || abbreviation.trim().isEmpty()) {
            throw new IllegalArgumentException("Abbreviation cannot be null or empty");
        }
        if (nameRacer == null || nameRacer.trim().isEmpty()) {
            throw new IllegalArgumentException("Racer name cannot be null or empty");
        }
        if (teamRacing == null || teamRacing.trim().isEmpty()) {
            throw new IllegalArgumentException("Team racing cannot be null or empty");
        }

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

    public long getLapTime() {
        return lapTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        if (endTime < startTime) {
            throw new IllegalArgumentException("End time cannot be before start time");
        }
        this.endTime = endTime;
        setLapTime();
    }

    private void setLapTime() {
        this.lapTime = endTime - startTime;
    }

}
