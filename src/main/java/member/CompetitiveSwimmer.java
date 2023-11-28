package member;

import java.time.Duration;
import java.time.LocalDate;


public class CompetitiveSwimmer extends Member {
    private String coach;
    private String discipline;
    private Duration bestTime;


    public CompetitiveSwimmer(String name, String address, LocalDate birthday, int memberID, String email, boolean isActive, String coach, String discipline, int minutes, int seconds, int hundredths) {
        super(name, address, birthday, memberID, email, true, isActive);
        this.coach = coach;
        this.discipline = discipline;
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
        return String.format("%d:%02d.%02d", minutes, seconds, hundredths);
    }

    public String getCoach() {
        return coach;
    }

    public String getDiscipline() {
        return discipline;
    }



    @Override
    public String toString() {
        return super.toString() +
                "CompetitiveSwimmer{" +
                "coach='" + coach + '\'' +
                ", discipline='" + discipline + '\'' +
                ", bestTime='" + formatBestTime() + '\'' +
                '}';
    }

}


