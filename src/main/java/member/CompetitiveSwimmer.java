package member;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CompetitiveSwimmer extends Member {
    private String coach;
    private String discipline;
    private String meet;
    private String placement;
    private LocalDate dateWhenAchieved;
    private Duration bestTime;


    public CompetitiveSwimmer(String name, String address, LocalDate birthday, int memberID, String email, boolean isCompetitive, boolean isActive, int arrears, String coach, String discipline, String meet, String placement, LocalDate dateWhenAchieved, int minutes, int seconds, int hundredths) {
        super(name, address, birthday, memberID, email, true, isActive, arrears);
        this.coach = coach;
        this.discipline = discipline;
        this.meet = meet;
        this.placement = placement;
        this.dateWhenAchieved = dateWhenAchieved;
        this.bestTime = Duration.ofMinutes(minutes).plusSeconds(seconds).plusMillis(hundredths * 10);

    }

    public Duration getBestTime() {
        return bestTime;
    }

    public void setBestTime(int minutes, int seconds, int hundredths) {
        this.bestTime = Duration.ofMinutes(minutes).plusSeconds(seconds).plusMillis(hundredths * 10);
    }

    public String formatBestTime() {
        long minutes = bestTime.toMinutes();
        int seconds = (int) (bestTime.getSeconds() % 60);
        int hundredths = (int) (bestTime.toMillis() % 1000) / 10;
        return String.format("%d:%02d:%02d", minutes, seconds, hundredths);
    }

    public String getCoach() {
        return coach;
    }

    public void setCoach(String newCoach) {
        this.coach = newCoach;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String newDiscipline) {
        this.discipline = newDiscipline;
    }

    public LocalDate getDateWhenAchieved() {
        return dateWhenAchieved;
    }

    public void setDateWhenAchieved(LocalDate dateWhenAchieved) {
        this.dateWhenAchieved = dateWhenAchieved;
    }

    public String formatDateWhenAchieved() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return dateWhenAchieved.format(formatter);
    }

    public String getMeet() {
        return meet;
    }

    public void setMeet(String newMeet) {
        this.meet = newMeet;
    }

    public String getPlacement() {
        return placement;
    }

    public void setPlacement(String newPlacement) {
        this.placement = newPlacement;
    }

    @Override
    public String toString() {
        return super.toString() + coach + "," + discipline + "," + meet + "," + placement + "," + formatDateWhenAchieved() + "," + formatBestTime();
    }
}



