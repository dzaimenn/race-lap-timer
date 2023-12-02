package git.foxminded.dzaimenko;

public class Racer {

    private String abbreviation;
    private String nameRacer;
    private String teamRacing;
    private long startTime;
    private long endTime;
    private long lapTime;

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

    public long getLapTime() {
        return lapTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
        setLapTime();
    }

    private void setLapTime() {
        this.lapTime = endTime - startTime;
    }

}
